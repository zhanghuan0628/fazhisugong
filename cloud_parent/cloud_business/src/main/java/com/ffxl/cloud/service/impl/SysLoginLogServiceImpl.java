package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SysLoginLogMapper;
import com.ffxl.cloud.model.SysLoginLog;
import com.ffxl.cloud.model.SysLoginLogExample;
import com.ffxl.cloud.model.base.BaseSysLoginLogExample.Criteria;
import com.ffxl.cloud.service.SysLoginLogService;
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
public class SysLoginLogServiceImpl extends GenericServiceImpl<SysLoginLog, SysLoginLogExample, String> implements SysLoginLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysLoginLogServiceImpl.class);

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public GenericMapper<SysLoginLog, SysLoginLogExample, String> getGenericMapper() {
        return sysLoginLogMapper;
    }

    public SysLoginLog queryByModel(SysLoginLog sysLoginLog) {
        SysLoginLogExample example = new SysLoginLogExample();
        Criteria c= example.createCriteria();
        List<SysLoginLog> modelList =  sysLoginLogMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SysLoginLog> queryPageList(SysLoginLog sysLoginLog, Page page) {
		return sysLoginLogMapper.queryPageList(sysLoginLog,page);
	}
}