package com.gemt.apex.dao.utility;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.gemt.apex.model.bom.Demand;


public class DemandRowMapper implements RowMapper<Demand>{
									
			
	@Override
	public Demand mapRow(ResultSet rs, int arg1) throws SQLException {
		Demand demand = new Demand();
		demand.setPartNum(rs.getString("PartNum"));
		demand.setRevisionNum(rs.getString("RevisionNum"));
		demand.setRequirementFlag(rs.getBoolean("RequirementFlag"));
		demand.setDueDate(rs.getDate("DueDate"));
		demand.setQuantity(rs.getFloat("Quantity"));
		demand.setIum(rs.getString("IUM"));
		demand.setJobNum(rs.getString("JobNum"));
		demand.setSourceFile(rs.getString("SourceFile"));
		
		String finishedPart = rs.getString("finishedPart");
		demand.setFinishedPart( finishedPart==null? "" : finishedPart );
		
		String finishedPartDesc = rs.getString("finishedPartDescription");
		demand.setFinishedPartDescription( finishedPartDesc==null? "" : finishedPartDesc );
		
		Object prodQty = rs.getObject("jobQty");
		demand.setJobQty( prodQty==null? 0 : (float)prodQty );
		
		Object startDate = rs.getObject("jobStartDate");
		demand.setJobStartDate( startDate==null? null : (Date)startDate );
		
		demand.setProjectId(rs.getString("projectId"));
		
		return demand;// TODO Auto-generated method stub
	}

}
