package com.gemt.apex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gemt.apex.dao.PartsDao;
import com.gemt.apex.exception.RestError;
import com.gemt.apex.exception.RestException;
import com.gemt.apex.model.bom.Demand;
import com.gemt.apex.model.bom.Part;
import com.gemt.apex.model.bom.PartBin;
import com.gemt.apex.model.bom.PartDetail;
import com.gemt.apex.model.bom.PartMaterial;
import com.gemt.apex.model.bom.PartPlant;
import com.gemt.apex.model.bom.PartRev;
import com.gemt.apex.model.bom.Supply;
import com.gemt.apex.model.bom.SupplyJob;

@Service("partsService")
public class PartsService {
	@Autowired
	PartsDao partsDao;
	
	public Part getPartInfo(String partNum) throws Exception{
		Part part = partsDao.getPartInfo(partNum);
		if (part == null)
			throw new RestException(RestError.PART_NOT_FOUND, "Part not found: " + partNum);
		return part;
	}

	public PartDetail getPartDetail(String partNum) throws Exception{
		Part part = partsDao.getPartInfo(partNum);
		if (part == null)
			throw new RestException(RestError.PART_NOT_FOUND, "Part not found: " + partNum);
		PartDetail pd = new PartDetail(part);
		List<PartRev> revs = partsDao.getPartRevisions(partNum);
		if(revs.size() > 0)
			pd.setPartRev(revs);
		List<PartPlant> plants = partsDao.getPartPlants(partNum);
		if(plants.size() > 0)
			pd.setPartPlant(plants);
		return pd;
	}
	
	public List<PartRev> getPartRevisions(String partNum) throws Exception{
		List<PartRev> revs = partsDao.getPartRevisions(partNum);
		if (revs.size() < 1)
			throw new RestException(RestError.PART_REVISION_NOT_FOUND, "Revisions not found for part: " + partNum);
		return revs;
	}
	
	public List<PartPlant> getPartPlants(String partNum) throws Exception{
		List<PartPlant> plants = partsDao.getPartPlants(partNum);
		if (plants.size() < 1)
			throw new RestException(RestError.PART_PLANT_INFO_NOT_FOUND, "Plant information not found for part: " + partNum);
		return plants;
	}
	
	
	public PartRev getPrimaryPartRevision(String partNum) throws Exception{
		PartRev rev = null;
		try{
			rev = partsDao.getPrimaryPartRevision(partNum);
		}
		catch(EmptyResultDataAccessException ex){			
			throw new RestException(RestError.PART_REVISION_NOT_FOUND, "Revisions not found for part: " + partNum);			
		}
		return rev;
	}
	
	
	public List<PartMaterial> getPartMaterials(String partNum, String partRev) throws Exception{
		List<PartMaterial> mats = partsDao.listPartMaterialsRaw(partNum, partRev);
		if (mats.size() < 1)
			throw new RestException(RestError.MATERIALS_NOT_FOUND, "Materials not found for part: " + partNum + " [" + partRev + "]");
		else{
			for(PartMaterial item : mats){
				PartRev rev = null;
				try{					
					rev = partsDao.getPrimaryPartRevision(item.getPartNum());				
					item.setPartRev(rev.getRevisionNum());														
				}
				catch(RestException ex){
					item.setPartRev("");
				}				
			}
		}
		return mats;
	}
	
	public List<Demand> getPartDemandsFromJobs(String partNum) throws Exception{
		List<Demand> demands = partsDao.listPartDemandsFromJobs(partNum);
		if(demands.size() < 1)
			throw new RestException(RestError.JOB_DEMANDS_NOT_FOUND, "Job demands not found for part: " + partNum);
		return demands;
	}
	
/*	public List<Demand> getPartDemandsFromOrders(String partNum) throws Exception{
		List<Demand> demands = partsDao.listPartDemandsFromOrders(partNum);
		if(demands.size() < 1)
			throw new RestException(RestError.ORDER_DEMANDS_NOT_FOUND, "Sales orders not found for part: " + partNum);
		return demands;
	}*/
	
	public List<PartBin> getPartBins(String partNum) throws Exception{	
		List<PartBin> bins = partsDao.listPartBins(partNum);
		if(bins.size() < 1)
			throw new RestException(RestError.PART_BIN_NOT_FOUND, "Bins not found for part: " + partNum);
		return bins;
	}
	
	public List<SupplyJob> getPartSuppliesFromJobs(String partNum) throws Exception{	
		List<SupplyJob> supplies = partsDao.listPartSuppliesFromJobs(partNum);
		if(supplies.size() < 1)
			throw new RestException(RestError.JOB_SUPPLY_NOT_FOUND, "Supplies not found for part: " + partNum);
		return supplies;
	}
	
	public List<Supply> getPartSuppliesFromPOs(String partNum) throws Exception{	
		List<Supply> supplies = partsDao.listPartSuppliesFromPOs(partNum);
		if(supplies.size() < 1)
			throw new RestException(RestError.PO_SUPPLY_NOT_FOUND, "Supplies not found for part: " + partNum);
		return supplies;
	}
}
