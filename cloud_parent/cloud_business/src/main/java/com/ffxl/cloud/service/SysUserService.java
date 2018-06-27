package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SysUser;
import com.ffxl.cloud.model.SysUserExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SysUserService extends GenericService<SysUser, SysUserExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSysUser
     * @return 
     **/
    SysUser queryByModel(SysUser sysUser);

    /**
     * 用户列表
     * @param sysUser
     * @param page
     * @return
     */
	List<SysUser> queryPageList(SysUser sysUser, Page page);

	/**
	 * 
	 * @param id 用户id
	 * @param status 状态
	 * @return
	 */
	int updateStatus(String id, int status);

	/**
	 * 批量删除用户
	 * @param idList
	 * @return
	 */
	int deleteByIds(List<String> idList);

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	int update(SysUser user);
}