package com.ffxl.cloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ffxl.cloud.mapper.SgSubjectMapper;
import com.ffxl.cloud.model.SgSubject;
import com.ffxl.cloud.model.SgSubjectExample;
import com.ffxl.cloud.model.SgTheme;
import com.ffxl.cloud.model.base.BaseSgSubjectExample.Criteria;
import com.ffxl.cloud.service.SgSubjectService;
import com.ffxl.cloud.service.SgThemeService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.StringUtil;

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
public class SgSubjectServiceImpl extends GenericServiceImpl<SgSubject, SgSubjectExample, String> implements SgSubjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgSubjectServiceImpl.class);

    @Autowired
    private SgSubjectMapper sgSubjectMapper;
    
    @Autowired
    private SgThemeService sgThemeService;

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
	public Map queryCheckTheme() {
		List<SgTheme> list = sgThemeService.queryPageList(null,null);
		Map map = new HashMap();
		for(SgTheme st:list){
			if(st.getStage().equals("3")){
				String id = st.getId();
				int num  = st.getSubjectCounts();
				int score = st.getSore();
				map.put("themeId", id);
				map.put("num", num);
				map.put("score", score);
				int n = st.getNum();
				String s = st.getStage();
				map.put("stage", s);
				break;
			}
		}
		return map;
		
	}

	@Override
	public List<SgSubject> querySubjectByTheme(String themeId, int num) {
		List<SgSubject> list = sgSubjectMapper.querySubjectByTheme(num+"");
		for(SgSubject ss:list){
			ss.setThemeId(themeId);
		}
		return list;
	}
}