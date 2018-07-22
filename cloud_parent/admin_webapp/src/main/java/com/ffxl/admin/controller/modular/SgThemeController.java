package com.ffxl.admin.controller.modular;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.admin.core.common.annotion.BussinessLog;
import com.ffxl.admin.core.common.constant.dictmap.DictionaryDic;
import com.ffxl.admin.core.common.constant.dictmap.SgLawDic;
import com.ffxl.admin.core.common.constant.dictmap.SgThemeDic;
import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgTheme;
import com.ffxl.cloud.model.SgThemeAnswerLog;
import com.ffxl.cloud.model.SgThemeAnswerLogExample;
import com.ffxl.cloud.model.SgThemeAwardLog;
import com.ffxl.cloud.model.SgThemeAwardLogExample;
import com.ffxl.cloud.model.SgThemeExample;
import com.ffxl.cloud.model.base.BaseSgThemeExample.Criteria;
import com.ffxl.cloud.service.SgThemeAnswerLogService;
import com.ffxl.cloud.service.SgThemeAwardLogService;
import com.ffxl.cloud.service.SgThemeService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.DateUtil;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

/**
 * 我是法官
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sg_theme")
public class SgThemeController extends BaseController{
	@Autowired
	private SgThemeService sgThemeService;
	
	@Autowired
	private SgThemeAnswerLogService sgThemeAnswerLogService;
	
	@Autowired
	private SgThemeAwardLogService sgThemeAwardLogService;
	
	private static String PREFIX = "/fzsg/sg_theme/";
	/**
     * 跳转到答题人数管理的页面
     */
    @RequestMapping("/answer_person_num")
    public String index() {
        return PREFIX + "answer_person_num.html";
    }
    /**
     * 查询答题人数管理列表
     */
    @RequestMapping("/answer_num_pageList")
    @ResponseBody
    public JsonResult list(DataTablesUtil dataTables,Page page,SgTheme sgTheme) {
    	page = this.getPageInfo(page,dataTables);
    	List<SgTheme> dataList = sgThemeService.queryPageList(sgTheme,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 跳转到我是法官的页面
     */
    @RequestMapping("/theme_list")
    public String themeList() {
        return PREFIX + "theme_list.html";
    }
    /**
     * 查询我是法官列表
     */
    @RequestMapping("/theme_list_pageList")
    @ResponseBody
    public JsonResult pageList(DataTablesUtil dataTables,Page page,SgTheme sgTheme) {
    	if(!StringUtil.isEmpty(sgTheme.getTitle4())){
    		sgTheme.setTitle(sgTheme.getTitle4());
    	}
    	page = this.getPageInfo(page,dataTables);
    	List<SgTheme> dataList = sgThemeService.queryPageList(sgTheme,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 跳转到新增我是法官的页面
     * @throws ParseException 
     */
    @RequestMapping("/add_theme")
    public String addTheme(Model modle) throws ParseException {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	int num = sgThemeService.selectMaxNum();
    	modle.addAttribute("num", num+1);
    	SgTheme sgTheme = sgThemeService.selectMaxEndDate(null);
    	String enddate = "";
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	if(sgTheme != null){
    		String endDate = sgTheme.getEndDate();
    		Date  currdate = format.parse(endDate);
    		Date dt1 = df.parse(endDate);
            Date dt2 = df.parse(format.format(new Date()));
    		if(dt1.getTime()<dt2.getTime()){
    			enddate = format.format(new Date());
    		}else{
    			Calendar  calendar = new GregorianCalendar();
    			calendar.setTime(dt1); 
    			calendar.add(calendar.DATE,1);
            	currdate = calendar.getTime();
            	enddate = format.format(currdate);
    		}
    	}else{
    		enddate = format.format(new Date());
    	}
    	modle.addAttribute("endDate", enddate);
        return PREFIX + "add_theme.html";
    }
    /**
     * 跳转到编辑我是法官的页面
     * @throws ParseException 
     */
    @RequestMapping("/edit_theme")
    public String editTheme(String id,String type,Model modle) throws ParseException {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	SgTheme sgTheme = new SgTheme();
    	sgTheme.setId(id);
    	List<SgTheme> list = sgThemeService.queryPageList(sgTheme,null);
    	SgTheme st = list.get(0);
    	int num = st.getNum();
    	SgTheme sg = sgThemeService.selectMaxEndDate((num-1)+"");
    	modle.addAttribute("info", st);
    	modle.addAttribute("type", type);
    	String enddate = "";
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	if(type.equals("edit")){
    		String endDate = sg.getEndDate();
    		Date  currdate = format.parse(endDate);
    		Date dt1 = df.parse(endDate);
            Date dt2 = df.parse(format.format(new Date()));
    		if(dt1.getTime()<dt2.getTime()){
    			enddate = format.format(new Date());
    		}else{
    			Calendar  calendar = new GregorianCalendar();
    			calendar.setTime(dt1); 
    			calendar.add(calendar.DATE,1);
            	currdate = calendar.getTime();
            	enddate = format.format(currdate);
    		}
        	
    	}
    	modle.addAttribute("endDate", enddate);
        return PREFIX + "edit_theme.html";
    }
    /**
     * 新增
     * @return
     */
    @ResponseBody
    @BussinessLog(value = "新增我是法官", key = "id", dict = SgThemeDic.class)
    @RequestMapping("/add")
    public JsonResult add(SgTheme sgTheme){
    	String startDate = sgTheme.getStartDate();
    	String endDate = sgTheme.getEndDate();
    	Date startTime = DateUtil.parseDate(startDate);
    	Date endTime = DateUtil.parseDate(endDate);
    	int i = -1;
    	sgTheme.setId(UUIDUtil.getUUID());
    	sgTheme.setCreateTime(new Date());
    	int num = sgThemeService.selectMaxNum();
    	sgTheme.setNum(num+1);
    	sgTheme.setStartTime(startTime);
    	sgTheme.setEndTime(endTime);
    	i = sgThemeService.insertSelective(sgTheme);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    	
    	
    }
    /**
     * 修改
     * @return
     */
    @ResponseBody
    @BussinessLog(value = "修改我是法官", key = "id", dict = SgThemeDic.class)
    @RequestMapping("/edit")
    public JsonResult edit(SgTheme sgTheme){
    	String startDate = sgTheme.getStartDate();
    	String endDate = sgTheme.getEndDate();
    	Date startTime = DateUtil.parseDate(startDate);
    	Date endTime = DateUtil.parseDate(endDate);
    	sgTheme.setStartTime(startTime);
    	sgTheme.setEndTime(endTime);
    	int i = -1;
    	i = sgThemeService.updateByPrimaryKeySelective(sgTheme);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    	
    }
    /**
     * 查看答题人数管理的页面
     */
    @RequestMapping("/answer_num_edit")
    public String answerNumEdit(String themeId ,Model m) {
    	m.addAttribute("themeId", themeId);
        return PREFIX + "answer_num_edit.html";
    } 
    /**
     * 查询答题人数获奖列表
     */
    @RequestMapping("/answerPerson_awardList")
    @ResponseBody
    public JsonResult awardList(DataTablesUtil dataTables,Page page,SgThemeAnswerLog model) {
    	page = this.getPageInfo(page,dataTables);
    	List<SgThemeAnswerLog> dataList = sgThemeAnswerLogService.queryThemeList(model,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 删除
     * @param ids
     * @return
     */
   @RequestMapping("/del")
   @BussinessLog(value = "删除我是法官", key = "id", dict = SgThemeDic.class)
   @ResponseBody
   public JsonResult del(String ids){
   	if (StringUtil.isEmpty(ids)) {
   		return new JsonResult(Message.M4002);
       }
   	int ret = -1;
   	String[] idArray = ids.split(",");
   	for(String id:idArray ){
   		SgThemeAwardLogExample e = new SgThemeAwardLogExample();
   	 	com.ffxl.cloud.model.base.BaseSgThemeAwardLogExample.Criteria cc= e.createCriteria();
   	 	cc.andThemeIdEqualTo(id);
   		sgThemeAwardLogService.deleteByExample(e);
   		SgThemeAnswerLogExample example = new SgThemeAnswerLogExample();
   	 	com.ffxl.cloud.model.base.BaseSgThemeAnswerLogExample.Criteria c= example.createCriteria();
   	 	c.andThemeIdEqualTo(id);
   		sgThemeAnswerLogService.deleteByExample(example);
   		ret = sgThemeService.deleteByPrimaryKey(id);
   	}
   	if(ret >0){
      	 return new JsonResult(Message.M2000);
      }else{
      	 return new JsonResult(Message.M5000);
      }
   	
   }
}
