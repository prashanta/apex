package com.gemt.apex.dao;


import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gemt.apex.bean.MaterialBean;
import com.gemt.apex.bean.MaterialDetailBean;
import com.gemt.apex.bean.MaterialInfoBean;
import com.gemt.apex.bean.PartRevBean;
import com.gemt.apex.exception.*;

@Repository
public class MaterialDao {
		
	private JdbcTemplate jdbcTemplate;
	 
	@Autowired
	@Qualifier("dataSource1")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
//	@Autowired
//	@Qualifier("txManager1")
//	DataSourceTransactionManager txManager;

	static Logger log = Logger.getLogger(MaterialDao.class);
	
	String beanMapSQL = 
			"SELECT "+
			"PartNum as partNum, "+
			"RevisionNum as revisionNum, "+
			"MtlSeq as mtlSeq, "+
			"QtyPer as qtyPer, "+
			"MtlPartNum as mtlPartNum, "+
			"PullAsAsm as pullAsAsm, "+
			"ViewAsAsm as viewAsAsm ";
	/**
	 * Get part information. Retrieves information if part is active.
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	public List<MaterialBean> getMaterials(String partNum, String revNum) throws Exception{
		String sql =	
				"SELECT "+
				"PartNum as partNum, "+
				"RevisionNum as revisionNum, "+
				"MtlSeq as mtlSeq, "+
				"QtyPer as qtyPer, "+
				"MtlPartNum as mtlPartNum, "+
				"PullAsAsm as pullAsAsm, "+
				"ViewAsAsm as viewAsAsm " +
				"FROM ev_partmtl where PartNum = ? AND RevisionNum = ? ";
		
		System.out.println(">> rev num: '" + revNum + "'");
		try{
			//log.info("Retriving materials for part number: " + partNum);
			RowMapper<MaterialBean> rm = BeanPropertyRowMapper.newInstance(MaterialBean.class);
			List<MaterialBean> materials = jdbcTemplate.query(sql, new Object[]{partNum, revNum}, rm);
			if(materials.isEmpty())
				throw new Exception("MATERIALS_NOT_FOUND");
			return materials;
		}		
		catch(CannotGetJdbcConnectionException ex){
			ex.printStackTrace();
			throw new Exception("NO_DATABASE_CONNECTION" + ex.getMessage());
		}
	}
	
//	@Transactional(isolation=Isolation.READ_COMMITTED)
	public List<MaterialDetailBean> getMaterialDetails(String partNum, String revNum) throws Exception{
		String sql =	
				  "SELECT "
				+ "ev_partmtl.PartNum as partNum, "		
				+ "ev_partmtl.MtlSeq as mtlSeq, "
				+ "ev_partmtl.QtyPer as qtyPer, "
				+ "ev_partmtl.MtlPartNum as mtlPartNum, "
				+ "ev_partmtl.BubbleNum as bubbleNum, "
				+ "ev_partmtl.PullAsAsm as pullAsAsm, "
				+ "ev_partmtl.ViewAsAsm as viewAsAsm, "
				
				+ "ev_part.PartDescription as partDescription, "
				+ "ev_part.Class as partClass, "
				+ "ev_part.IUM as invUM, "
				+ "ev_part.TypeCode as partType, "
				+ "ev_part.NonStock as nonStock, "
				+ "ev_part.MFG as manufacturer, "
				+ "ev_part.MFGNo as manufacturerNumber, "
				+ "ev_part.Project as project, "
				//+ "ev_part.UserDecimal1 as estimatedCost, "
				+ "ev_part.MfgComment as mfgComment, "
				+ "ev_part.PurComment as purComment, "				
				
				+ "ev_partplant.LeadTime as leadTime, "
				+ "ev_partplant.ProcessMRP as processMRP, "
				+ "ev_partplant.DaysOfSupply as daysOfSupply, "
				+ "ev_partplant.GenerateSugg as genPOSuggestion, "
								
				+ "ev_partrev.RevisionNum as revisionNum, " 
				+ "ev_partrev.EffectiveDate as revisionEffectiveDate, "
				
				+ "ev_vendor.Name as approvedVendor, "
				
				+ "ev_plantwhse.PrimBin as primaryBin "
				
				+ "FROM ev_partmtl "
				+ "LEFT JOIN ev_part ON ev_part.PartNum = ev_partmtl.MtlPartNum "
				+ "LEFT JOIN ev_partPlant ON ev_partplant.PartNum = ev_partmtl.MtlPartNum "
				+ "LEFT JOIN ev_partrev ON ev_partrev.PartNum = ev_partmtl.MtlPartNum AND ev_partrev.Approved = 1 "
				+ "LEFT JOIN ev_aprvvend ON ev_aprvvend.PartNum = ev_partmtl.MtlPartNum " 
				+ "LEFT JOIN ev_vendor ON ev_aprvvend.VendorNum = ev_vendor.VendorNum "
				+ "LEFT JOIN ev_plantwhse ON ev_plantwhse.PartNum = ev_partmtl.MtlPartNum "
				
				+ "WHERE ev_partmtl.PartNum = ? AND ev_partmtl.RevisionNum = ? "
				+ "ORDER BY ev_partmtl.MtlSeq ";
		
	    
		log.info("Retriving materials with detail info for part number: " + partNum);			
		
		
		RowMapper<MaterialDetailBean> rm = BeanPropertyRowMapper.newInstance(MaterialDetailBean.class);		
		List<MaterialDetailBean> materials = jdbcTemplate.query(sql, new Object[]{partNum, revNum}, rm);			
	
		
		if(materials.isEmpty())
			throw new RestException(RestError.MATERIALS_NOT_FOUND, "Child parts not found for part: " + partNum + "[" + revNum + "]");
		
		return materials;
	}
	
	public List<MaterialInfoBean> getMaterialInfo(String partNum, String revNum)
			throws Exception {

		String sql = "SELECT "
				+ "ev_partmtl.PartNum as partNum, "
				+ "ev_partmtl.MtlSeq as mtlSeq, "
				+ "ev_partmtl.QtyPer as qtyPer, "
				+ "ev_partmtl.MtlPartNum as mtlPartNum, "
				+ "ev_partmtl.PullAsAsm as pullAsAsm, "
				+ "ev_partmtl.ViewAsAsm as viewAsAsm, "

				+ "ev_part.PartDescription as mtlPartDescription, "
				+ "ev_part.Class as mtlPartClass, "
				+ "ev_part.IUM as invUM, "
				+ "ev_part.TypeCode as mtlPartType, "

				+ "ev_partrev.RevisionNum as mtlPartRev, "
				+ "ev_partrev.EffectiveDate as revisionEffectiveDate "

				+ "FROM ev_partmtl "
				+ "LEFT JOIN ev_part ON ev_part.PartNum = ev_partmtl.MtlPartNum "
				+ "LEFT JOIN ev_partrev ON ev_partrev.PartNum = ev_partmtl.MtlPartNum AND ev_partrev.Approved = 1 "
			
				+ "WHERE ev_partmtl.PartNum = ? AND ev_partmtl.RevisionNum = ? "
				+ "ORDER BY ev_partmtl.MtlSeq ";

		//log.info("Retriving materials info for part number: "
		//		+ partNum);

		RowMapper<MaterialInfoBean> rm = BeanPropertyRowMapper.newInstance(MaterialInfoBean.class);
		List<MaterialInfoBean> materials = jdbcTemplate.query(sql,new Object[] { partNum, revNum }, rm);

		if (materials.isEmpty())
			throw new RestException(RestError.MATERIALS_NOT_FOUND);
		return materials;
	}
	
	public List<MaterialInfoBean> getMaterialBasic(String partNum, String revNum)
			throws Exception {

		String sql = "SELECT "
				+ "ev_partmtl.PartNum as partNum, "
				+ "ev_partmtl.MtlSeq as mtlSeq, "
				+ "ev_partmtl.BubbleNum as bubbleNum, "				
				+ "ev_partmtl.QtyPer as qtyPer, "
				+ "ev_partmtl.MtlPartNum as mtlPartNum, "
				+ "ev_partmtl.PullAsAsm as pullAsAsm, "
				+ "ev_partmtl.ViewAsAsm as viewAsAsm, "				

				+ "ev_part.PartDescription as mtlPartDescription, "
				+ "ev_part.Class as mtlPartClass, "
				+ "ev_part.IUM as invUM, "
				+ "ev_part.TypeCode as mtlPartType, "
				+ "ev_part.Project as mtlPartProject "				
				
				+ "FROM ev_partmtl "
				+ "LEFT JOIN ev_part ON ev_part.PartNum = ev_partmtl.MtlPartNum "
				
				+ "WHERE ev_partmtl.PartNum = ? AND ev_partmtl.RevisionNum = ? "
				+ "ORDER BY ev_partmtl.MtlSeq ";

		//log.info("Retriving materials info for part number: "
		//		+ partNum);

		RowMapper<MaterialInfoBean> rm = BeanPropertyRowMapper.newInstance(MaterialInfoBean.class);
		List<MaterialInfoBean> materials = jdbcTemplate.query(sql,new Object[] { partNum, revNum }, rm);

		if (materials.isEmpty())
			throw new RestException(RestError.MATERIALS_NOT_FOUND);
		return materials;
	}
	
	public PartRevBean getValidPartRev(String partNum)
			throws Exception {

		String sql = "SELECT "
				+ "ev_partrev.RevisionNum as revisionNum, "
				+ "ev_partrev.Approved as approved, "
				+ "ev_partrev.ApprovedDate as approvedDate, "
				+ "ev_partrev.ApprovedBy as approvedBy, "
				+ "ev_partrev.EffectiveDate as effectiveDate "
				
				+ "FROM ev_partrev "
				+ "WHERE ev_partrev.PartNum= ? order by ev_partrev.Approved DESC, ev_partrev.ApprovedDate DESC , ev_partrev.RevisionNum DESC";

		//log.info("Retriving valid revision for part number: " + partNum);

		RowMapper<PartRevBean> rm = BeanPropertyRowMapper.newInstance(PartRevBean.class);
		List<PartRevBean> revs = jdbcTemplate.query(sql, new Object[]{partNum}, rm);
		
		if (revs.size() > 0)
			return revs.get(0);
		else
			return new PartRevBean();
	}
}
