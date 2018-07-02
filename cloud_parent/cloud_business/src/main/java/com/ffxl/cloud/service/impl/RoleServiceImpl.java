package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.RoleMapper;
import com.ffxl.cloud.model.Role;
import com.ffxl.cloud.model.RoleExample;
import com.ffxl.cloud.model.base.BaseRoleExample.Criteria;
import com.ffxl.cloud.service.RoleService;
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
public class RoleServiceImpl extends GenericServiceImpl<Role, RoleExample, String> implements RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public GenericMapper<Role, RoleExample, String> getGenericMapper() {
        return roleMapper;
    }

    public Role queryByModel(Role role) {
        RoleExample example = new RoleExample();
        Criteria c= example.createCriteria();
        List<Role> modelList =  roleMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }
}