package com.ffxl.cloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffxl.cloud.mapper.SysUserMapper;
import com.ffxl.cloud.model.SysRole;
import com.ffxl.cloud.model.SysUser;
import com.ffxl.cloud.model.SysUserExample;
import com.ffxl.cloud.model.base.BaseSysUserExample.Criteria;
import com.ffxl.cloud.service.SysRoleService;
import com.ffxl.cloud.service.SysUserService;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.exception.BusinessException;
import com.ffxl.platform.util.StringUtil;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SysUserServiceImpl extends GenericServiceImpl<SysUser, SysUserExample, String> implements SysUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public GenericMapper<SysUser, SysUserExample, String> getGenericMapper() {
        return sysUserMapper;
    }

    public SysUser queryByModel(SysUser sysUser) {
        SysUserExample example = new SysUserExample();
        Criteria c= example.createCriteria();
        if(!StringUtil.isEmpty(sysUser.getLoginName())){
            c.andLoginNameEqualTo(sysUser.getLoginName());
        }
        c.andLoginNameEqualTo(sysUser.getLoginName());
        List<SysUser> modelList =  sysUserMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SysUser> queryPageList(SysUser sysUser, Page page) {
		List<SysUser> list = sysUserMapper.queryPageList(sysUser,page);
		String rs = "";
		for(SysUser s:list){
			String roles = s.getRoleId();
			String[] roleStr = roles.split(",");
			for(int i = 0;i < roleStr.length;i++){
				SysRole sr = sysRoleService.selectByPrimaryKey(roleStr[i]);
				String name = sr.getName();
				rs = rs+name+",";
			}
			s.setRoles(rs);
		}
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

	@Override
	public int update(SysUser user) {
		//查询用户名是否重复
		SysUserExample example = new SysUserExample();
        Criteria c= example.createCriteria();
        c.andIdNotEqualTo(user.getId());
        c.andLoginNameEqualTo(user.getLoginName());
        List<SysUser> modelList =  sysUserMapper.selectByExample(example);
        if(modelList.size() > 0){
            throw new BusinessException(Message.M6002);
        }
        //查询用户信息
        SysUser u = sysUserMapper.selectByPrimaryKey(user.getId());
        if(u !=null){
            user.setLoginPassword(user.getLoginPassword());
            user.setStatus(user.getStatus());
        }
        int ret = sysUserMapper.updateByPrimaryKeySelective(user);
		return ret;
	}

	@Override
	public int updateUserRole(String userId, String roleIds) {
		return sysUserMapper.updateUserRole(userId,roleIds);
	}

}