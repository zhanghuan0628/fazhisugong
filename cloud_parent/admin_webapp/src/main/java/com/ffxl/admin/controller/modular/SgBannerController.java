package com.ffxl.admin.controller.modular;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.admin.core.common.annotion.BussinessLog;
import com.ffxl.admin.core.common.constant.dictmap.SgBannerDic;
import com.ffxl.admin.core.common.constant.dictmap.SgLawDic;
import com.ffxl.admin.core.log.LogObjectHolder;
import com.ffxl.cloud.model.SgAsk;
import com.ffxl.cloud.model.SgBanner;
import com.ffxl.cloud.model.SgBannerExample;
import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgLawExample;
import com.ffxl.cloud.model.SgTheme;
import com.ffxl.cloud.model.base.BaseSgLawExample.Criteria;
import com.ffxl.cloud.service.SgAskService;
import com.ffxl.cloud.service.SgBannerService;
import com.ffxl.cloud.service.SgLawService;
import com.ffxl.cloud.service.SgThemeService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.exception.BusinessException;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

/**
 * banner
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sg_banner")
public class SgBannerController extends BaseController{
	@Autowired
	private SgBannerService sgBannerService;
	
	@Autowired
	private SgLawService sgLawService;
	
	@Autowired
	private SgAskService sgAskService;
	
	@Autowired
	private SgThemeService sgThemeService;
	
	private static String PREFIX = "/fzsg/sg_banner/";
	
	/**
     * 跳转到banner的页面
     */
    @RequestMapping("/banner_list")
    public String index() {
        return PREFIX + "banner_list.html";
    }
    /**
     * 查询banner列表
     */
    @RequestMapping("/banner_pageList")
    @ResponseBody
    public JsonResult bannerPageList(DataTablesUtil dataTables,Page page,SgBanner sgBanner) {
    	page = this.getPageInfo(page,dataTables);
    	List<SgBanner> dataList = sgBannerService.queryPageList(sgBanner,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 跳转到查看banner新增的页面
     */
    @RequestMapping("/add_banner")
    public String bannerAdd() {
        return PREFIX + "add_banner.html";
    }
    /**
     * 跳转到查看banner修改的页面
     */
    @RequestMapping("/edit_banner")
    public String bannerEdit(String id,Model model) {
    	if (StringUtil.isEmpty(id)) {
            throw new BusinessException(Message.M6002);
        }
    	SgBanner info = sgBannerService.selectByPrimaryKey(id);
        model.addAttribute("info", info);
        LogObjectHolder.me().set(info);
        return PREFIX + "edit_banner.html";
    }
    /**
     * 新增
     */
    @RequestMapping("/add")
    @BussinessLog(value = "新增banner", key = "id", dict = SgBannerDic.class)
    @ResponseBody
    public JsonResult add(SgBanner sgBanner) {
    	sgBanner.setId(UUIDUtil.getUUID());
    	sgBanner.setCreateDate(new Date());
    	int num = sgBannerService.queryMaxNum();
    	sgBanner.setSortNum(num+1);
    	int i = sgBannerService.insertSelective(sgBanner);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    }
    /**
     * 修改
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "修改banner", key = "id", dict = SgBannerDic.class)
    @ResponseBody
    public JsonResult edit(SgBanner sgBanner) {
    	int i = sgBannerService.updateByPrimaryKeySelective(sgBanner);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    }
    /**
     * 上下架
     */
    @RequestMapping("/updateStatus")
    @BussinessLog(value = "上下架banner", key = "id", dict = SgLawDic.class)
    @ResponseBody
    public JsonResult updateStatus(String ids,String state){
    	if (StringUtil.isEmpty(ids)) {
    		return new JsonResult(Message.M4002);
        }
    	int ret = -1;
    	String[] idArray = ids.split(",");
    	for(String id:idArray ){
    		SgBanner record = new SgBanner();
    		record.setId(id);
    		record.setStatus(state);
    		ret = sgBannerService.updateByPrimaryKeySelective(record);
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
    @RequestMapping("/del")
    @BussinessLog(value = "删除banner", key = "id", dict = SgLawDic.class)
    @ResponseBody
    public JsonResult del(String ids){
    	if (StringUtil.isEmpty(ids)) {
    		return new JsonResult(Message.M4002);
        }
    	int ret = -1;
    	String[] idArray = ids.split(",");
    	for(String id:idArray ){
    		ret = sgBannerService.deleteByPrimaryKey(id);
    	}
    	if(ret >0){
       	 return new JsonResult(Message.M2000);
       }else{
       	 return new JsonResult(Message.M5000);
       }
    	
    }
    /**
     * 跳转到查看banner链接新增的页面
     */
    @RequestMapping("/add_bannerPanel")
    public String bannersAdd() {
        return PREFIX + "add_bannerPanel.html";
    }
    /**
     * 法治动态
     * @param id
     * @return
     */
    @RequestMapping("/queryLawTitle")
    @ResponseBody
    public JsonResult queryLawTitle(String ids){
    	SgLaw sl = sgLawService.selectByPrimaryKey(ids);
		return new JsonResult(Message.M2000,sl);
    	
    }
    /**
     * 咨询
     * @param id
     * @return
     */
    @RequestMapping("/queryAskTitle")
    @ResponseBody
    public JsonResult queryAskTitle(String ids){
    	SgAsk sl = sgAskService.selectByPrimaryKey(ids);
		return new JsonResult(Message.M2000,sl);
    	
    }
    /**
     * 我是法官
     * @param id
     * @return
     */
    @RequestMapping("/queryThemeTitle")
    @ResponseBody
    public JsonResult queryThemeTitle(String ids){
    	SgTheme sl = sgThemeService.selectByPrimaryKey(ids);
		return new JsonResult(Message.M2000,sl);
    	
    }
    /**
     * 上移下移
     */
    @RequestMapping("/push")
    @BussinessLog(value = "上移下移banner", key = "id", dict = SgLawDic.class)
    @ResponseBody
    public JsonResult push(String id,String state,int sort){
    	int i = -1;
    	SgBanner record = new SgBanner();
    	SgBannerExample example = new SgBannerExample();
        com.ffxl.cloud.model.base.BaseSgBannerExample.Criteria c= example.createCriteria();
        c.andIdEqualTo(id);
        if(state.equals("down")){
            if(sort > 1){
            	SgBanner sb = new SgBanner();
            	sb.setNum(sort);
            	sb.setSortNum(sort - 1);
                i = sgBannerService.updateSort(sb);
                record.setSortNum(sort - 1);
                i = sgBannerService.updateByExampleSelective(record, example);
            }
        }else{
            int s = sgBannerService.queryMaxNum();
            if(s > sort){
            	SgBanner sb = new SgBanner();
            	sb.setNum(sort);
            	sb.setSortNum(sort + 1);
                i = sgBannerService.updateSort(sb);
                record.setSortNum(sort + 1);
                i = sgBannerService.updateByExampleSelective(record, example);
            }
        }
        
        if(i > 0){
            return new JsonResult(Message.M2000);
        }
        return new JsonResult(Message.M5000);
    	
    }
}
