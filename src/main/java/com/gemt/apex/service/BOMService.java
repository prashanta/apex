package com.gemt.apex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gemt.apex.bean.MaterialInfoBean;


/**
 * Returns information on parts.
 * 
 * qtyCount counts the total quantity of parts required to build the material
 * 
 * compaerMap(parentMap,childMap) updates the parentMap, if the part in childMap
 * is found in parentMap, update the quantity and add to parentMap.
 * 
 * setFlatBomBean(MaterialDetailBean mdb,FlatBOMBean flatBomBean) sets id's of
 * FlatBOMBean
 * 
 * @throws Exception
 * @author Yemini.B
 */

@Service
public class BOMService {

	@Deprecated
	public Map<String, MaterialInfoBean> getFlat(List<MaterialInfoBean> mtlList, float n, MaterialService materialService) throws Exception {

		Map<String, MaterialInfoBean> masterMap = new HashMap<String, MaterialInfoBean>();
		Map<String, MaterialInfoBean> childrenMap = new HashMap<String, MaterialInfoBean>();

		BOMService bomService = new BOMService();

		// Do for all materials	
		for (MaterialInfoBean mtl : mtlList) 
		{
			MaterialInfoBean currentBean = new MaterialInfoBean();
			// If child part exists and is Pulled as Assembly, get its children
			if (mtl.isPullAsAsm() && mtl.getMtlPartNum() != null) { // condition if child exists
				
				String partNo = mtl.getMtlPartNum();  // partNo has view and pull as yes and yes and should not appear in flatBOM
				float qty = mtl.getQtyPer();		  // qty notes partNo quantity
				
				List<MaterialInfoBean> children = null;
				try{
					children = materialService.getMaterialInfo(partNo); // retrieve child parts of
				}
				catch(Exception e){
					System.out.println("Error when processing " + partNo + " : "+ e.getMessage());
				}
				finally{
					if ( children != null && children.size() > 0) {
						childrenMap = bomService.getFlat(children, qty, materialService); // recursive call
						compareMap(masterMap, childrenMap);
					}
				}
			}
			// Else child part is a material
			else{				
				// If part already in master map then add quantity
				if (masterMap.containsKey(mtl.getMtlPartNum())) { 
					MaterialInfoBean tempBean = masterMap.get(mtl.getMtlPartNum());
					float tmpCount = tempBean.getQtyPer();
					tempBean.setQtyPer(tmpCount + (mtl.getQtyPer() * n));
					masterMap.put(mtl.getMtlPartNum(), tempBean);
				} 
				// Else if part does not exist in master map then add
				else { 
					currentBean.setQtyPer(mtl.getQtyPer() * n);
					masterMap.put(mtl.getMtlPartNum(),setFlatBomBean(mtl, currentBean));
				}
			}
		}
		return masterMap;
	}

	private static void compareMap(Map<String, MaterialInfoBean> masterMap, Map<String, MaterialInfoBean> childrenMap) {

		for (String key : childrenMap.keySet()) {
			// If part already in master map then add quantity
			if (masterMap.containsKey(key)) { // if part exists in map
				MaterialInfoBean Parent_BOM = masterMap.get(key);
				MaterialInfoBean child_BOM = childrenMap.get(key);
				Parent_BOM.setQtyPer(Parent_BOM.getQtyPer() + child_BOM.getQtyPer());
				masterMap.put(key, Parent_BOM);
			} 
			// Else add new part to master map
			else { 				
				masterMap.put(key, childrenMap.get(key));
			}

		}
	}

	private static MaterialInfoBean setFlatBomBean(MaterialInfoBean mdb, MaterialInfoBean flatBomBean) {

		flatBomBean.setMtlPartClass(mdb.getMtlPartClass());
		flatBomBean.setMtlPartDescription(mdb.getMtlPartDescription());
		flatBomBean.setMtlPartNum(mdb.getMtlPartNum());
		flatBomBean.setInvUM(mdb.getInvUM());
		flatBomBean.setMtlPartType(mdb.getMtlPartType());

		return flatBomBean;
	}
	
}
