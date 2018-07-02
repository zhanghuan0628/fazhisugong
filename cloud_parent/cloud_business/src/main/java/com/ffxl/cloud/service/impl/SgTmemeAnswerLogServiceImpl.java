package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgTmemeAnswerLogMapper;
import com.ffxl.cloud.model.SgTmemeAnswerLog;
import com.ffxl.cloud.model.SgTmemeAnswerLogExample;
import com.ffxl.cloud.model.base.BaseSgTmemeAnswerLogExample.Criteria;
import com.ffxl.cloud.service.SgTmemeAnswerLogService;
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
public class SgTmemeAnswerLogServiceImpl extends GenericServiceImpl<SgTmemeAnswerLog, SgTmemeAnswerLogExample, String> implements SgTmemeAnswerLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgTmemeAnswerLogServiceImpl.class);

    @Autowired
    private SgTmemeAnswerLogMapper sgTmemeAnswerLogMapper;

    @Override
    public GenericMapper<SgTmemeAnswerLog, SgTmemeAnswerLogExample, String> getGenericMapper() {
        return sgTmemeAnswerLogMapper;
    }

    public SgTmemeAnswerLog queryByModel(SgTmemeAnswerLog sgTmemeAnswerLog) {
        SgTmemeAnswerLogExample example = new SgTmemeAnswerLogExample();
        Criteria c= example.createCriteria();
        List<SgTmemeAnswerLog> modelList =  sgTmemeAnswerLogMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }
}