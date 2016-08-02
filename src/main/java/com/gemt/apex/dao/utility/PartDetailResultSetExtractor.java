package com.gemt.apex.dao.utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gemt.apex.model.bom.PartDetail;
import com.gemt.apex.model.bom.PartPlant;
import com.gemt.apex.model.bom.PartRev;


public class PartDetailResultSetExtractor implements ResultSetExtractor<PartDetail> {


	@Override
	public PartDetail extractData(ResultSet rs) throws SQLException, DataAccessException {
		PartDetail pd = null;
		ArrayList<PartRev> revs = new ArrayList<PartRev>();
		ArrayList<PartPlant> plants = new ArrayList<PartPlant>();
		while(rs.next()){	
			if(pd == null)
				pd = new PartDetail();
			if(pd.getPartNum() == null){
				BeanPropertyRowMapper<PartDetail> mapper = new BeanPropertyRowMapper<PartDetail>(PartDetail.class);
				PartDetail temp = mapper.mapRow(rs, 0);
				pd = temp;
			}
			String rev = rs.getString("revNum");
			if(rev != null){
				BeanPropertyRowMapper<PartRev> mapper = new BeanPropertyRowMapper<PartRev>(PartRev.class);
				PartRev pr = mapper.mapRow(rs, 0);
				revs.add(pr);
			}
			
			BeanPropertyRowMapper<PartPlant> mapper = new BeanPropertyRowMapper<PartPlant>(PartPlant.class);
			PartPlant plant = mapper.mapRow(rs, 0);
			if(plant != null){
				plants.add(plant);
			}				
		}
		if(revs.size() > 0)
			pd.setPartRev(revs);
			pd.setPartPlant(plants);
		return pd;
	}

}
