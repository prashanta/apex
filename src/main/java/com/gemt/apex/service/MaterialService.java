package com.gemt.apex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gemt.apex.bean.MaterialBean;
import com.gemt.apex.bean.MaterialDetailBean;
import com.gemt.apex.bean.MaterialInfoBean;
import com.gemt.apex.bean.PartRevBean;
import com.gemt.apex.dao.MaterialDao;
import com.gemt.apex.dao.PartsDao;
import com.gemt.apex.model.bom.PartRev;

@Service
public class MaterialService {
	@Autowired
	MaterialDao materialDao;

	@Autowired
	PartsDao partsDao;

	public List<MaterialBean> getMaterials(String partNum, String revNum)
			throws Exception {
		return materialDao.getMaterials(partNum, revNum);
	}

	public List<MaterialBean> getMaterials(String partNum) throws Exception {
		PartRev partRev = partsDao.getPrimaryPartRevision(partNum);
		return materialDao.getMaterials(partNum, partRev.getRevisionNum());
	}
	
	public List<MaterialDetailBean> getMaterialDetails(String partNum)
			throws Exception {
		PartRev partRev = partsDao.getPrimaryPartRevision(partNum);
		return materialDao.getMaterialDetails(partNum, partRev.getRevisionNum());
	}

	public List<MaterialDetailBean> getMaterialDetails(String partNum,
			String revNum) throws Exception {
		return materialDao.getMaterialDetails(partNum, revNum);
	}

	public List<MaterialInfoBean> getMaterialInfo(String partNum) throws Exception {
		PartRev partRev = partsDao.getPrimaryPartRevision(partNum);
		return materialDao.getMaterialInfo(partNum, partRev.getRevisionNum());
	}
	
	public List<MaterialInfoBean> getMaterialInfo(String partNum, String revNum) throws Exception {
		return materialDao.getMaterialInfo(partNum, revNum);
	}
	
	public List<MaterialInfoBean> getPartMaterialBasic(String partNum, String revNum) throws Exception {
		List<MaterialInfoBean> list = materialDao.getMaterialBasic(partNum, revNum); 
		for(MaterialInfoBean item : list){			
			PartRevBean rev = materialDao.getValidPartRev(item.getMtlPartNum());
			if(rev.getRevisionNum() == null)
				item.setMtlPartRev(null);
			else
				item.setMtlPartRev(rev.getRevisionNum());
		}
		return list;
	}
}
