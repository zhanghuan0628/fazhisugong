package com.ffxl.admin.controller.modular;

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
import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgLawExample;
import com.ffxl.cloud.model.base.BaseSgLawExample.Criteria;
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
 * 苏供法宝库
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sg_law_magic")
public class SgLawMagicController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SgLawMagicController.class);
	
	@Autowired
	private SgLawService sgLawService;
	
	private static String PREFIX = "/fzsg/law/";
	/**
     * 跳转到苏供法宝的页面
     */
    @RequestMapping("/law_magic_list")
    public String index() {
        return PREFIX + "lawMagicList.html";
    }
    /**
     * 查询苏供法宝列表
     */
    @RequestMapping("/lawMagicList")
    @ResponseBody
    public JsonResult list(DataTablesUtil dataTables,Page page,SgLaw sgLaw) {
    	sgLaw.setCategory("law_magic");
    	page = this.getPageInfo(page,dataTables);
    	List<SgLaw> dataList = sgLawService.queryPageList(sgLaw,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 上下架
     */
    @RequestMapping("/updateStatus")
    @BussinessLog(value = "上下架法律法宝", key = "id", dict = SgLawDic.class)
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
    @RequestMapping("/delLawMagic")
    @BussinessLog(value = "删除法律法宝", key = "id", dict = SgLawDic.class)
    @ResponseBody
    public JsonResult delLawMagic(String ids){
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
     * 上移下移
     */
    @RequestMapping("/push")
    @BussinessLog(value = "上移下移法律法宝", key = "id", dict = SgLawDic.class)
    @ResponseBody
    public JsonResult push(String id,String state,String code,int sort){
    	int i = -1;
    	SgLaw record = new SgLaw();
    	SgLawExample example = new SgLawExample();
        Criteria c= example.createCriteria();
        c.andIdEqualTo(id);
        if(state.equals("up")){
            if(sort > 1){
            	SgLaw bb = new SgLaw();
                bb.setNum(sort);
                bb.setSort(sort - 1);
                bb.setCategoryCode(code);
                bb.setCategory("law_magic");
                i = sgLawService.updateSort(bb);
                record.setNum(sort - 1);
                i = sgLawService.updateByExampleSelective(record, example);
            }
        }else{
        	String category = "law_magic";
            int s = sgLawService.selectMaxSort(code,code,category);
            if(s > sort){
            	SgLaw bb = new SgLaw();
                bb.setNum(sort);
                bb.setSort(sort + 1);
                bb.setCategoryCode(code);
                bb.setCategory("law_magic");
                i = sgLawService.updateSort(bb);
                record.setNum(sort + 1);
                i = sgLawService.updateByExampleSelective(record, example);
            }
        }
        
        if(i > 0){
            return new JsonResult(Message.M2000);
        }
        return new JsonResult(Message.M5000);
    	
    }
    /**
     * 跳转到查看苏供法宝新增的页面
     */
    @RequestMapping("/law_magic_add")
    public String lawMagicAdd() {
        return PREFIX + "law_magic_add.html";
    }
    /**
     * 跳转到查看苏供法宝修改的页面
     */
    @RequestMapping("/law_magic_edit")
    public String lawMagicEdit(String id,Model model) {
    	if (StringUtil.isEmpty(id)) {
            throw new BusinessException(Message.M6002);
        }
    	SgLaw user = sgLawService.selectByPrimaryKey(id);
        model.addAttribute("info", user);
        LogObjectHolder.me().set(user);
        return PREFIX + "law_magic_edit.html";
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
    @BussinessLog(value = "新增法宝", key = "id", dict = SgLawDic.class)
    @RequestMapping("/add")
    public JsonResult add(SgLaw sgLaw,HttpSession session){
    	int s = sgLawService.selectMaxSort(sgLaw.getCategoryCode(),sgLaw.getCategoryCode(),sgLaw.getCategory());
    	sgLaw.setId(UUIDUtil.getUUID());
    	sgLaw.setCreateDate(new Date());
    	sgLaw.setStatus("no_publish");
    	sgLaw.setModifyDate(new Date());
    	sgLaw.setNum(s+1);
    	int i= sgLawService.insertSelective(sgLaw);
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
    @BussinessLog(value = "修改法律法宝", key = "id", dict = SgLawDic.class)
    @RequestMapping("/edit")
    public JsonResult edit(SgLaw sgLaw,HttpSession session){
    	sgLaw.setModifyDate(new Date());
    	int i= sgLawService.updateByPrimaryKeySelective(sgLaw);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    	
    }
    /**
     * 跳转到查看苏供法宝详情的页面
     */
    @RequestMapping("/law_magic_detail")
    public String lawMagicDetail(String id,String title,Model model) {
    	model.addAttribute("id", id);
    	model.addAttribute("title", title);
        return PREFIX + "law_magic_detail.html";
    }
    /**
     * 跳转到查看苏供法宝详情新增的页面
     */
    @RequestMapping("/law_magic_detail_add")
    public String lawMagicDetailAdd(String id,String title,Model model) {
    	String time = DateUtil.formatStandardDatetime(new Date());
    	model.addAttribute("time", time);
    	model.addAttribute("id", id);
    	model.addAttribute("title", title);
    	model.addAttribute("info", new SgLaw());
        return PREFIX + "law_magic_detail_add.html";
    }
    /**
     * 跳转到查看苏供法宝详情修改的页面
     */
    @RequestMapping("/law_magic_detail_edit")
    public String lawMagicDetailEdit(String id,String title,Model model) {
    	if (StringUtil.isEmpty(id)) {
            throw new BusinessException(Message.M6002);
        }
    	SgLaw sgLaw = sgLawService.selectByPrimaryKey(id);
    	SgLaw s = sgLawService.selectByPrimaryKey(sgLaw.getCategoryCode());
    	String time = DateUtil.formatStandardDatetime(sgLaw.getModifyDate());
    	sgLaw.setCreateTime(time);
        model.addAttribute("info", sgLaw);
        model.addAttribute("title", s.getTitle());
        LogObjectHolder.me().set(sgLaw);
        return PREFIX + "law_magic_detail_edit.html";
    }
    /**
     * 跳转到查看苏供法宝详情修改的页面
     */
    @RequestMapping("/law_magic_content")
    public String lawMagicContent(String id,Model model) {
    	if (StringUtil.isEmpty(id)) {
            throw new BusinessException(Message.M6002);
        }
    	SgLaw sgLaw = sgLawService.selectByPrimaryKey(id);
        model.addAttribute("info", sgLaw);
        LogObjectHolder.me().set(sgLaw);
        return PREFIX + "law_magic_content.html";
    }
    
}
