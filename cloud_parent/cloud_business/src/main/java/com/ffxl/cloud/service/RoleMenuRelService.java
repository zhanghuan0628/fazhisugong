package com.ffxl.cloud.service;

import com.ffxl.cloud.model.RoleMenuRel;
import com.ffxl.cloud.model.RoleMenuRelExample;
import com.ffxl.platform.core.GenericService;

 /**
 * Generate By MBG 
 **/
public interface RoleMenuRelService extends GenericService<RoleMenuRel, RoleMenuRelExample, String> {
     /**
     * According to the model information query object  
     * @param BaseRoleMenuRel
     * @return 
     **/
    RoleMenuRel queryByModel(RoleMenuRel roleMenuRel);
}