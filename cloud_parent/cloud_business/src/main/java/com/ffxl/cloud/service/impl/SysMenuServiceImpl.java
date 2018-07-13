package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SysMenuMapper;
import com.ffxl.cloud.model.SysMenu;
import com.ffxl.cloud.model.SysMenuExample;
import com.ffxl.cloud.model.base.BaseSysMenuExample.Criteria;
import com.ffxl.cloud.service.SysMenuService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.node.ZTreeNode;
import com.ffxl.platform.util.StringUtil;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SysMenuServiceImpl extends GenericServiceImpl<SysMenu, SysMenuExample, String> implements SysMenuService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public GenericMapper<SysMenu, SysMenuExample, String> getGenericMapper() {
        return sysMenuMapper;
    }

    public SysMenu queryByModel(SysMenu sysMenu) {
        SysMenuExample example = new SysMenuExample();
        Criteria c= example.createCriteria();
        if(!StringUtil.isEmpty(sysMenu.getCode())){
        	c.andCodeEqualTo(sysMenu.getCode());
        }
        if(!StringUtil.isEmpty(sysMenu.getUrl())){
        	c.andUrlEqualTo(sysMenu.getUrl());
        }
        List<SysMenu> modelList =  sysMenuMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SysMenu> getMenuIdsByRoleId(String roleId) {
		return sysMenuMapper.getMenuIdsByRoleId(roleId);
	}

	@Override
	public List<ZTreeNode> menuTreeList() {
		return sysMenuMapper.menuTreeList();
	}

	@Override
	public List<ZTreeNode> menuTreeListByMenuIds(List<SysMenu> menuIds) {
		return sysMenuMapper.menuTreeListByMenuIds(menuIds);
	}

	@Override
	public List<SysMenu> queryPageList(SysMenu sysMenu, Page page) {
		return sysMenuMapper.queryPageList(sysMenu,page);
	}

	@Override
	public int selectMaxNum() {
		return sysMenuMapper.selectMaxNum();
	}
}