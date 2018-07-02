package com.ffxl.cloud.service;

import com.ffxl.cloud.model.Role;
import com.ffxl.cloud.model.RoleExample;
import com.ffxl.platform.core.GenericService;

 /**
 * Generate By MBG 
 **/
public interface RoleService extends GenericService<Role, RoleExample, String> {
     /**
     * According to the model information query object  
     * @param BaseRole
     * @return 
     **/
    Role queryByModel(Role role);
}