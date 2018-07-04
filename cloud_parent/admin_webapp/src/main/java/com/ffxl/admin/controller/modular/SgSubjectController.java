package com.ffxl.admin.controller.modular;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.admin.core.common.annotion.BussinessLog;
import com.ffxl.admin.core.common.constant.dictmap.SgLawDic;
import com.ffxl.admin.core.common.constant.dictmap.SgSubjectDic;
import com.ffxl.admin.core.log.LogObjectHolder;
import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgSubject;
import com.ffxl.cloud.service.SgSubjectService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.exception.BusinessException;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

/**
 * 法官题目管理
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sg_subject")
public class SgSubjectController extends BaseController{
	@Autowired
	private SgSubjectService sgSubjectService;
	
	private static String PREFIX = "/fzsg/sg_subject/";
	/**
     * 跳转到法官题目的页面
     */
    @RequestMapping("/subject_list")
    public String index() {
        return PREFIX + "subject_list.html";
    }
    /**
     * 查询法官题目列表
     */
    @RequestMapping("/subject_pageList")
    @ResponseBody
    public JsonResult list(DataTablesUtil dataTables,Page page,SgSubject sgSubject) {
    	page = this.getPageInfo(page,dataTables);
    	List<SgSubject> dataList = sgSubjectService.queryPageList(sgSubject,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 跳转到查看法官题目新增的页面
     */
    @RequestMapping("/subject_add")
    public String subjectAdd(Model model) {
        return PREFIX + "subject_add.html";
    }
    /**
     * 跳转到查看法官题目修改的页面
     */
    @RequestMapping("/subject_edit")
    public String subjectEdit(String id,Model model) {
    	if (StringUtil.isEmpty(id)) {
            throw new BusinessException(Message.M6002);
        }
    	SgSubject info = sgSubjectService.selectByPrimaryKey(id);
    	
    	JSONObject jsStr = JSONObject.parseObject(info.getQuestionJson());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("qstn", jsStr.get("qstn"));
        map.put("optn", jsStr.get("optn"));
        map.put("id", info.getId());
        
        model.addAttribute("info", map);
        LogObjectHolder.me().set(info);
        return PREFIX + "subject_edit.html";
    }
    /**
     * 新增
     * @param sgLaw
     * @param session
     * @return
     */
    @ResponseBody
    @BussinessLog(value = "新增法官题目", key = "id", dict = SgSubjectDic.class)
    @RequestMapping("/add")
    public JsonResult add(SgSubject sgSubject){
    	sgSubject.setId(UUIDUtil.getUUID());
    	sgSubject.setCreateDate(new Date());
    	sgSubject.setModifyDate(new Date());
    	int i = sgSubjectService.insertSelective(sgSubject);
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
    @BussinessLog(value = "修改法官题目", key = "id", dict = SgSubjectDic.class)
    @RequestMapping("/edit")
    public JsonResult edit(SgSubject sgSubject){
    	sgSubject.setModifyDate(new Date());
    	int i = sgSubjectService.updateByPrimaryKeySelective(sgSubject);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    }
    /**
     * 删除
     * @param ids
     * @return
     */
   @RequestMapping("/del_subject")
   @BussinessLog(value = "删除法官题目", key = "id", dict = SgSubjectDic.class)
   @ResponseBody
   public JsonResult del(String ids){
   	if (StringUtil.isEmpty(ids)) {
   		return new JsonResult(Message.M4002);
       }
   	int ret = -1;
   	String[] idArray = ids.split(",");
   	for(String id:idArray ){
   		ret = sgSubjectService.deleteByPrimaryKey(id);
   	}
   	if(ret >0){
      	 return new JsonResult(Message.M2000);
      }else{
      	 return new JsonResult(Message.M5000);
      }
   	
   }
}
