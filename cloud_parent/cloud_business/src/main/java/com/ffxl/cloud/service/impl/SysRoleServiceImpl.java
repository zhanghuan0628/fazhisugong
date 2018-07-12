package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SysRoleMapper;
import com.ffxl.cloud.model.SysRole;
import com.ffxl.cloud.model.SysRoleExample;
import com.ffxl.cloud.model.base.BaseSysRoleExample.Criteria;
import com.ffxl.cloud.service.SysRoleService;
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
public class SysRoleServiceImpl extends GenericServiceImpl<SysRole, SysRoleExample, String> implements SysRoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public GenericMapper<SysRole, SysRoleExample, String> getGenericMapper() {
        return sysRoleMapper;
    }

    public SysRole queryByModel(SysRole sysRole) {
        SysRoleExample example = new SysRoleExample();
        Criteria c= example.createCriteria();
        List<SysRole> modelList =  sysRoleMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SysRole> queryPageList(SysRole sysRole, Page page) {
		return sysRoleMapper.queryPageList(sysRole,page);
	}
}