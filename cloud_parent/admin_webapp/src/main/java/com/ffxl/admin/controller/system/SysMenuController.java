package com.ffxl.admin.controller.system;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.admin.core.common.annotion.BussinessLog;
import com.ffxl.admin.core.common.constant.dictmap.SysMenuDic;
import com.ffxl.admin.core.common.constant.factory.ConstantFactory;
import com.ffxl.cloud.model.SysMenu;
import com.ffxl.cloud.model.SysMenuExample;
import com.ffxl.cloud.model.SysRoleMenuRelExample;
import com.ffxl.cloud.model.base.BaseSysMenuExample.Criteria;
import com.ffxl.cloud.service.SysMenuService;
import com.ffxl.cloud.service.SysRoleMenuRelService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.node.ZTreeNode;
import com.ffxl.platform.util.StringUtil;

/**
 * 菜单
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sys_menu")
public class SysMenuController extends BaseController{
 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	private static String PREFIX = "/system/sys_menu/";
	 
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private SysRoleMenuRelService sysRoleMenuRelService;
	 /**
	 * 跳转到菜单列表列表页面
	 */
    @RequestMapping("/sys_menu_list")
    public String index() {
        return PREFIX + "menu_list.html";
    }
    /**
     * 查询菜单列表
     */
    @RequestMapping("/menu_pageList")
    @ResponseBody
    public  List<SysMenu> list(SysMenu sysMenu) {
        List<SysMenu> dataList = sysMenuService.queryPageList(sysMenu);
        for(SysMenu s:dataList){
        	if(s.getMenu()==true){
        		s.setMenuName("是");
        	}else{
        		s.setMenuName("否");
        	}
        	if(s.getStatus()==1){
        		s.setState("启用");
        	}else{
        		s.setState("禁用");
        	}
        }
        return dataList;

    }
	 /**
     * 获取角色列表
     */
    @RequestMapping(value = "/menuTreeListByRoleId")
    @ResponseBody
    public List<ZTreeNode> menuTreeListByRoleId(String roleId) {
        List<SysMenu> menuIds = sysMenuService.getMenuIdsByRoleId(roleId);
        if (menuIds != null && menuIds.size() > 0) {
        	List<ZTreeNode> roleTreeListByUserId = sysMenuService.menuTreeListByMenuIds(menuIds);
            return roleTreeListByUserId;
        } else {
        	List<ZTreeNode> roleTreeList = sysMenuService.menuTreeList();
            return roleTreeList;
        }
    }
    /**
     * 跳转到菜单列表列表页面
     */
    @RequestMapping(value = "/menu_add")
    public String menuAdd() {
        return PREFIX + "menu_add.html";
    }
    
    /**
     * 跳转到菜单详情列表页面
     */
    @RequestMapping(value = "/menu_edit")
    public String menuEdit(String menuId, Model model) {
        SysMenu menu = sysMenuService.selectByPrimaryKey(menuId);

        //获取父级菜单的id
        SysMenuExample example = new SysMenuExample();
        Criteria c= example.createCriteria();
        c.andCodeEqualTo(menu.getPcode());
        List<SysMenu> list = sysMenuService.selectByExample(example);
        //如果父级是顶级菜单
        if (list != null && list.size()>0) {
        	//设置父级菜单的code为父级菜单的id
        	SysMenu pMenu = list.get(0);
            menu.setPcode(String.valueOf(pMenu.getId()));
            menu.setPname(pMenu.getName());
            
        } else {
        	menu.setPcode("0");
        }
        if(!StringUtil.isEmpty(menu.getIcon())){
        	String ic = menu.getIcon();
            String icon = ic.substring(1) ;
            menu.setIcon(icon);
    	}
        model.addAttribute("menu", menu);
        return PREFIX + "menu_edit.html";
    }
    /**
     * 获取菜单列表(选择父级菜单用)
     */
    @RequestMapping(value = "/selectMenuTreeList")
    @ResponseBody
    public List<ZTreeNode> selectMenuTreeList() {
        List<ZTreeNode> roleTreeList = sysMenuService.menuTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }
    /**
     * 校验code是否存在
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/check")
    public Boolean check(String code,String url) {
    	SysMenu model = new SysMenu();
        model.setCode(code);
        model.setUrl(url);
        SysMenu c = sysMenuService.queryByModel(model);
        if (c != null) {
            return false;
        }else{
            return true;
        }
    }
    /**
     * 修改菜单
     */
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修改菜单", key = "id", dict = SysMenuDic.class)
    @ResponseBody
    public JsonResult edit(SysMenu menu) {
    	if(!StringUtil.isEmpty(menu.getIcon())){
    		menu.setIcon("&"+menu.getIcon());
    	}
    	SysMenu s = sysMenuService.selectByPrimaryKey(menu.getPcode());
    	if(s != null){
    		String l = s.getLevels();
        	if(StringUtil.isEmpty(l)){
        		l = "0";
        	}
        	int le = Integer.parseInt(l);
        	menu.setLevels((le+1)+"");
        	menu.setPcode(s.getCode());
    	}
        int i = sysMenuService.updateByPrimaryKeySelective(menu);
        if (i > 0) {
            return new JsonResult(Message.M2000);
        }else{
        	return new JsonResult(Message.M5000);
        }
    }
    /**
     * 新增菜单
     */
    @RequestMapping(value = "/add")
    @BussinessLog(value = "新增菜单", key = "id", dict = SysMenuDic.class)
    @ResponseBody
    public JsonResult add(SysMenu menu) {
        //判断是否存在该编号
        String existedMenuName = ConstantFactory.me().getMenuNameByCode(menu.getCode());
        if (!StringUtil.isEmpty(existedMenuName)) {
        	JsonResult js = new JsonResult();
        	js.setCode("1");
        	js.setMessage("菜单编号已经存在");
        	return js;
        }
        if(!StringUtil.isEmpty(menu.getIcon())){
    		menu.setIcon("&"+menu.getIcon());
    	}
        SysMenu s = sysMenuService.selectByPrimaryKey(menu.getPcode());
        String l = "";
        if(s!=null){
        	menu.setPcode(s.getCode());
        	l = s.getLevels();
        	if(StringUtil.isEmpty(l)){
        		l = "0";
        	}
        	int le = Integer.parseInt(l);
        	menu.setLevels((le+1)+"");
        }else{
        	menu.setPcode(0+"");
        	menu.setLevels(1+"");
        }
        int idd = sysMenuService.selectMaxId();
        menu.setId(idd+1+"");
        menu.setStatus(1);
        /*int num = sysMenuService.selectMaxNum();
        menu.setNum(num+1);*/
        int i = sysMenuService.insertSelective(menu);
        if (i > 0) {
            return new JsonResult(Message.M2000);
        }else{
        	return new JsonResult(Message.M5000);
        }
    }
    /**
     * 删除
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/remove")
    @BussinessLog(value = "删除菜单", key = "id", dict = SysMenuDic.class)
    @ResponseBody
    public JsonResult remove(String menuId){
    	SysRoleMenuRelExample example = new SysRoleMenuRelExample();
        com.ffxl.cloud.model.base.BaseSysRoleMenuRelExample.Criteria c= example.createCriteria();
        c.andMenuIdEqualTo(menuId);
    	int i = sysRoleMenuRelService.deleteByExample(example);
    	i = sysMenuService.deleteByPrimaryKey(menuId);
    	if (i > 0) {
            return new JsonResult(Message.M2000);
        }else{
        	return new JsonResult(Message.M5000);
        }
    	
    }
    /**
     * 跳转到菜单列表列表页面
     */
    @RequestMapping(value = "/add_icon")
    public String addIcon() {
        return PREFIX + "add_icon.html";
    }
}
