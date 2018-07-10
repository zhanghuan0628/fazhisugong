package com.ffxl.cloud.service;

import com.ffxl.cloud.model.SysMenu;
import com.ffxl.cloud.model.SysMenuExample;
import com.ffxl.platform.core.GenericService;

 /**
 * Generate By MBG 
 **/
public interface SysMenuService extends GenericService<SysMenu, SysMenuExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSysMenu
     * @return 
     **/
    SysMenu queryByModel(SysMenu sysMenu);
}