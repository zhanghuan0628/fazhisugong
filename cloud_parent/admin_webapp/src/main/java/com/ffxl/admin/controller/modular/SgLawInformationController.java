package com.ffxl.admin.controller.modular;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.admin.core.common.annotion.BussinessLog;
import com.ffxl.admin.core.common.constant.dictmap.SgLawDic;
import com.ffxl.admin.core.log.LogObjectHolder;
import com.ffxl.admin.core.shiro.ShiroKit;
import com.ffxl.admin.core.shiro.ShiroUser;
import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.service.SgLawService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.exception.BusinessException;
import com.ffxl.platform.util.DateUtil;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

/**
 * 法治动态
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sg_law_information")
public class SgLawInformationController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SgLawInformationController.class);
	@Autowired
	private SgLawService sgLawService;
	
	private static String PREFIX = "/fzsg/law_information/";
	/**
     * 跳转到法治动态的页面
     */
    @RequestMapping("/law_information_list")
    public String index() {
        return PREFIX + "law_information_list.html";
    }
    /**
     * 查询法治动态列表
     */
    @RequestMapping("/law_information_pageList")
    @ResponseBody
    public JsonResult list(DataTablesUtil dataTables,Page page,SgLaw sgLaw) {
    	if(!StringUtil.isEmpty(sgLaw.getTitle3())){
    		sgLaw.setTitle(sgLaw.getTitle3());
    	}
    	sgLaw.setCategory("law_information");
    	page = this.getPageInfo(page,dataTables);
    	List<SgLaw> dataList = sgLawService.queryPageList(sgLaw,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 上下架
     */
    @RequestMapping("/updateStatus")
    @BussinessLog(value = "上下架法治动态", key = "id", dict = SgLawDic.class)
    @ResponseBody
    public JsonResult updateStatus(String ids,String state){
    	if (StringUtil.isEmpty(ids)) {
    		return new JsonResult(Message.M4002);
        }
    	int ret = -1;
    	String[] idArray = ids.split(",");
    	for(String id:idArray ){
    		SgLaw record = new SgLaw();
    		record.setId(id);
    		record.setStatus(state);
    		ret = sgLawService.updateByPrimaryKeySelective(record);
    	}
    	if(ret >0){
       	 return new JsonResult(Message.M2000);
       }else{
       	 return new JsonResult(Message.M5000);
       }
    	
    }
    /**
     * 删除
     */
    @RequestMapping("/del_law_information")
    @BussinessLog(value = "删除法治动态", key = "id", dict = SgLawDic.class)
    @ResponseBody
    public JsonResult dellawInformation(String ids){
    	if (StringUtil.isEmpty(ids)) {
    		return new JsonResult(Message.M4002);
        }
    	int ret = -1;
    	String[] idArray = ids.split(",");
    	for(String id:idArray ){
    		ret = sgLawService.deleteByPrimaryKey(id);
    	}
    	if(ret >0){
       	 return new JsonResult(Message.M2000);
       }else{
       	 return new JsonResult(Message.M5000);
       }
    	
    }
    /**
     * 跳转到查看法治动态新增的页面
     */
    @RequestMapping("/law_information_add")
    public String lawInformationAdd(Model model) {
    	Date currentTime = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	String dateString = formatter.format(currentTime);
    	model.addAttribute("dateString", dateString);
        return PREFIX + "law_information_add.html";
    }
    /**
     * 跳转到查看法治动态修改的页面
     */
    @RequestMapping("/law_information_edit")
    public String lawInformationEdit(String id,Model model) {
    	if (StringUtil.isEmpty(id)) {
            throw new BusinessException(Message.M6002);
        }
    	SgLaw user = sgLawService.selectByPrimaryKey(id);
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	String dateString = formatter.format(user.getCreateDate());
    	user.setCreateTime(dateString);
        model.addAttribute("info", user);
        Date currentTime = new Date();
        String dateS = formatter.format(currentTime);
        model.addAttribute("dateString", dateS);
        LogObjectHolder.me().set(user);
        return PREFIX + "law_information_edit.html";
    }
    /**
     * 校验title是否存在
     * @param loginName
     * @return
     */
    @ResponseBody
    @RequestMapping("/check")
    public Boolean check(String title,String category) {
        // 判断账号是否重复
    	SgLaw model = new SgLaw();
        model.setTitle(title);
        model.setCategory(category);
        SgLaw theUser = sgLawService.queryByModel(model);
        if (theUser != null) {
            return false;
        }else{
            return true;
        }
    }
    /**
     * 新增
     * @param sgLaw
     * @param session
     * @return
     */
    @ResponseBody
    @BussinessLog(value = "新增法治动态", key = "id", dict = SgLawDic.class)
    @RequestMapping("/add")
    public JsonResult add(SgLaw sgLaw){
    	int i = -1;
    	SgLaw sl = new SgLaw();
    	sl.setId(UUIDUtil.getUUID());
    	sl.setCategory(sgLaw.getCategory());
    	sl.setModifyDate(new Date());
    	Date d = DateUtil.parseDate(sgLaw.getCreateTime());
    	sl.setCreateDate(d);
    	sl.setTitle(sgLaw.getTitle());
    	sl.setStatus("no_publish");
    	sl.setImgUrl(sgLaw.getImgUrl());
    	sl.setStatus(sgLaw.getStatus());
    	ShiroUser shiroUser = ShiroKit.getUser();
    	String userId = shiroUser.getId();
    	sl.setCreateBy(userId);
    	sl.setModifyBy(userId);
    	sl.setAuthor(sgLaw.getAuthor());
    	if(sgLaw.getType().equals("text")){
    		sl.setContent(sgLaw.getContent());
    		sl.setType("text");
    		i = sgLawService.insertSelective(sl);
    	}else{
    		sl.setContent(sgLaw.getContent());
    		sl.setNum(sgLaw.getNum());
    		sl.setType("video");
    		sl.setVideoUrl(sgLaw.getVideoUrl());
    		i= sgLawService.insertSelective(sl);
    	}
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    	
    	
    }
    /**
     * 修改
     * @param sgLaw
     * @param session
     * @return
     */
    @ResponseBody
    @BussinessLog(value = "修改法治动态", key = "id", dict = SgLawDic.class)
    @RequestMapping("/edit")
    public JsonResult edit(SgLaw sgLaw){
    	int i = -1;
    	SgLaw sl = new SgLaw();
    	sl.setId(sgLaw.getId());
    	sl.setCategory(sgLaw.getCategory());
    	sl.setModifyDate(new Date());
    	sl.setTitle(sgLaw.getTitle());
    	sl.setImgUrl(sgLaw.getImgUrl());
    	sl.setStatus(sgLaw.getStatus());
    	ShiroUser shiroUser = ShiroKit.getUser();
    	sl.setAuthor(sgLaw.getAuthor());
    	String userId = shiroUser.getId();
    	sl.setModifyBy(userId);
    	Date d = DateUtil.parseDate(sgLaw.getCreateTime());
    	sl.setCreateDate(d);
    	if(sgLaw.getType().equals("text")){
    		sl.setContent(sgLaw.getContent());
    		sl.setType("text");
    		i = sgLawService.updateByPrimaryKeySelective(sl);
    	}else{
    		sl.setContent(sgLaw.getContent());
    		sl.setNum(sgLaw.getNum());
    		sl.setVideoUrl(sgLaw.getVideoUrl());
    		sl.setType("video");
    		i= sgLawService.updateByPrimaryKeySelective(sl);
    	}
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    	
    }
}
