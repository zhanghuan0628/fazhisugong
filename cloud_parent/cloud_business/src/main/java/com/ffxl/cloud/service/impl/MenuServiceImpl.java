package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.MenuMapper;
import com.ffxl.cloud.model.Menu;
import com.ffxl.cloud.model.MenuExample;
import com.ffxl.cloud.model.base.BaseMenuExample.Criteria;
import com.ffxl.cloud.model.warpper.MenuWarpper;
import com.ffxl.cloud.service.MenuService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.node.MenuNode;
import com.ffxl.platform.util.ToolUtil;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class MenuServiceImpl extends GenericServiceImpl<Menu, MenuExample, String> implements MenuService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public GenericMapper<Menu, MenuExample, String> getGenericMapper() {
        return menuMapper;
    }

    public Menu queryByModel(Menu menu) {
        MenuExample example = new MenuExample();
        Criteria c= example.createCriteria();
        if(ToolUtil.isNotEmpty(menu.getCode())){
            c.andCodeEqualTo(menu.getCode());
        }
        List<Menu> modelList =  menuMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<String> getResUrlsByRoleId(String roleId) {
        return menuMapper.getResUrlsByRoleId(roleId);
    }

    @Override
    public List<MenuNode> getMenusByRoleIds(List<String> roleIds) {
        return menuMapper.getMenusByRoleIds(roleIds);
    }

    @Override
    public MenuWarpper queryMenuByCode(String code) {
        return menuMapper.queryMenuByCode(code);
    }
}