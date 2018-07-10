package com.ffxl.cloud.service;

import com.ffxl.cloud.model.SysRoleMenuRel;
import com.ffxl.cloud.model.SysRoleMenuRelExample;
import com.ffxl.platform.core.GenericService;

 /**
 * Generate By MBG 
 **/
public interface SysRoleMenuRelService extends GenericService<SysRoleMenuRel, SysRoleMenuRelExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSysRoleMenuRel
     * @return 
     **/
    SysRoleMenuRel queryByModel(SysRoleMenuRel sysRoleMenuRel);
}