package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SysRoleMapper;
import com.ffxl.cloud.model.SysRole;
import com.ffxl.cloud.model.SysRoleExample;
import com.ffxl.cloud.model.SysRoleMenuRel;
import com.ffxl.cloud.model.base.BaseSysRoleExample.Criteria;
import com.ffxl.cloud.service.SysRoleMenuRelService;
import com.ffxl.cloud.service.SysRoleService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.node.ZTreeNode;
import com.ffxl.platform.util.Convert;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

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
    
    @Autowired
    private SysRoleMenuRelService sysRoleMenuRelService;

    @Override
    public GenericMapper<SysRole, SysRoleExample, String> getGenericMapper() {
        return sysRoleMapper;
    }

    public SysRole queryByModel(SysRole sysRole) {
        SysRoleExample example = new SysRoleExample();
        Criteria c= example.createCriteria();
        if(!StringUtil.isEmpty(sysRole.getName())){
        	c.andNameEqualTo(sysRole.getName());
        }
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

	@Override
	public List<ZTreeNode> roleTreeList() {
		return sysRoleMapper.roleTreeList();
	}

	@Override
	public int selectMaxNum() {
		return sysRoleMapper.selectMaxNum();
	}

	@Override
	public int setAuthority(String roleId, String ids) {
		// 删除该角色所有的权限
		int i = -1;
        i = sysRoleMapper.deleteRolesById(roleId);
        if(!StringUtil.isEmpty(ids)){
        	String[] idss = ids.split(",");
        	for(String id:idss){
            	SysRoleMenuRel record = new SysRoleMenuRel();
            	record.setId(UUIDUtil.getUUID());
            	record.setRoleId(roleId);
            	record.setMenuId(id);
            	i = sysRoleMenuRelService.insertSelective(record);
            }
       
        }
		return i;
        
	}

	@Override
	public List<ZTreeNode> roleTreeListByRoleId(String[] strArray) {
		return sysRoleMapper.roleTreeListByRoleId(strArray);
	}
}