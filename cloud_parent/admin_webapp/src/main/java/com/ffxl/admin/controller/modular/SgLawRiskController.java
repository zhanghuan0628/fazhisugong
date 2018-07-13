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
import com.ffxl.admin.core.common.constant.dictmap.SgLawCommentDic;
import com.ffxl.admin.core.common.constant.dictmap.SgLawDic;
import com.ffxl.admin.core.log.LogObjectHolder;
import com.ffxl.admin.core.shiro.ShiroKit;
import com.ffxl.admin.core.shiro.ShiroUser;
import com.ffxl.cloud.model.Dictionary;
import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgLawComment;
import com.ffxl.cloud.model.SgLawCommentExample;
import com.ffxl.cloud.model.SgLawExample;
import com.ffxl.cloud.model.base.BaseSgLawExample.Criteria;
import com.ffxl.cloud.service.DictionaryService;
import com.ffxl.cloud.service.SgLawCommentService;
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
 * 法律风险
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sg_law_risk")
public class SgLawRiskController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SgLawRiskController.class);
	@Autowired
	private SgLawService sgLawService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private SgLawCommentService sgLawCommentService;
	
	private static String PREFIX = "/fzsg/law_risk/";
	/**
     * 跳转到法律风险的页面
     */
    @RequestMapping("/law_risk_list")
    public String index(Model model) {
    	Dictionary dictionary = new Dictionary();
    	dictionary.setPid("4");
    	List<Dictionary> list = dictionaryService.queryListByPid(dictionary);
    	model.addAttribute("list", list);
        return PREFIX + "law_risk_list.html";
    }
    /**
     * 查询法律风险列表
     */
    @RequestMapping("/law_risk_pageList")
    @ResponseBody
    public JsonResult list(DataTablesUtil dataTables,Page page,SgLaw sgLaw) {
    	sgLaw.setCategory("law_risk");
    	page = this.getPageInfo(page,dataTables);
    	List<SgLaw> dataList = sgLawService.queryPageList(sgLaw,page);
    	for(SgLaw sl:dataList){
    		Dictionary dictionary = dictionaryService.selectByPrimaryKey(sl.getCategoryCode());
    		if(dictionary != null){
    			sl.setName(dictionary.getName());
    		}
    		
    	}
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 上下架
     */
    @RequestMapping("/updateStatus")
    @BussinessLog(value = "上下架法律风险", key = "id", dict = SgLawDic.class)
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
    @RequestMapping("/delLawRisk")
    @BussinessLog(value = "删除法律风险", key = "id", dict = SgLawDic.class)
    @ResponseBody
    public JsonResult delLawrisk(String ids){
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
    @BussinessLog(value = "上移下移法律风险", key = "id", dict = SgLawDic.class)
    @ResponseBody
    public JsonResult push(String id,String state,int sort){
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
                bb.setCategory("law_risk");
                i = sgLawService.updateSort(bb);
                record.setNum(sort - 1);
                i = sgLawService.updateByExampleSelective(record, example);
            }
        }else{
        	String category = "law_risk";
            int s = sgLawService.selectMaxSort(null,null,category);
            if(s > sort){
            	SgLaw bb = new SgLaw();
                bb.setNum(sort);
                bb.setSort(sort + 1);
                bb.setCategory("law_risk");
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
     * 跳转到查看法律风险新增的页面
     */
    @RequestMapping("/law_risk_add")
    public String lawriskAdd(Model model) {
    	Dictionary dictionary = new Dictionary();
    	dictionary.setPid("4");
    	List<Dictionary> list = dictionaryService.queryListByPid(dictionary);
    	Date currentTime = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String dateString = formatter.format(currentTime);
    	model.addAttribute("dateString", dateString);
    	model.addAttribute("list", list);
        return PREFIX + "law_risk_add.html";
    }
    /**
     * 跳转到查看法律风险修改的页面
     */
    @RequestMapping("/law_risk_edit")
    public String lawriskEdit(String id,Model model) {
    	if (StringUtil.isEmpty(id)) {
            throw new BusinessException(Message.M6002);
        }
    	SgLaw user = sgLawService.selectByPrimaryKey(id);
    	String time = DateUtil.formatStandardDatetime(user.getCreateDate());
    	user.setCreateTime(time);
    	Dictionary dictionary = new Dictionary();
    	dictionary.setPid("4");
    	List<Dictionary> list = dictionaryService.queryListByPid(dictionary);
    	model.addAttribute("list", list);
        model.addAttribute("info", user);
        LogObjectHolder.me().set(user);
        return PREFIX + "law_risk_edit.html";
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
    @BussinessLog(value = "新增法律风险", key = "id", dict = SgLawDic.class)
    @RequestMapping("/add")
    public JsonResult add(SgLaw sgLaw,HttpSession session){
    	int s = sgLawService.selectMaxSort(sgLaw.getCategoryCode(),sgLaw.getCategoryCode(),sgLaw.getCategory());
    	sgLaw.setId(UUIDUtil.getUUID());
    	sgLaw.setCreateDate(new Date());
    	sgLaw.setStatus("no_publish");
    	sgLaw.setModifyDate(new Date());
    	sgLaw.setNum(s+1);
    	ShiroUser shiroUser = ShiroKit.getUser();
    	String userId = shiroUser.getId();
    	sgLaw.setModifyBy(userId);
    	sgLaw.setCreateBy(userId);
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
    @BussinessLog(value = "修改法律风险", key = "id", dict = SgLawDic.class)
    @RequestMapping("/edit")
    public JsonResult edit(SgLaw sgLaw){
    	sgLaw.setModifyDate(new Date());
    	ShiroUser shiroUser = ShiroKit.getUser();
    	String userId = shiroUser.getId();
    	sgLaw.setModifyBy(userId);
    	int i= sgLawService.updateByPrimaryKeySelective(sgLaw);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    	
    }
    /**
     * 跳转到查看法律风险详情的页面
     */
    @RequestMapping("/law_risk_detail")
    public String lawriskDetail(String id,String title,Model model) {
    	model.addAttribute("id", id);
    	model.addAttribute("title", title);
        return PREFIX + "law_risk_detail.html";
    }
    /**
     * 跳转到查看法律风险详情新增的页面
     */
    @RequestMapping("/law_risk_detail_add")
    public String lawriskDetailAdd(String id,String title,Model model) {
    	model.addAttribute("id", id);
    	model.addAttribute("title", title);
    	model.addAttribute("info", new SgLaw());
        return PREFIX + "law_risk_detail_add.html";
    }
    /**
     * 跳转到查看法律风险详情修改的页面
     */
    @RequestMapping("/law_risk_detail_edit")
    public String lawriskDetailEdit(String id,String title,Model model) {
    	if (StringUtil.isEmpty(id)) {
            throw new BusinessException(Message.M6002);
        }
    	SgLaw sgLaw = sgLawService.selectByPrimaryKey(id);
    	SgLaw s = sgLawService.selectByPrimaryKey(sgLaw.getCategoryCode());
        model.addAttribute("info", sgLaw);
        model.addAttribute("title", s.getTitle());
        LogObjectHolder.me().set(sgLaw);
        return PREFIX + "law_risk_detail_edit.html";
    }
    /**
     * 跳转到查看法律风险详情修改的页面
     */
    @RequestMapping("/law_risk_content")
    public String lawriskContent(String id,Model model) {
    	if (StringUtil.isEmpty(id)) {
            throw new BusinessException(Message.M6002);
        }
    	SgLaw sgLaw = sgLawService.selectByPrimaryKey(id);
        model.addAttribute("info", sgLaw);
        LogObjectHolder.me().set(sgLaw);
        return PREFIX + "law_risk_content.html";
    }
    /**
     * 查询法律风险评论列表
     */
    @RequestMapping("/law_risk_comment")
    @ResponseBody
    public JsonResult law_risk_comment(DataTablesUtil dataTables,Page page,SgLawComment sgLawComment) {
    	page = this.getPageInfo(page,dataTables);
    	List<SgLawComment> dataList = sgLawCommentService.queryPageList(sgLawComment,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 通不通过
     */
    @RequestMapping("/updateState")
    @BussinessLog(value = "通不通过评论", key = "id", dict = SgLawCommentDic.class)
    @ResponseBody
    public JsonResult updateState(String ids,String state){
    	if (StringUtil.isEmpty(ids)) {
    		return new JsonResult(Message.M4002);
        }
    	int ret = -1;
    	String[] idArray = ids.split(",");
    	for(String id:idArray ){
    		SgLawComment record = new SgLawComment();
    		record.setId(id);
    		record.setState(state);
    		ret = sgLawCommentService.updateByPrimaryKeySelective(record);
    	}
    	if(ret >0){
       	 return new JsonResult(Message.M2000);
       }else{
       	 return new JsonResult(Message.M5000);
       }
    	
    }
    /**
     * 跳转到回复的页面
     */
    @RequestMapping("/counselor_back")
    public String counselorBack(String id,Model model) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	String userId = shiroUser.getId();
    	SgLawComment m = new SgLawComment();
    	m.setId(id);
    	List<SgLawComment> dataList = sgLawCommentService.queryPageList(m,null);
    	SgLawComment sgLawComment = dataList.get(0);
        SgLawCommentExample example = new SgLawCommentExample();
        com.ffxl.cloud.model.base.BaseSgLawCommentExample.Criteria c= example.createCriteria();
        c.andTopicIdEqualTo(id);
        c.andReplyUserIdEqualTo(userId);
        List<SgLawComment> list = sgLawCommentService.selectByExample(example);
        if(list != null && list.size() > 0){
        	 SgLawComment sc = list.get(0);
        	 sgLawComment.setReplyContent(sc.getReplyContent());
        	 sgLawComment.setReplyId(sc.getId());
        }
        model.addAttribute("info", sgLawComment);
        return PREFIX + "counselor_back.html";
    }
    /**
     * 修改咨询师回复
     * @param sgLaw
     * @param session
     * @return
     */
    @ResponseBody
    @BussinessLog(value = "修改咨询师回复", key = "id", dict = SgLawCommentDic.class)
    @RequestMapping("/edit_counselor_back")
    public JsonResult edit(SgLawComment sgLawComment,HttpSession session){
    	ShiroUser shiroUser = ShiroKit.getUser();
    	String userId = shiroUser.getId();
    	sgLawComment.setReplyUserId(userId);
    	sgLawComment.setReplyUserType("sys");
    	int i= sgLawCommentService.updateByPrimaryKeySelective(sgLawComment);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    	
    }
    /**
     * 删除
     */
    @RequestMapping("/delComment")
    @BussinessLog(value = "删除法律风险", key = "id", dict = SgLawDic.class)
    @ResponseBody
    public JsonResult delComment(String ids){
    	if (StringUtil.isEmpty(ids)) {
    		return new JsonResult(Message.M4002);
        }
    	int ret = -1;
    	String[] idArray = ids.split(",");
    	for(String id:idArray ){
    		ret = sgLawCommentService.deleteByPrimaryKey(id);
    	}
    	if(ret >0){
       	 return new JsonResult(Message.M2000);
       }else{
       	 return new JsonResult(Message.M5000);
       }
    	
    }
}
