package com.ffxl.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.cloud.model.SgBanner;
import com.ffxl.cloud.model.SgBannerExample;
import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgLawExample;
import com.ffxl.cloud.model.SgUserFavorite;
import com.ffxl.cloud.model.SgUserFavoriteExample;
import com.ffxl.cloud.model.base.BaseSgLawExample.Criteria;
import com.ffxl.cloud.service.SgBannerService;
import com.ffxl.cloud.service.SgLawService;
import com.ffxl.cloud.service.SgUserFavoriteService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;
/**
 * 首页
 * @author feifan
 *
 */
@Controller
@RequestMapping(value = "/SgHomePageController")
public class SgHomePageController {
	@Autowired
	private SgLawService sgLawService;
	
	@Autowired
	private SgUserFavoriteService sgUserFavoriteService;
	
	@Autowired
	private SgBannerService sgBannerService;
	
	/**
	 * banner
	 * @return
	 */
	@RequestMapping(value = "/queryBanner")
    @ResponseBody
	public JsonResult queryBanner(){
		SgBannerExample example = new SgBannerExample();
        com.ffxl.cloud.model.base.BaseSgBannerExample.Criteria c= example.createCriteria();
        c.andStatusEqualTo("publish");
        example.setOrderByClause(" sort_num desc ");
		List<SgBanner> list = sgBannerService.selectByExample(example);
		return new JsonResult(Message.M2000,list);
		
	}
	
	
	
	/**
	 * 首页的法治动态和法律讲堂
	 * @param category(law_information 法治动态)(law_lecture_room 法律讲堂)
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/querySgLaw")
    @ResponseBody
    public JsonResult getOrders(String category, Page page) {
		if(StringUtil.isEmpty(category)){
			return new JsonResult(Message.M4003);
		}
        List<SgLaw> list = sgLawService.querySgLawByPage(category, page);
        page.setDataList(list);
        return new JsonResult(Message.M2000, page);
    }
	/**
	 * 法治详情
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/querySgLawDetail")
    @ResponseBody
    public JsonResult querySgLawDetail(String userId,String id,String category,int sortnum) {
		if(StringUtil.isEmpty(id)){
			return new JsonResult(Message.M4003);
		}
		if(StringUtil.isEmpty(userId)){
			return new JsonResult(Message.M4003);
		}
        SgLaw sgLaw = sgLawService.selectByPrimaryKey(id);
        SgUserFavoriteExample example = new SgUserFavoriteExample();
        com.ffxl.cloud.model.base.BaseSgUserFavoriteExample.Criteria c= example.createCriteria();
        c.andSourceIdEqualTo(id);
        c.andUserIdEqualTo(userId);
        List<SgUserFavorite> list = sgUserFavoriteService.selectByExample(example);
        if(list != null && list.size() > 0){
        	sgLaw.setFavorite("1");//已收藏
        }
        if(category.equals("law_lecture_room")){
        	if(StringUtil.isEmpty(sortnum+"")){
    			return new JsonResult(Message.M4003);
    		}
        	SgLaw sl = new SgLaw();
        	sl.setSortnum(sortnum);
        	SgLaw pre = sgLawService.queryNextLawRoom(sl);
        	Map<String,String> map = new HashMap<String,String>();
        	
        	if(pre != null){
        		map.put("preId", pre.getId());
            	map.put("preTitle", pre.getTitle());
        	}else{
        		map.put("nextId", null);
            	map.put("nextTitle", null);
        	}
        	
        	SgLaw next = sgLawService.queryPreLawRoom(sl);
        	if(next != null){
        		map.put("nextId", next.getId());
            	map.put("nextTitle", next.getTitle());
        	}else{
        		map.put("nextId", null);
            	map.put("nextTitle", null);
        	}
        	sgLaw.setMap(map);
        }
        return new JsonResult(Message.M2000, sgLaw);
    }
	/**
	 * 收藏(	取消收藏)
	 * @param sourceType
	 * @param sourceId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/insertUserFavorite")
    @ResponseBody
	public JsonResult insertUserFavorite(String sourceType,String sourceId,String userId){
		if(StringUtil.isEmpty(sourceType)){
			return new JsonResult(Message.M4003);
		}
		if(StringUtil.isEmpty(sourceId)){
			return new JsonResult(Message.M4003);
		}
		if(StringUtil.isEmpty(userId)){
			return new JsonResult(Message.M4003);
		}
		SgUserFavorite record = new SgUserFavorite();
		record.setId(UUIDUtil.getUUID());
		record.setSourceId(sourceId);
		record.setSourceType(sourceType);
		record.setUserId(userId);
		record.setCreateDate(new Date());
		SgUserFavoriteExample example = new SgUserFavoriteExample();
        com.ffxl.cloud.model.base.BaseSgUserFavoriteExample.Criteria c= example.createCriteria();
        c.andSourceTypeEqualTo(sourceType);
        c.andSourceIdEqualTo(sourceId);
        c.andUserIdEqualTo(userId);
        int i = -1;
        List<SgUserFavorite> list = sgUserFavoriteService.selectByExample(example);
        if(list != null && list.size() > 0){
        	i = sgUserFavoriteService.deleteByExample(example);
        }else{
        	i = sgUserFavoriteService.insertSelective(record);
        }
		if(i > 0){
			return new JsonResult(Message.M2000);
		}else{
			return new JsonResult(Message.M5000);
		}
		
	}
	/**
	 * 查询苏供法宝
	 * @param id
	 * @param category
	 * @param categoryCode
	 * @return
	 */
	@RequestMapping(value = "/querySgMagic")
    @ResponseBody
	public JsonResult querySgMagic(String category,String categoryCode,String title){
		if(StringUtil.isEmpty(category)){
			return new JsonResult(Message.M4003);
		}
		SgLawExample example = new SgLawExample();
        Criteria c= example.createCriteria();
        c.andStatusEqualTo("publish");
        c.andCategoryEqualTo(category);
        if(StringUtil.isEmpty(categoryCode)){
        	c.andCategoryCodeIsNull();
        	example.setOrderByClause(" num desc ");
        }else{
        	c.andCategoryCodeEqualTo(categoryCode);
        	example.setOrderByClause(" num asc ");
        }
        if(!StringUtil.isEmpty(title)){
        	c.andTitleLike(title);
        }
		List<SgLaw> list = sgLawService.selectByExample(example);
		if(!StringUtil.isEmpty(categoryCode)){
			for(SgLaw sl:list){
				String stage = toChinese(sl.getNum()+"");
				sl.setChapter("第"+stage+"章");
			}
		}
		return new JsonResult(Message.M2000,list);
		
	}
	/**
	 * 查询苏供法宝详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/querySgMagicDetail")
    @ResponseBody
	public JsonResult querySgMagicDetail(String id){
		if(StringUtil.isEmpty(id)){
			return new JsonResult(Message.M4003);
		}
		SgLaw sgLaw = sgLawService.selectByPrimaryKey(id);
		String category = sgLaw.getCategory();
		String categoryCode = sgLaw.getCategoryCode();
		SgLawExample example = new SgLawExample();
        Criteria c= example.createCriteria();
        c.andStatusEqualTo("publish");
        c.andCategoryEqualTo(category);
        c.andCategoryCodeEqualTo(categoryCode);
        List<SgLaw> list = sgLawService.selectByExample(example);
        int num = 0;
        int count = list.size();
        for(int i = 0;i<list.size();i++){
        	if(list.get(i).getId().equals(sgLaw.getId())){
        		num = i;
        		break;
        	}
        }
        sgLaw.setAllChapter(num+"/"+count);
        String stage = toChinese(sgLaw.getNum()+"");
        sgLaw.setChapter("第"+stage+"章");
        SgLaw sl = new SgLaw();
    	sl.setNum(sgLaw.getNum());
    	sl.setCategoryCode(categoryCode);
        SgLaw next = sgLawService.queryNextLawMagic(sl);
    	Map<String,String> map = new HashMap<String,String>();
    	if(next != null){
    		map.put("nextId", next.getId());
        	map.put("nextTitle", next.getTitle());
    	}else{
    		map.put("nextId", null);
        	map.put("nextTitle", null);
    	}
    	
    	SgLaw pre = sgLawService.queryPreLawMagic(sl);
    	
    	if(pre != null){
    		map.put("preId", pre.getId());
        	map.put("preTitle", pre.getTitle());
    	}else{
    		map.put("preId", null);
        	map.put("preTitle", null);
    	}
    	sgLaw.setMap(map);
    	return new JsonResult(Message.M2000,sgLaw);
		
	}
	/**
     * 阿拉伯数组转中文數字【十万九千零六十  --> 109060】
     * @author
     * @param chineseNumber
     * @return
     */
	public String toChinese(String string) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        String result = "";
        int n = string.length();
        for (int i = 0; i < n; i++) {
            int num = string.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;
	}
}
