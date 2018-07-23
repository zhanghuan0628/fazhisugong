package com.ffxl.admin.controller.modular;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.ffxl.admin.core.common.constant.dictmap.SgAskDic;
import com.ffxl.admin.core.common.constant.dictmap.SgLawDic;
import com.ffxl.admin.core.shiro.ShiroKit;
import com.ffxl.admin.core.shiro.ShiroUser;
import com.ffxl.cloud.model.SgAsk;
import com.ffxl.cloud.model.SgAskExample;
import com.ffxl.cloud.model.SgLawComment;
import com.ffxl.cloud.model.SgLawCommentExample;
import com.ffxl.cloud.model.SgUser;
import com.ffxl.cloud.model.base.BaseSgAskExample.Criteria;
import com.ffxl.cloud.service.SgAskService;
import com.ffxl.cloud.service.SgLawCommentService;
import com.ffxl.cloud.service.SgUserService;
import com.ffxl.cloud.service.impl.SysUserServiceImpl;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

/**
 * 咨询管理
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sg_ask")
public class SgAskController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);
	
	private static String PREFIX = "/fzsg/sg_ask/";
	@Autowired
	private SgAskService sgAskService;
	
	@Autowired
	private SgUserService sgUserService;
	
	@Autowired
	private SgLawCommentService sgLawCommentService;
	
	/**
     * 跳转到咨询管理列表的页面
     */
    @RequestMapping("/ask_list")
    public String index() {
        return PREFIX + "ask_list.html";
    }
	/**
     * 查询咨询管理列表
     */
    @RequestMapping("/ask_pageList")
    @ResponseBody
    public JsonResult userAskList(DataTablesUtil dataTables,Page page,SgAsk sgAsk) {
    	if(!StringUtil.isEmpty(sgAsk.getTitle1())){
    		sgAsk.setTitle(sgAsk.getTitle1());
    	}
    	page = this.getPageInfo(page,dataTables);
    	List<SgAsk> dataList = sgAskService.queryPageList(sgAsk,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 跳转到虚拟咨询管理的页面
     */
    @RequestMapping("/ask_add")
    public String askAdd(Model model) {
    	Date currentTime = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String dateString = formatter.format(currentTime);
    	model.addAttribute("dateString", dateString);
        return PREFIX + "ask_add.html";
    }
    /**
     * 跳转到虚拟咨询管理的页面
     */
    @RequestMapping("/ask_detail")
    public String askDetail(String id,Model model) {
    	SgAsk user = sgAskService.queryUserAsk(id);
        model.addAttribute("info", user);
        return PREFIX + "ask_detail.html";
    }
    /**
     * 跳转到回复的页面
     */
    @RequestMapping("/counselor_back")
    public String counselorBack(String id,Model model) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	String userId = shiroUser.getId();
        SgAsk user = sgAskService.queryUserAsk(id);
        SgLawCommentExample example = new SgLawCommentExample();
        com.ffxl.cloud.model.base.BaseSgLawCommentExample.Criteria c= example.createCriteria();
        c.andTopicIdEqualTo(id);
        c.andReplyUserIdEqualTo(userId);
        List<SgLawComment> list = sgLawCommentService.selectByExample(example);
        if(list != null && list.size() > 0){
        	 SgLawComment sc = list.get(0);
             user.setReplyContent(sc.getReplyContent());
             user.setCommentId(sc.getId());
        }
        model.addAttribute("info", user);
        return PREFIX + "counselor_back.html";
    }
    /**
     * 新增
     */
    @RequestMapping("/add")
    @BussinessLog(value = "新增虚拟咨询", key = "id", dict = SgAskDic.class)
    @ResponseBody
    public JsonResult add(SgAsk sgAsk) {
    	sgAsk.setId(UUIDUtil.getUUID());
    	sgAsk.setCreateDate(new Date());
    	sgAsk.setDummy(true);
    	int i = sgAskService.insertSelective(sgAsk);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    }
    /**
     * 跳转到虚拟用户的页面
     */
    @RequestMapping("/add_xnUser")
    public String addXNUser() {
        return PREFIX + "add_xnUser.html";
    }
    /**
     * 查询虚拟用户
     */
    @RequestMapping("/queryDummName")
    @ResponseBody
    public JsonResult queryDummName(String id) {
    	SgUser user = sgUserService.selectByPrimaryKey(id);
    	return new JsonResult(true,user);
       
    }
    /**
     * 删除
     */
    @RequestMapping("/del")
    @BussinessLog(value = "删除虚拟咨询", key = "id", dict = SgAskDic.class)
    @ResponseBody
    public JsonResult del(String ids){
    	if (StringUtil.isEmpty(ids)) {
    		return new JsonResult(Message.M4002);
        }
    	int ret = -1;
    	String[] idArray = ids.split(",");
    	for(String id:idArray ){
    		ret = sgAskService.deleteByPrimaryKey(id);
    	}
    	if(ret >0){
       	 return new JsonResult(Message.M2000);
       }else{
       	 return new JsonResult(Message.M5000);
       }
    	
    }
}
