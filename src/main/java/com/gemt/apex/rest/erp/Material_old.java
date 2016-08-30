package com.gemt.apex.rest.erp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gemt.apex.bean.MaterialInfoBean;
import com.gemt.apex.exception.ErrorMessage;
import com.gemt.apex.service.BOMService;
import com.gemt.apex.service.MaterialService;

/**
 * Returns information on parts.
 * 
 * @author Prashanta.s
 */

@RestController
@RequestMapping(value="/mtl")
public class Material_old {
	@Autowired  
	MaterialService materialService;
	
	@Autowired  
	BOMService bomService;
	

	@RequestMapping(value="/{partnum}/{revnum}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Object getMaterials(@PathVariable(value = "partnum") String partNum, @PathVariable(value = "revnum") String revNum) throws Exception {
		return materialService.getMaterials(partNum, revNum);
	}
	
	@RequestMapping(value="/{partnum:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Object getMaterials(@PathVariable(value = "partnum") String partNum) throws Exception {
		return materialService.getMaterials(partNum);
	}
	
	@RequestMapping(value="/detail/{partnum:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Object getMaterailDetails(@PathVariable(value = "partnum") String partNum) throws Exception {
		return materialService.getMaterialDetails(partNum);
	}
	
	@RequestMapping(value="/detail/{partnum:.+}/{revnum}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Object getMaterailDetails(@PathVariable(value = "partnum") String partNum, @PathVariable(value = "revnum") String revNum) throws Exception {
		return materialService.getMaterialDetails(partNum, revNum);
	}	
	
	@Deprecated
	@RequestMapping(value = "/flatBOM/{partNum}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MaterialInfoBean>> getFlatBOM(@PathVariable(value = "partNum") String partNum) throws Exception {
		List<MaterialInfoBean> beans = materialService.getMaterialInfo(partNum);
		Map<String, MaterialInfoBean> m = bomService.getFlat(beans, 1,materialService);
		List<MaterialInfoBean> flatBOMs = new ArrayList<MaterialInfoBean>(m.values());
		System.out.println("Parts retried for FLAT BOM " + partNum + " : " + flatBOMs.size());
		return new ResponseEntity<List<MaterialInfoBean>>(flatBOMs, HttpStatus.OK);	
	}
	
	@Deprecated
	@RequestMapping(value = "/flatBOM/{partnum}/{revnum}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MaterialInfoBean>> getFlatBOM(@PathVariable(value = "partnum") String partNum, @PathVariable(value = "revnum") String revNum) throws Exception {
		List<MaterialInfoBean> beans = materialService.getMaterialInfo(partNum, revNum);
		Map<String, MaterialInfoBean> m = bomService.getFlat(beans, 1,materialService);
		List<MaterialInfoBean> flatBOMs = new ArrayList<MaterialInfoBean>(m.values());
		System.out.println("Parts retried for FLAT BOM " + partNum + " : " + flatBOMs.size());
		return new ResponseEntity<List<MaterialInfoBean>>(flatBOMs, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/mtlbasic/{partnum}/{revnum}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Object getPartMaterialBasic(@PathVariable(value = "partnum") String partNum, @PathVariable(value = "revnum") String revNum) throws Exception {
		return materialService.getPartMaterialBasic(partNum, revNum);	
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
