package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.RoleMenuRelMapper;
import com.ffxl.cloud.model.RoleMenuRel;
import com.ffxl.cloud.model.RoleMenuRelExample;
import com.ffxl.cloud.model.base.BaseRoleMenuRelExample.Criteria;
import com.ffxl.cloud.service.RoleMenuRelService;
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
public class RoleMenuRelServiceImpl extends GenericServiceImpl<RoleMenuRel, RoleMenuRelExample, String> implements RoleMenuRelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleMenuRelServiceImpl.class);

    @Autowired
    private RoleMenuRelMapper roleMenuRelMapper;

    @Override
    public GenericMapper<RoleMenuRel, RoleMenuRelExample, String> getGenericMapper() {
        return roleMenuRelMapper;
    }

    public RoleMenuRel queryByModel(RoleMenuRel roleMenuRel) {
        RoleMenuRelExample example = new RoleMenuRelExample();
        Criteria c= example.createCriteria();
        List<RoleMenuRel> modelList =  roleMenuRelMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }
}