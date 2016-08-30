package com.gemt.apex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gemt.apex.dao.JobsDao;
import com.gemt.apex.dao.PartsDao;
import com.gemt.apex.exception.RestError;
import com.gemt.apex.exception.RestException;
import com.gemt.apex.model.bom.Demand;
import com.gemt.apex.model.bom.Inspection;
import com.gemt.apex.model.bom.Parent;
import com.gemt.apex.model.bom.Part;
import com.gemt.apex.model.bom.PartBin;
import com.gemt.apex.model.bom.PartDetail;
import com.gemt.apex.model.bom.PartMaterial;
import com.gemt.apex.model.bom.PartPlant;
import com.gemt.apex.model.bom.PartRev;
import com.gemt.apex.model.bom.Supply;
import com.gemt.apex.model.bom.SupplyJob;
import com.gemt.apex.model.job.Job;
import com.gemt.apex.model.job.JobMtl;
import com.gemt.apex.model.job.JobSubAssy;

@Service("jobsService")
public class JobsService {
	@Autowired
	JobsDao jobsDao;
	
	public Job getJobInfo(String jobNum) throws Exception{
		Job j = jobsDao.getJobInfo(jobNum);
		if (j == null)
			throw new RestException(RestError.JOB_NOT_FOUND, "Job not fund: " + jobNum);
		return j;
	}
	
	public List<JobSubAssy> getJobSubAssemblies(String jobNum, int assemblySeq) throws Exception{
		List<JobSubAssy> subs = jobsDao.getJobSubAssemblies(jobNum, assemblySeq);
		return subs;
	}
	
	public List<JobMtl> getJobMaterials(String jobNum, int assemblySeq) throws Exception{
		List<JobMtl> subs = jobsDao.getJobMaterials(jobNum, assemblySeq);
		return subs;
	}
}
