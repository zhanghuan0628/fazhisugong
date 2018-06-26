package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SysUserMapper;
import com.ffxl.cloud.model.SysUser;
import com.ffxl.cloud.model.SysUserExample;
import com.ffxl.cloud.model.base.BaseSysUserExample.Criteria;
import com.ffxl.cloud.service.SysUserService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SysUserServiceImpl extends GenericServiceImpl<SysUser, SysUserExample, String> implements SysUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public GenericMapper<SysUser, SysUserExample, String> getGenericMapper() {
        return sysUserMapper;
    }

    public SysUser queryByModel(SysUser sysUser) {
        SysUserExample example = new SysUserExample();
        Criteria c= example.createCriteria();
        List<SysUser> modelList =  sysUserMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SysUser> queryPageList(SysUser sysUser, Page page) {
		return sysUserMapper.queryPageList(sysUser,page);
	}

	@Override
	public int updateStatus(String id, int status) {
		List<String> idList = new ArrayList<String>();
		idList.add(id);
		return sysUserMapper.updateStatus(idList,status);
	}

	@Override
	public int deleteByIds(List<String> idList) {
		return sysUserMapper.deleteByIds(idList);
	}

}