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
import com.gemt.apex.model.bom.Demand;
import com.gemt.apex.model.bom.Inspection;
import com.gemt.apex.model.bom.Parent;
import com.gemt.apex.model.bom.PartBin;
import com.gemt.apex.model.bom.PartDetail;
import com.gemt.apex.model.bom.PartMaterial;
import com.gemt.apex.model.bom.PartPlant;
import com.gemt.apex.model.bom.PartRev;
import com.gemt.apex.model.bom.Supply;
import com.gemt.apex.model.bom.SupplyJob;
import com.gemt.apex.service.PartsService;



/**
 * REST API for information on parts.
 * 
 * @author Prashanta.s
 */

@RestController
@RequestMapping(value="/parts/{partnum}")
public class Parts {
	
	@Autowired
	@Qualifier("partsService")
	PartsService partsService;

	/**
	 * Get information of part for given part number 
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
	public Object getPartInfo(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partsService.getPartInfo(partNum);
	}
	
	/**
	 * Get detail information of part for given part number 
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/detail", produces=MediaType.APPLICATION_JSON_VALUE)
	public PartDetail getPartDetail(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partsService.getPartDetail(partNum);
	}
	
	/**
	 * Get revisions of part for given part number 
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/revisions", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PartRev> getPartRevisions(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partsService.getPartRevisions(partNum);
	}
	
	/**
	 * Get plant information of part for given part number 
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/plants", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PartPlant> getPartPlants(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partsService.getPartPlants(partNum);
	}
	
	/**
	 * Get primary revision of part number. If approved revision is more than one, returns the latest. 
	 * If no approved revisions found, returns the latest un-approved revision. 
	 * 
	 * @param partNum
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/revisions/primary", produces=MediaType.APPLICATION_JSON_VALUE)
	public PartRev getPrimaryPartRevisions(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partsService.getPrimaryPartRevision(partNum);
	}
	
	/**
	 * Get materials for given part number and part revision.
	 * 
	 * @param partNum
	 * @param partRev
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{partrev}/materials", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PartMaterial> getPartMaterials(@PathVariable(value = "partnum") String partNum, @PathVariable(value = "partrev") String partRev) throws Exception {
		return partsService.getPartMaterials(partNum, partRev);
	}
	
	/**
	 * Get demands for a given part.
	 * 
	 * @param partNum
	 * @param partRev
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/demands/jobs", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Demand> getPartDemandsFromJobs(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partsService.getPartDemandsFromJobs(partNum);
	}

	
	/**
	 * Get bins for given part.
	 * 
	 * @param partNum
	 * @param partRev
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bins", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PartBin> getPartBins(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partsService.getPartBins(partNum);
	}
	
	@RequestMapping(value="/supplies/jobs", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SupplyJob> getPartSuppliesFromJobs(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partsService.getPartSuppliesFromJobs(partNum);
	}
	
	@RequestMapping(value="/supplies/pos", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Supply> getPartSuppliesFromPOs(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partsService.getPartSuppliesFromPOs(partNum);
	}
	
	@RequestMapping(value="/parents", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Parent> getPartParents(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partsService.getPartParents(partNum);
	}
	
	@RequestMapping(value="/inspections", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Inspection> getPendingInspections(@PathVariable(value = "partnum") String partNum) throws Exception {
		return partsService.getPendingInspections(partNum);
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
