package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SysOperationLogMapper;
import com.ffxl.cloud.model.SysOperationLog;
import com.ffxl.cloud.model.SysOperationLogExample;
import com.ffxl.cloud.model.base.BaseSysOperationLogExample.Criteria;
import com.ffxl.cloud.service.SysOperationLogService;
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
public class SysOperationLogServiceImpl extends GenericServiceImpl<SysOperationLog, SysOperationLogExample, String> implements SysOperationLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysOperationLogServiceImpl.class);

    @Autowired
    private SysOperationLogMapper sysOperationLogMapper;

    @Override
    public GenericMapper<SysOperationLog, SysOperationLogExample, String> getGenericMapper() {
        return sysOperationLogMapper;
    }

    public SysOperationLog queryByModel(SysOperationLog sysOperationLog) {
        SysOperationLogExample example = new SysOperationLogExample();
        Criteria c= example.createCriteria();
        List<SysOperationLog> modelList =  sysOperationLogMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }
}