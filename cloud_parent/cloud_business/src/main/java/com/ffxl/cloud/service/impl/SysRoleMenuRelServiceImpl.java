package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SysRoleMenuRelMapper;
import com.ffxl.cloud.model.SysRoleMenuRel;
import com.ffxl.cloud.model.SysRoleMenuRelExample;
import com.ffxl.cloud.model.base.BaseSysRoleMenuRelExample.Criteria;
import com.ffxl.cloud.service.SysRoleMenuRelService;
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
public class SysRoleMenuRelServiceImpl extends GenericServiceImpl<SysRoleMenuRel, SysRoleMenuRelExample, String> implements SysRoleMenuRelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleMenuRelServiceImpl.class);

    @Autowired
    private SysRoleMenuRelMapper sysRoleMenuRelMapper;

    @Override
    public GenericMapper<SysRoleMenuRel, SysRoleMenuRelExample, String> getGenericMapper() {
        return sysRoleMenuRelMapper;
    }

    public SysRoleMenuRel queryByModel(SysRoleMenuRel sysRoleMenuRel) {
        SysRoleMenuRelExample example = new SysRoleMenuRelExample();
        Criteria c= example.createCriteria();
        List<SysRoleMenuRel> modelList =  sysRoleMenuRelMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }
}