package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgThmemeAnswerLogMapper;
import com.ffxl.cloud.model.SgThmemeAnswerLog;
import com.ffxl.cloud.model.SgThmemeAnswerLogExample;
import com.ffxl.cloud.model.base.BaseSgThmemeAnswerLogExample.Criteria;
import com.ffxl.cloud.service.SgThmemeAnswerLogService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SgThmemeAnswerLogServiceImpl extends GenericServiceImpl<SgThmemeAnswerLog, SgThmemeAnswerLogExample, String> implements SgThmemeAnswerLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgThmemeAnswerLogServiceImpl.class);

    @Autowired
    private SgThmemeAnswerLogMapper sgThmemeAnswerLogMapper;

    @Override
    public GenericMapper<SgThmemeAnswerLog, SgThmemeAnswerLogExample, String> getGenericMapper() {
        return sgThmemeAnswerLogMapper;
    }

    public SgThmemeAnswerLog queryByModel(SgThmemeAnswerLog sgThmemeAnswerLog) {
        SgThmemeAnswerLogExample example = new SgThmemeAnswerLogExample();
        Criteria c= example.createCriteria();
        List<SgThmemeAnswerLog> modelList =  sgThmemeAnswerLogMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public SgThmemeAnswerLog queryThemeAnswerUser(String id) {
		return sgThmemeAnswerLogMapper.queryThemeAnswerUser(id);
	}
}