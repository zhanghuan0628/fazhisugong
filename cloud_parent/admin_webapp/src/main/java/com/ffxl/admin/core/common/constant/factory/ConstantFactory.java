package com.ffxl.admin.core.common.constant.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.ffxl.admin.core.common.constant.cache.Cache;
import com.ffxl.admin.core.common.constant.cache.CacheKey;
import com.ffxl.admin.core.log.LogObjectHolder;
import com.ffxl.admin.core.util.ApplicationContextUtils;
import com.ffxl.cloud.model.Dictionary;
import com.ffxl.cloud.model.Menu;
import com.ffxl.cloud.model.Role;
import com.ffxl.cloud.model.SysUser;
import com.ffxl.cloud.service.DictionaryService;
import com.ffxl.cloud.service.MenuService;
import com.ffxl.cloud.service.RoleService;
import com.ffxl.cloud.service.SysUserService;
import com.ffxl.platform.constant.status.MenuStatus;
import com.ffxl.platform.constant.status.SysUserStatus;
import com.ffxl.platform.core.node.MenuNode;
import com.ffxl.platform.core.support.StrKit;
import com.ffxl.platform.util.Convert;
import com.ffxl.platform.util.ToolUtil;

/**
 * 常量的生产工厂
 * @author jiawei
 * 2018年6月28日
 */
@Component
public class ConstantFactory implements IConstantFactory {
    
    @Autowired
    private RoleService roleService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private DictionaryService dictionaryService;

    public static IConstantFactory me() {
        return ApplicationContextUtils.getBean(IConstantFactory.class);
    }

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    @Override
    public String getUserNameById(String userId) {
        SysUser user = userService.selectByPrimaryKey(userId);
        if (user != null) {
            return user.getUserName();
        } else {
            return "--";
        }
    }
    
    /**
     * 获取被缓存的对象(用户删除业务)
     */
    @Override
    public String getCacheObject(String para) {
        return LogObjectHolder.me().get().toString();
    }

    
    /**
     * 获取性别名称
     */
    @Override
    public String getSexName(String sex) {
        return getDictsByName("性别", sex);
    }

    /**
     * 获取用户登录状态
     */
    @Override
    public String getStatusName(Integer status) {
        return SysUserStatus.valueOf(status);
    }

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    @Override
    public String getUserAccountById(String userId) {
        SysUser user = userService.selectByPrimaryKey(userId);
        if (user != null) {
            return user.getLoginName();
        } else {
            return "--";
        }
    }

    /**
     * 通过角色ids获取角色名称
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.ROLES_NAME + "'+#roleIds")
    public String getRoleName(String roleIds) {
        String[] roles = Convert.toStrArray(roleIds);
        StringBuilder sb = new StringBuilder();
        for (String role : roles) {
            Role roleObj = roleService.selectByPrimaryKey(role);
            if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
                sb.append(roleObj.getName()).append(",");
            }
        }
        return StrKit.removeSuffix(sb.toString(), ",");
    }

    /**
     * 通过角色id获取角色名称
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    public String getSingleRoleName(String roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            return "--";
        }
        Role roleObj = roleService.selectByPrimaryKey(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    /**
     * 通过角色id获取角色英文名称
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleId")
    public String getSingleRoleTip(String roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            return "--";
        }
        Role roleObj = roleService.selectByPrimaryKey(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getTips();
        }
        return "";
    }
    
    /**
     * 通过角色ids获取菜单
     */
    @Override
    public List<MenuNode> getUserMenus(List<String> roleIds) {
        List<MenuNode> menus = menuService.getMenusByRoleIds(roleIds);
        List<MenuNode> titles = MenuNode.buildTitle(menus);
        return titles;
    }

    /**
     * 获取菜单的名称们(多个)
     */
    @Override
    public String getMenuNames(String menuIds) {
        String[] menus = Convert.toStrArray(menuIds);
        StringBuilder sb = new StringBuilder();
        for (String menu : menus) {
            Menu menuObj = menuService.selectByPrimaryKey(menu);
            if (ToolUtil.isNotEmpty(menuObj) && ToolUtil.isNotEmpty(menuObj.getName())) {
                sb.append(menuObj.getName()).append(",");
            }
        }
        return StrKit.removeSuffix(sb.toString(), ",");
    }

    /**
     * 获取菜单名称
     */
    @Override
    public String getMenuName(String menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            return "";
        } else {
            Menu menu = menuService.selectByPrimaryKey(menuId);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    /**
     * 获取菜单名称通过编号
     */
    @Override
    public String getMenuNameByCode(String code) {
        if (ToolUtil.isEmpty(code)) {
            return "";
        } else {
            Menu param = new Menu();
            param.setCode(code);
            Menu menu = menuService.queryByModel(param);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }
    
    /**
     * 获取菜单状态
     */
    @Override
    public String getMenuStatusName(Integer status) {
        return MenuStatus.valueOf(status);
    }

    /**
     * 获取字典名称
     */
    @Override
    public String getDictName(String dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        } else {
            Dictionary dict = dictionaryService.selectByPrimaryKey(dictId);
            if (dict == null) {
                return "";
            } else {
                return dict.getName();
            }
        }
    }

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    @Override
    public String getDictsByName(String name, String val) {
        Dictionary temp = new Dictionary();
        temp.setName(name);
        Dictionary dict = dictionaryService.queryByModel(temp);
        if (dict == null) {
            return "";
        } else {
            Dictionary qm = new Dictionary();
            qm.setPid(dict.getId());
            List<Dictionary> dicts = dictionaryService.queryListByPid(qm);
            for (Dictionary item : dicts) {
                if (item.getNum() != null && item.getNum().equals(val)) {
                    return item.getName();
                }
            }
            return "";
        }
    }

    /**
     * 查询字典
     */
    @Override
    public List<Dictionary> findInDict(String id) {
        if (ToolUtil.isEmpty(id)) {
            return null;
        } else {
            Dictionary qm = new Dictionary();
            qm.setPid(id);
            List<Dictionary> dicts = dictionaryService.queryListByPid(qm);
            if (dicts == null || dicts.size() == 0) {
                return null;
            } else {
                return dicts;
            }
        }
    }
   
    /**
     * 获取子部门id
     */
    @Override
    public List<String> getSubDeptId(String deptid) {
//        Wrapper<Dept> wrapper = new EntityWrapper<>();
//        wrapper = wrapper.like("pids", "%[" + deptid + "]%");
//        List<Dept> depts = this.deptMapper.selectList(wrapper);
//
        ArrayList<String> deptids = new ArrayList<>();
//
//        if(depts != null && depts.size() > 0){
//            for (Dept dept : depts) {
//                deptids.add(dept.getId());
//            }
//        }
//
        return deptids;
    }

    /**
     * 获取所有父部门id
     */
    @Override
    public List<String> getParentDeptIds(String deptid) {
//        Dept dept = deptMapper.selectById(deptid);
//        String pids = dept.getPids();
//        String[] split = pids.split(",");
        ArrayList<String> parentDeptIds = new ArrayList<>();
//        for (String s : split) {
//            parentDeptIds.add(String.valueOf(StrKit.removeSuffix(StrKit.removePrefix(s, "["), "]")));
//        }
        return parentDeptIds;
    }
    
    /**
     * 获取部门名称
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
    public String getDeptName(String deptId) {
//        Dept dept = deptMapper.selectById(deptId);
//        if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getFullname())) {
//            return dept.getFullname();
//        }
        return "";
    }

    /**
     * 获取通知标题
     */
    @Override
    public String getNoticeTitle(String dictId) {
//        if (ToolUtil.isEmpty(dictId)) {
//            return "";
//        } else {
//            Notice notice = noticeMapper.selectById(dictId);
//            if (notice == null) {
//                return "";
//            } else {
//                return notice.getTitle();
//            }
//        }
        return "未完待续";
    }

}
