package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgLawMapper;
import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgLawExample;
import com.ffxl.cloud.model.base.BaseSgLawExample.Criteria;
import com.ffxl.cloud.service.SgLawService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SgLawServiceImpl extends GenericServiceImpl<SgLaw, SgLawExample, String> implements SgLawService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgLawServiceImpl.class);

    @Autowired
    private SgLawMapper sgLawMapper;

    @Override
    public GenericMapper<SgLaw, SgLawExample, String> getGenericMapper() {
        return sgLawMapper;
    }

    public SgLaw queryByModel(SgLaw sgLaw) {
        SgLawExample example = new SgLawExample();
        Criteria c= example.createCriteria();
        c.andTitleEqualTo(sgLaw.getTitle());
        List<SgLaw> modelList =  sgLawMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SgLaw> queryPageList(SgLaw sgLaw, Page page) {
		return sgLawMapper.queryPageList(sgLaw, page);
	}

	@Override
	public int updateSort(SgLaw bb) {
		return sgLawMapper.updateSort(bb);
	}

	@Override
	public int selectMaxSort(String id,String code, String category) {
		return sgLawMapper.selectMaxSort(id,code,category);
	}
}