package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SysRole;
import com.ffxl.cloud.model.SysRoleExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SysRoleService extends GenericService<SysRole, SysRoleExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSysRole
     * @return 
     **/
    SysRole queryByModel(SysRole sysRole);
    /**
     * 角色列表
     * @param sysRole
     * @param page
     * @return
     */
	List<SysRole> queryPageList(SysRole sysRole, Page page);
}