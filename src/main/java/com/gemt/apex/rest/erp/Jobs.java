package com.gemt.apex.rest.erp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gemt.apex.exception.ErrorMessage;
import com.gemt.apex.model.job.JobMtl;
import com.gemt.apex.model.job.JobSubAssy;
import com.gemt.apex.service.JobsService;



/**
 * REST API for information on jobs.
 * 
 * @author Prashanta.s
 */

@RestController
@RequestMapping(value="/jobs/{jobnum}")
public class Jobs {
	
	@Autowired
	JobsService jobsService;

	/**
	 * Get information of job for given job number 
	 * 
	 * @param jobNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
	public Object getJobInfo(@PathVariable(value = "jobnum") String jobNum) throws Exception {
		return jobsService.getJobInfo(jobNum);
	}
	
	/**
	 * Get sub-assemblies for given job number and assembly sequence.
	 * 
	 * @param jobNum
	 * @param assemblySeq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/subs/{asmseq}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<JobSubAssy> getJobSubAssemblies(@PathVariable(value = "jobnum") String jobNum, @PathVariable(value = "asmseq") int assemblySeq) throws Exception {
		return jobsService.getJobSubAssemblies(jobNum, assemblySeq);
	}
	
	/**
	 * Get materials for given job number and assembly sequence.
	 * 
	 * @param jobNum
	 * @param assemblySeq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mtls/{asmseq}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<JobMtl> getJobMaterials(@PathVariable(value = "jobnum") String jobNum, @PathVariable(value = "asmseq") int assemblySeq) throws Exception {
		return jobsService.getJobMaterials(jobNum, assemblySeq);
	}
	
	@RequestMapping(value="**", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Object fallbackMethod(){
		ErrorMessage msg = new ErrorMessage();
		msg.setError(0);
		msg.setMessage("No end point found.");
		return msg;
	}
}
