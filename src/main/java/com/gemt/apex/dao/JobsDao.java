package com.gemt.apex.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gemt.apex.exception.RestError;
import com.gemt.apex.exception.RestException;
import com.gemt.apex.model.job.Job;
import com.gemt.apex.model.job.JobMtl;
import com.gemt.apex.model.job.JobSubAssy;

@Repository("jobsDao")
public class JobsDao {

	private JdbcTemplate jdbcTemplate;
	 
	@Autowired
	@Qualifier("dataSource1")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
			
	/**
	 * Get information about JOB
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Job  getJobInfo(String jobNum) throws Exception {
		Job job = null;
		String sql ="SELECT " +
					"ev_jobhead.JobNum, " +
					"ev_jobhead.PartNum, " +
					"ev_jobhead.PartDescription AS description, " +
					"ev_jobhead.ProdQty, " +
					"ev_jobhead.IUM, " +
					"ev_jobhead.StartDate, " +
					"ev_jobhead.ReqDueDate, " +
					"ev_jobhead.JobClosed, " +
					"ev_jobhead.ClosedDate, " +
					"ev_jobhead.JobComplete, " +
					"ev_jobhead.JobReleased, " +
					"ev_jobhead.JobCompletionDate, " +
					"ev_jobhead.DueDate, " +
					"ev_jobhead.ProjectID, " +
					"ev_jobhead.JobFirm " +
				 	"FROM ev_jobhead WHERE ev_jobhead.JobNum = ? ";

		RowMapper<Job> rm = BeanPropertyRowMapper.newInstance(Job.class);
		try{			
			job = jdbcTemplate.queryForObject(sql, new Object[]{jobNum}, rm);
		}
		catch(EmptyResultDataAccessException ex){
			throw new RestException(RestError.JOB_NOT_FOUND, "Job not found : " + jobNum);
		}
		return job;	
	}
	
	/**
	 * Get JOB Sub-Assenblies
	 * @param jobNum
	 * @param assemblySeq
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public List<JobSubAssy>  getJobSubAssemblies(String jobNum, int assemblySeq) throws Exception {
		List<JobSubAssy> subList = null;
		
		String sql = "SELECT " + 
					 "ev_jobasmbl.JobNum, " + 
					 "ev_jobasmbl.AssemblySeq, " + 
					 "ev_jobasmbl.PartNum, " +
				 	 "ev_part.PartDescription AS description, " +
				 	 "ev_jobasmbl.QtyPer, " +
				 	 "ev_jobasmbl.RequiredQty, " +
				 	 "ev_jobasmbl.IUM, " +
				 	 "ev_jobasmbl.Parent " +
				 	 "FROM ev_jobasmbl " + 
				 	 "LEFT JOIN ev_part ON ev_part.PartNum = ev_jobasmbl.PartNum " +
				 	 "WHERE ev_jobasmbl.JobNum = ? AND ev_jobasmbl.Parent = ? AND ev_jobasmbl.AssemblySeq > 0 " +
				 	 "ORDER BY ev_jobasmbl.AssemblySeq ASC ";					

		RowMapper<JobSubAssy> rm = BeanPropertyRowMapper.newInstance(JobSubAssy.class);
		try{			
			subList = jdbcTemplate.query(sql, new Object[]{jobNum, assemblySeq}, rm);
		}
		catch(EmptyResultDataAccessException ex){
			throw new RestException(RestError.DATA_NOT_FOUND, "No sub-assemblies found for : " + jobNum + ", AsmSeq: " + assemblySeq);
		}
		return subList;	
	}
	
	/**
	 * Get JOB Materials
	 * @param jobNum
	 * @param assemblySeq
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public List<JobMtl>  getJobMaterials(String jobNum, int assemblySeq) throws Exception {
		List<JobMtl> subList = null;
		
		String sql = "SELECT " + 
					 "ev_jobmtl.MtlSeq, " + 
					 "ev_jobmtl.BubbleNum, " + 
					 "ev_jobmtl.PartNum, " +
				 	 "ev_part.PartDescription AS description, " +
				 	 "ev_jobmtl.QtyPer, " +
				 	 "ev_jobmtl.RequiredQty, " +
				 	 "ev_jobmtl.IUM, " +
				 	 "ev_jobmtl.IssuedComplete, " +
				 	 "ev_jobmtl.IssuedQty " +
				 	 "FROM ev_jobmtl " + 
				 	 "LEFT JOIN ev_part ON ev_part.PartNum = ev_jobmtl.PartNum " +
				 	 "WHERE ev_jobmtl.JobNum = ? AND ev_jobmtl.AssemblySeq = ? " +
				 	 "ORDER BY ev_jobmtl.MtlSeq ASC ";					

		RowMapper<JobMtl> rm = BeanPropertyRowMapper.newInstance(JobMtl.class);
		try{			
			subList = jdbcTemplate.query(sql, new Object[]{jobNum, assemblySeq}, rm);
		}
		catch(EmptyResultDataAccessException ex){
			throw new RestException(RestError.DATA_NOT_FOUND, "No materials found for : " + jobNum + ", AsmSeq: " + assemblySeq);
		}
		return subList;	
	}
	
	
	/*
	 SELECT ev_jobmtl.JobNum, 
ev_jobmtl.AssemblySeq, 
ev_jobmtl.MtlSeq, 
ev_jobmtl.PartNum, 
ev_part.PartDescription,
ev_jobmtl.QtyPer, 
ev_jobmtl.RequiredQty
FROM ev_jobmtl
LEFT JOIN ev_part ON ev_part.PartNum = ev_jobmtl.PartNum
WHERE ev_jobmtl.JobNum = '43106' AND ev_jobmtl.AssemblySeq = 1


SELECT ev_jobasmbl.JobNum, ev_jobasmbl.AssemblySeq, ev_jobasmbl.PartNum, ev_part.PartDescription, 
ev_jobasmbl.Parent, ev_jobasmbl.PriorPeer, ev_jobasmbl.NextPeer, ev_jobasmbl.Child, ev_jobasmbl.QtyPer, ev_jobasmbl.RequiredQty 
FROM `ev_jobasmbl` 
LEFT JOIN ev_part ON ev_part.PartNum = ev_jobasmbl.PartNum
WHERE JobNum LIKE '43106' AND Parent = 1
ORDER BY AssemblySeq ASC


	 */
}
