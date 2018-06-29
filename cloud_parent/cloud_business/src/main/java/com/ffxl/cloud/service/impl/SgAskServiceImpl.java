package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgAskMapper;
import com.ffxl.cloud.model.SgAsk;
import com.ffxl.cloud.model.SgAskExample;
import com.ffxl.cloud.model.base.BaseSgAskExample.Criteria;
import com.ffxl.cloud.service.SgAskService;
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
public class SgAskServiceImpl extends GenericServiceImpl<SgAsk, SgAskExample, String> implements SgAskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgAskServiceImpl.class);

    @Autowired
    private SgAskMapper sgAskMapper;

    @Override
    public GenericMapper<SgAsk, SgAskExample, String> getGenericMapper() {
        return sgAskMapper;
    }

    public SgAsk queryByModel(SgAsk sgAsk) {
        SgAskExample example = new SgAskExample();
        Criteria c= example.createCriteria();
        List<SgAsk> modelList =  sgAskMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SgAsk> queryPageList(SgAsk sgAsk, Page page) {
		return sgAskMapper.queryPageList(sgAsk, page);
	}

	@Override
	public SgAsk queryUserAsk(String id) {
		return sgAskMapper.queryUserAsk(id);
	}
}