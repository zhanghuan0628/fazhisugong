package com.ffxl.cloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ffxl.cloud.mapper.SgSubjectMapper;
import com.ffxl.cloud.model.SgSubject;
import com.ffxl.cloud.model.SgSubjectExample;
import com.ffxl.cloud.model.SgTheme;
import com.ffxl.cloud.model.SgThemeAnswerLog;
import com.ffxl.cloud.model.SgThemeAnswerLogExample;
import com.ffxl.cloud.model.base.BaseSgSubjectExample.Criteria;
import com.ffxl.cloud.service.SgSubjectService;
import com.ffxl.cloud.service.SgThemeAnswerLogService;
import com.ffxl.cloud.service.SgThemeService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.StringUtil;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
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
public class SgSubjectServiceImpl extends GenericServiceImpl<SgSubject, SgSubjectExample, String> implements SgSubjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgSubjectServiceImpl.class);

    @Autowired
    private SgSubjectMapper sgSubjectMapper;
    
    @Autowired
    private SgThemeService sgThemeService;
    
    @Autowired
    private SgThemeAnswerLogService sgThemeAnswerLogService;

    @Override
    public GenericMapper<SgSubject, SgSubjectExample, String> getGenericMapper() {
        return sgSubjectMapper;
    }

    public SgSubject queryByModel(SgSubject sgSubject) {
        SgSubjectExample example = new SgSubjectExample();
        Criteria c= example.createCriteria();
        List<SgSubject> modelList =  sgSubjectMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SgSubject> queryPageList(SgSubject sgSubject, Page page) {
		List<SgSubject> list = sgSubjectMapper.queryPageList(sgSubject, page);
		List newList = new ArrayList();
		for(SgSubject ss:list){
			String json = ss.getQuestionJson();
            JSONObject jsStr = JSONObject.parseObject(json);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("qstn", jsStr.get("qstn"));
            String optn = String.valueOf(jsStr.get("optn"));
            JSONArray jsonArray = JSONArray.fromObject(optn);
            List<SgSubject> l = JSONArray.toList(jsonArray, SgSubject.class);// 过时方法
            int i = 1;
            for(SgSubject s:l){
            	String r = s.getResult();
            	String t = s.getTitle();
            	if(r.equals("1")){
            		map.put("result", i+"."+t);
            	}
            	i++;
            }
            map.put("id", ss.getId());
            map.put("num", l.size());
            newList.add(map);
		}
		return newList;
	}

	@Override
	public Map queryCheckTheme(String userId) {
		boolean b = false;
		SgTheme model = new SgTheme();
		List<SgTheme> list = sgThemeService.queryPageList(model,null);
		Map map = new HashMap();
		for(SgTheme st:list){
			if(st.getState().equals("3")){
				SgThemeAnswerLogExample example = new SgThemeAnswerLogExample();
		        com.ffxl.cloud.model.base.BaseSgThemeAnswerLogExample.Criteria c= example.createCriteria();
		        c.andUserIdEqualTo(userId);
		        List<SgThemeAnswerLog> l = sgThemeAnswerLogService.selectByExample(example);
		        if(l != null && l.size() > 0){
		        	for(SgThemeAnswerLog mod:l){
		        		if(mod.getThemeId().equals(st.getId())){
		        			String id = st.getId();
		        			int num  = st.getSubjectCounts();
							int score = st.getSore();
		        			map.put("act", 4);//已完成答题
		        			map.put("themeId", id);
		        			map.put("num", num);
							map.put("score", score);
							String s = st.getStage();
							map.put("stage", s);
		        			return map;
		        		}else{
		        			String id = st.getId();
							int num  = st.getSubjectCounts();
							int score = st.getSore();
							map.put("act", 1);//进行中
							map.put("themeId", id);
							map.put("num", num);
							map.put("score", score);
							String s = st.getStage();
							map.put("stage", s);
							b = true;
		        		}
		        	}
		        }else{
		        	String id = st.getId();
					int num  = st.getSubjectCounts();
					int score = st.getSore();
					map.put("act", 1);//进行中
					map.put("themeId", id);
					map.put("num", num);
					map.put("score", score);
					String s = st.getStage();
					map.put("stage", s);
					return map;
		        }
				
			}else{
				if(b == false){
					SgThemeAnswerLog sl = sgThemeAnswerLogService.queryMaxDateById(userId);
					if(sl != null){
						Date endTime = sl.getCreateDate();
						SgThemeAnswerLogExample example = new SgThemeAnswerLogExample();
				        com.ffxl.cloud.model.base.BaseSgThemeAnswerLogExample.Criteria c= example.createCriteria();
				        c.andCreateDateEqualTo(endTime);
				        c.andUserIdEqualTo(userId);
						List<SgThemeAnswerLog> l = sgThemeAnswerLogService.selectByExample(example);
	        			int num  = st.getSubjectCounts();
						int score = st.getSore();
	        			map.put("num", num);
						map.put("score", score);
						String s = st.getStage();
						map.put("stage", s);
						map.put("act", 3);//活动已结束
						map.put("themeId", l.get(0).getThemeId());
						return map;
					}else{
	        			int num  = st.getSubjectCounts();
						int score = st.getSore();
	        			map.put("num", num);
						map.put("score", score);
						String s = st.getStage();
						map.put("stage", s);
						map.put("act", 2);//此人没参加过活动
						return map;
					}
				}else{
					return map;
				}
				
			}
		}
		return map;
		
	}

	@Override
	public List<SgSubject> querySubjectByTheme(String themeId, int num) {
		List<SgSubject> list = sgSubjectMapper.querySubjectByTheme(num+"");
		List sList = new ArrayList();
		for(SgSubject ss:list){
			
			String json = ss.getQuestionJson();
            JSONObject jsStr = JSONObject.parseObject(json);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("qstn", jsStr.get("qstn"));
            String optn = String.valueOf(jsStr.get("optn"));
            JSONArray jsonArray = JSONArray.fromObject(optn);
            List<SgSubject> l = JSONArray.toList(jsonArray, SgSubject.class);// 过时方法
            map.put("list", l);
            map.put("themeId", ss.getThemeId());
            map.put("id", ss.getId());
            sList.add(map);
		}
		return sList;
	}
}