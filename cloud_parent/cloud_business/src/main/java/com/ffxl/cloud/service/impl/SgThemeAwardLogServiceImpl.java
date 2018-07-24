package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgThemeAwardLogMapper;
import com.ffxl.cloud.model.SgThemeAwardLog;
import com.ffxl.cloud.model.SgThemeAwardLogExample;
import com.ffxl.cloud.model.base.BaseSgThemeAwardLogExample.Criteria;
import com.ffxl.cloud.service.SgThemeAwardLogService;
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
public class SgThemeAwardLogServiceImpl extends GenericServiceImpl<SgThemeAwardLog, SgThemeAwardLogExample, String> implements SgThemeAwardLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgThemeAwardLogServiceImpl.class);

    @Autowired
    private SgThemeAwardLogMapper sgThemeAwardLogMapper;

    @Override
    public GenericMapper<SgThemeAwardLog, SgThemeAwardLogExample, String> getGenericMapper() {
        return sgThemeAwardLogMapper;
    }

    public SgThemeAwardLog queryByModel(SgThemeAwardLog sgThemeAwardLog) {
        SgThemeAwardLogExample example = new SgThemeAwardLogExample();
        Criteria c= example.createCriteria();
        List<SgThemeAwardLog> modelList =  sgThemeAwardLogMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SgThemeAwardLog> queryThemePageList(SgThemeAwardLog model, Page page) {
		List<SgThemeAwardLog> list = sgThemeAwardLogMapper.queryThemePageList(model,page);
		for(SgThemeAwardLog sd:list){
			String num = sd.getNum();
			sd.setStage("第"+num+"期");
		}
		return sgThemeAwardLogMapper.queryThemePageList(model,page);
	}
	
}