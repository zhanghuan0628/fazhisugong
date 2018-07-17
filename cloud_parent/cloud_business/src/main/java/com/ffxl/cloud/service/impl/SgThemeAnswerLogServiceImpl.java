package com.ffxl.cloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ffxl.cloud.mapper.SgThemeAnswerLogMapper;
import com.ffxl.cloud.model.SgSubject;
import com.ffxl.cloud.model.SgThemeAnswerLog;
import com.ffxl.cloud.model.SgThemeAnswerLogExample;
import com.ffxl.cloud.model.SgThemeAwardLog;
import com.ffxl.cloud.model.SgThemeAwardLogExample;
import com.ffxl.cloud.model.base.BaseSgThemeAnswerLogExample.Criteria;
import com.ffxl.cloud.service.SgSubjectService;
import com.ffxl.cloud.service.SgThemeAnswerLogService;
import com.ffxl.cloud.service.SgThemeAwardLogService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SgThemeAnswerLogServiceImpl extends GenericServiceImpl<SgThemeAnswerLog, SgThemeAnswerLogExample, String> implements SgThemeAnswerLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgThemeAnswerLogServiceImpl.class);

    @Autowired
    private SgThemeAnswerLogMapper sgThemeAnswerLogMapper;
    
    @Autowired
    private SgThemeAwardLogService sgThemeAwardLogService;
    
    @Autowired
    private SgSubjectService sgSubjectService;

    @Override
    public GenericMapper<SgThemeAnswerLog, SgThemeAnswerLogExample, String> getGenericMapper() {
        return sgThemeAnswerLogMapper;
    }

    public SgThemeAnswerLog queryByModel(SgThemeAnswerLog sgThemeAnswerLog) {
        SgThemeAnswerLogExample example = new SgThemeAnswerLogExample();
        Criteria c= example.createCriteria();
        List<SgThemeAnswerLog> modelList =  sgThemeAnswerLogMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public SgThemeAnswerLog queryAnswerLogByUser(String userId, String themeId) {
		SgThemeAnswerLog m = sgThemeAnswerLogMapper.queryAnswerLogByUser(userId,themeId);
		if(m != null){
			SgThemeAwardLogExample example = new SgThemeAwardLogExample();
	        com.ffxl.cloud.model.base.BaseSgThemeAwardLogExample.Criteria c= example.createCriteria();
	        c.andThemeIdEqualTo(themeId);
	        c.andUserIdEqualTo(userId);
			List<SgThemeAwardLog> list = sgThemeAwardLogService.selectByExample(example);
			if(list != null && list.size() > 0){
				m.setGetAward("1");//已抽过奖
				SgThemeAwardLog sl = list.get(0);
				m.setCode(sl.getCode());
			}else{
				m.setGetAward("0");//未抽过奖
			}
			String num = toChinese(m.getNum());
			m.setStage("第"+num+"期");
			if(m.getRightNum()>=m.getFine()){
				m.setState("1");//优秀
			}else if(m.getRightNum()>=m.getGood()){
				m.setState("2");//良好
			}else if(m.getRightNum()>=m.getPass()){
				m.setState("3");//及格
			}else{
				m.setState("4");//不及格
			}
		}
		return m;
	}

	@Override
	public List<SgThemeAnswerLog> queryMyTheme(String userId,Page page) {
		List<SgThemeAnswerLog> list = sgThemeAnswerLogMapper.queryMyTheme(userId,page);
		for(SgThemeAnswerLog sl:list){
			String num = toChinese(sl.getNum());
			sl.setStage("第"+num+"期");
		}
		return list;
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

	@Override
	public SgThemeAnswerLog queryMaxDateById(String userId) {
		return sgThemeAnswerLogMapper.queryMaxDateById(userId);
	}

	@Override
	public List<SgSubject> queryUserBackTheme(String userId, String themeId) {
		SgThemeAnswerLog m = sgThemeAnswerLogMapper.queryAnswerLogByUser(userId,themeId);
		String json = m.getAnswerJson();
		JSONArray jsonArray = JSONArray.fromObject(json);
        List<SgSubject> list = JSONArray.toList(jsonArray, SgSubject.class);// 过时方法
        List ls = new ArrayList();
        for(SgSubject s:list){
        	SgSubject ss = sgSubjectService.selectByPrimaryKey(s.getQuestion_id());
        	String js = ss.getQuestionJson();
            JSONObject jsStr = JSONObject.parseObject(js);
            String optn = String.valueOf(jsStr.get("optn"));
            JSONArray jsonArrays = JSONArray.fromObject(optn);
            Map map = new HashMap();
            List<SgSubject> l = JSONArray.toList(jsonArrays, SgSubject.class);// 过时方法
            for(int i = 0;i < l.size();i++){
            	if(s.getChoose().equals(i+"")){
            		l.get(i).setNum(1);
            	}else{
            		l.get(i).setNum(0);
            	}
            }
            map.put("id", ss.getId());
            map.put("qstn", jsStr.get("qstn"));
            map.put("list", l);
            ls.add(map);
        }
		return ls;
	}
}