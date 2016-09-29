package com.gemt.apex.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gemt.apex.dao.utility.DemandRowMapper;
import com.gemt.apex.exception.RestError;
import com.gemt.apex.exception.RestException;
import com.gemt.apex.model.bom.Demand;
import com.gemt.apex.model.bom.Inspection;
import com.gemt.apex.model.bom.Parent;
import com.gemt.apex.model.bom.Part;
import com.gemt.apex.model.bom.PartBin;
import com.gemt.apex.model.bom.PartMaterial;
import com.gemt.apex.model.bom.PartPlant;
import com.gemt.apex.model.bom.PartRev;
import com.gemt.apex.model.bom.PlantWhse;
import com.gemt.apex.model.bom.SalesOrder;
import com.gemt.apex.model.bom.Supply;
import com.gemt.apex.model.bom.SupplyJob;

@Repository("partsDao")
public class PartsDao {

	private JdbcTemplate jdbcTemplate;
	 
	@Autowired
	@Qualifier("dataSource1")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	String beanMapSQL = "ev_part.PartNum as partNum, "
					+ 	"ev_part.PartDescription as description, "
					+ 	"ev_part.Class as partClass, "
					+ 	"ev_part.IUM as ium, "
					+ 	"ev_part.PUM as pum, "
					+ 	"ev_part.TypeCode as typeCode, "
					+ 	"ev_part.MfgComment as mfgComment, "
					+ 	"ev_part.PurComment as purComment, "
					+ 	"ev_part.MFG as mfg, "
					+ 	"ev_part.MFGNo as mfgNo, "
					+ 	"ev_part.Project as project, "
					+ 	"ev_part.NonStock as nonStock, "
					+ 	"ev_part.InActive as inActive, "
					+ 	"ev_part.PhantomBOM as phantomBOM ";
			
			
	/**
	 * Get information about part for given part number
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Part getPartInfo(String partNum) throws Exception {
		Part part = null;
		String sql ="SELECT " +
				 	beanMapSQL +
				 	"FROM ev_part WHERE PartNum = ? ";

		RowMapper<Part> rm = BeanPropertyRowMapper.newInstance(Part.class);
		try{			
			part = jdbcTemplate.queryForObject(sql, new Object[]{partNum}, rm);
		}
		catch(EmptyResultDataAccessException ex){
			throw new RestException(RestError.PART_NOT_FOUND, "Part not found : " + partNum);
		}
		return part;	
	}
	
	@Transactional
	public List<PartRev> getPartRevisions(String partNum) throws Exception {
		List<PartRev> partRev = null;
		String sql = 	"SELECT * " + 
						"FROM ev_partrev " +
						"WHERE ev_partrev.PartNum = ? ";
		
		RowMapper<PartRev> rm = BeanPropertyRowMapper.newInstance(PartRev.class);
		partRev = jdbcTemplate.query(sql, new Object[]{partNum}, rm);		
		return partRev;
	}
	
	@Transactional
	public List<PartPlant> getPartPlants(String partNum) throws Exception {
		List<PartPlant> plants = null;
		String sql = 	"SELECT * " + 
						"FROM ev_partplant " +
						"WHERE ev_partplant.PartNum = ? ";
		
		RowMapper<PartPlant> rm = BeanPropertyRowMapper.newInstance(PartPlant.class);
		plants = jdbcTemplate.query(sql, new Object[]{partNum}, rm);		
		return plants;
	}
	
	@Transactional
	public PlantWhse getPartPrimaryBin(String partNum) throws Exception {
		String sql = 	"SELECT PrimBin as primaryBin " + 
						"FROM ev_plantWhse " +
						"WHERE ev_plantWhse.PartNum = ? LIMIT 1";
		
		PlantWhse bin = jdbcTemplate.queryForObject(sql, new Object[]{partNum}, BeanPropertyRowMapper.newInstance(PlantWhse.class));		
		return bin;
	}
	
	@Transactional
	public PartRev getPrimaryPartRevision(String partNum) throws Exception {
		PartRev partRev = null;
		String sql = 	"SELECT * " + 
				 		"FROM ev_partrev " +
				 		"WHERE partNum= ? " +
				 		"ORDER BY Approved DESC, ApprovedDate DESC , RevisionNum DESC " +
				 		"LIMIT 1";
		
		RowMapper<PartRev> rm = BeanPropertyRowMapper.newInstance(PartRev.class);
		try{
			partRev = jdbcTemplate.queryForObject(sql, new Object[]{partNum}, rm);
		}
		catch(EmptyResultDataAccessException ex){
			throw new RestException(RestError.PART_REVISION_NOT_FOUND, "Revision not found for: " + partNum);
		}
		return partRev;
	}
	
	
	@Transactional
	public List<PartMaterial> listPartMaterialsRaw(String partNum, String partRev) {
		List<PartMaterial> listPart = null;
		String sql =	"SELECT " +
						"ev_partMtl.PartNum as parentPartNum, " + 
						"ev_partMtl.RevisionNum as parentPartRev, " + 
						"ev_partMtl.MtlSeq, " + 
						"ev_partMtl.BubbleNum, " + 
						"ev_partMtl.MtlPartNum as partNum, " +
						"ev_partMtl.QtyPer, " +
						"ev_partMtl.FixedQty, " +
						"ev_partMtl.MfgComment, " +
						"ev_partMtl.PurComment, " +
						"ev_partMtl.PullAsAsm, " +
						"ev_partMtl.ViewAsAsm, " +
						
						"ev_part.PartDescription, " +
						"ev_part.Class as partClass, " +
						"ev_part.TypeCode, " +
						"ev_part.NonStock, " +
						"ev_part.Project, " +						
						"ev_part.IUM " +
						
						"FROM ev_partmtl " +
						"LEFT JOIN ev_part ON ev_partmtl.MtlPartNum = ev_part.PartNum " +
						"WHERE ev_partmtl.partNum = ? AND ev_partmtl.revisionNum = ? " +
						"ORDER BY ev_partMtl.MtlSeq ASC ";
		
		RowMapper<PartMaterial> rm = BeanPropertyRowMapper.newInstance(PartMaterial.class);
		
		listPart = jdbcTemplate.query(sql, new Object[]{partNum, partRev}, rm);							
		return listPart;
	}
	
	@Transactional
	public List<PartBin> listPartBins(String partNum){
		List<PartBin> bins = null;
		String sql =	"SELECT " +
						"ev_partbin.BinNum, " + 
						"ev_partbin.OnhandQty as onHandQty " + 
						
						"FROM ev_partbin " +
						"WHERE ev_partbin.partNum = ? " +
						"ORDER BY ev_partbin.BinNum ASC ";
		
		RowMapper<PartBin> rm = BeanPropertyRowMapper.newInstance(PartBin.class);
		
		bins = jdbcTemplate.query(sql, new Object[]{partNum}, rm);							
		return bins;
	}
	
	@Transactional
	public List<Demand> listPartDemandsFromJobs(String partNum){
		List<Demand> demands = null;
		String sql =	"SELECT " +
						"ev_partdtl.PartNum, " + 
						"ev_partdtl.RevisionNum, " + 
						"ev_partdtl.RequirementFlag, " + 
						"ev_partdtl.DueDate, " + 
						"SUM(ev_partdtl.Quantity) as quantity, " +
						"ev_partdtl.IUM, " +
						"ev_partdtl.JobNum, " +
						"ev_partdtl.SourceFile, " +
						
						"ev_jobhead.PartNum as finishedPart, " +
						"ev_jobhead.PartDescription as finishedPartDescription, " +
						"ev_jobhead.ProdQty as jobQty, " +
						"ev_jobhead.StartDate as jobStartDate, " +
						"ev_jobhead.ProjectID as projectId " +
												
						"FROM ev_partdtl " +
						"LEFT JOIN ev_jobhead ON ev_partdtl.JobNum = ev_jobhead.JobNum " +
						"WHERE ev_partdtl.partNum = ? AND " + 
						"ev_partdtl.RequirementFlag = 1 AND ev_partdtl.SourceFile = 'JM' " +
						"GROUP BY ev_partdtl.JobNum "+
						"ORDER BY ev_partdtl.DueDate ASC ";
		
		demands = jdbcTemplate.query(sql, new Object[]{partNum}, new DemandRowMapper());							
		return demands;
	}
	
	/*@Transactional
	public List<Demand> listPartDemandsFromOrders(String partNum){
		List<Demand> demands = null;
		String sql =	"SELECT " +
						"ev_partdtl.PartNum, " + 
						"ev_partdtl.RevisionNum, " + 
						"ev_partdtl.RequirementFlag, " + 
						"ev_partdtl.DueDate, " + 
						"SUM(ev_partdtl.Quantity) as quantity, " +
						"ev_partdtl.IUM, " +
						"ev_partdtl.OrderNum, " +
						"ev_partdtl.SourceFile " +
						
						"FROM ev_partdtl " +
						"WHERE ev_partdtl.partNum = ? AND " + 
						"ev_partdtl.RequirementFlag = 1 AND ev_partdtl.SourceFile = 'OR' " +
						"GROUP BY ev_partdtl.OrderNum "+
						"ORDER BY ev_partdtl.DueDate ASC ";
		
		RowMapper<Demand> rm = BeanPropertyRowMapper.newInstance(Demand.class);
		
		demands = jdbcTemplate.query(sql, new Object[]{partNum}, rm);							
		return demands;
	}*/
	
	@Transactional
	public List<SupplyJob> listPartSuppliesFromJobs(String partNum){
		List<SupplyJob> supplies = null;
		String sql =	"SELECT " +
						"ev_partdtl.PartNum, " + 
						"ev_partdtl.RevisionNum, " + 
						"ev_partdtl.RequirementFlag, " + 
						"ev_partdtl.DueDate, " + 
						"SUM(ev_partdtl.Quantity) as quantity, " +
						"ev_partdtl.IUM, " +
						"ev_partdtl.JobNum, " +
						"ev_partdtl.SourceFile " +
						
						"FROM ev_partdtl " +
						"WHERE ev_partdtl.partNum = ? " +
						"AND ev_partdtl.RequirementFlag = 0 AND ev_partdtl.SourceFile = 'JH'" +
						"GROUP BY ev_partdtl.JobNum "+
						"ORDER BY ev_partdtl.DueDate ASC ";
		
		RowMapper<SupplyJob> rm = BeanPropertyRowMapper.newInstance(SupplyJob.class);
		
		supplies = jdbcTemplate.query(sql, new Object[]{partNum}, rm);							
		return supplies;
	}
	
	@Transactional
	public List<Supply> listPartSuppliesFromPOs(String partNum){
		List<Supply> supplies = null;
		String sql =	"SELECT " +
						"ev_partdtl.PartNum, " + 
						"ev_partdtl.RevisionNum, " + 
						"ev_partdtl.RequirementFlag, " + 
						"ev_partdtl.DueDate, " + 
						"SUM(ev_partdtl.Quantity) as quantity, " +
						"ev_partdtl.IUM, " +
						"ev_partdtl.PONum, " +
						"ev_partdtl.SourceFile, " +
						
						"ev_vendor.Name as vendorName " +
						
						"FROM ev_partdtl " +
						"LEFT JOIN ev_poheader ON ev_partdtl.PONum = ev_poheader.PONum " +
						"LEFT JOIN ev_vendor ON ev_poheader.VendorNum = ev_vendor.VendorNum " +
						
						"WHERE ev_partdtl.partNum = ? " +
						"AND ev_partdtl.RequirementFlag = 0 AND ev_partdtl.SourceFile = 'PO'" +
						"GROUP BY ev_partdtl.PONum "+
						"ORDER BY ev_partdtl.DueDate ASC ";
				
		RowMapper<Supply> rm = BeanPropertyRowMapper.newInstance(Supply.class);
		
		supplies = jdbcTemplate.query(sql, new Object[]{partNum}, rm);							
		return supplies;
	}
	
	@Transactional
	public List<Parent> getPartParents(String partNum){
		List<Parent> parents = null;
		String sql =	"SELECT " +
						"ev_partmtl.PartNum, " +
						"ev_partmtl.RevisionNum, " +
						"ev_part.PartDescription, " +
						"ev_partmtl.QtyPer, " +						
						"ev_partmtl.BubbleNum, " +						
						"ev_partmtl.MtlSeq, " +						
						"ev_part.Class as partClass, " +
						"ev_part.TypeCode, " +
						"ev_partrev.Approved as revApproved " +

						"FROM ev_partmtl " +
						"LEFT JOIN ev_part ON ev_part.PartNum = ev_partmtl.PartNum " +
						"LEFT JOIN ev_partrev ON ev_partrev.PartNum = ev_partmtl.PartNum AND ev_partrev.PartNum = ev_partmtl.PartNum " +
						"WHERE ev_partmtl.MtlPartNum = ? ";
	
		RowMapper<Parent> rm = BeanPropertyRowMapper.newInstance(Parent.class);
		
		parents = jdbcTemplate.query(sql, new Object[]{partNum}, rm);							
		return parents;
	}
	
	@Transactional
	public List<Inspection> getPendingInspections(String partNum){
		List<Inspection> inspections = null;
		String sql =	"SELECT " +
				"ev_rcvdtl.PackSlip, " +
				"ev_rcvdtl.BinNum, " +						
				"ev_rcvdtl.PONum, " +						
				"ev_rcvdtl.VendorQty, " +						
				"ev_rcvdtl.ReceivedTo, " +
				"ev_rcvdtl.receiptDate " +
				
						"FROM ev_rcvdtl " +
						"WHERE ev_rcvdtl.PartNum = ? AND ev_rcvdtl.InspectionPending = 1";
		
		RowMapper<Inspection> rm = BeanPropertyRowMapper.newInstance(Inspection.class);
		
		inspections = jdbcTemplate.query(sql, new Object[]{partNum}, rm);							
		return inspections;
	}
	
	@Transactional
	public List<SalesOrder> getSalesOrders(String partNum){
		List<SalesOrder> orders = null;
		String sql =	"SELECT " +
						"ev_partdtl.OrderNum, " +
						"ev_partdtl.OrderLine, " +						
						"ev_partdtl.Quantity, " +						
						"ev_partdtl.DueDate " +
						"FROM ev_partdtl " +
						"WHERE ev_partdtl.PartNum = ? AND OrderNum > 0";
	
		RowMapper<SalesOrder> rm = BeanPropertyRowMapper.newInstance(SalesOrder.class);
		
		orders = jdbcTemplate.query(sql, new Object[]{partNum}, rm);							
		return orders;
	}
}
