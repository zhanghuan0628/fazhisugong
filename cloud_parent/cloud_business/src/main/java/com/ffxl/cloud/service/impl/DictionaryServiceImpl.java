package com.ffxl.cloud.service.impl;


import com.ffxl.cloud.mapper.DictionaryMapper;
import com.ffxl.cloud.model.Dictionary;
import com.ffxl.cloud.model.DictionaryExample;
import com.ffxl.cloud.model.SgSubject;
import com.ffxl.cloud.model.base.BaseDictionaryExample.Criteria;
import com.ffxl.cloud.service.DictionaryService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.ToolUtil;
import com.ffxl.platform.util.UUIDUtil;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class DictionaryServiceImpl extends GenericServiceImpl<Dictionary, DictionaryExample, String> implements DictionaryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public GenericMapper<Dictionary, DictionaryExample, String> getGenericMapper() {
        return dictionaryMapper;
    }

    public Dictionary queryByModel(Dictionary dictionary) {
        DictionaryExample example = new DictionaryExample();
        Criteria c= example.createCriteria();
        if(ToolUtil.isNotEmpty(dictionary.getName())){
            c.andNameEqualTo(dictionary.getName());
        }
        if(ToolUtil.isNotEmpty(dictionary.getPid())){
            c.andPidEqualTo(dictionary.getPid());
        }
        List<Dictionary> modelList =  dictionaryMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Dictionary> queryListByPid(Dictionary dictionary) {
        DictionaryExample example = new DictionaryExample();
        Criteria c= example.createCriteria();
        if(ToolUtil.isNotEmpty(dictionary.getPid())){
            c.andPidEqualTo(dictionary.getPid());
        }
        example.setOrderByClause(" num asc");
        List<Dictionary> modelList =  dictionaryMapper.selectByExample(example);
        return modelList;
    }

	@Override
	public List<Dictionary> queryPageList(Dictionary dictionary, Page page) {
		
		return dictionaryMapper.queryPageList(dictionary,page);
	}

	@Override
	public int queryMaxNumByPid(String pid) {
		return dictionaryMapper.queryMaxNumByPid(pid);
	}

	@Override
	public Dictionary queryAllAward() {
		DictionaryExample example = new DictionaryExample();
        Criteria c= example.createCriteria();
        c.andPidEqualTo("6");
        example.setOrderByClause(" chance asc ");
        List<Dictionary> list = dictionaryMapper.selectByExample(example);
        int n = drawGift(list);
        Dictionary d = new Dictionary();
        for(int i = 0;i<list.size();i++){
        	if(i==n){
        		d.setCode(i+"");
        		d.setId(list.get(i).getId());
        		d.setName(list.get(i).getName());
        	}
        }
		return d;
	}
	public static int drawGift(List<Dictionary> giftList){
		 
        if(null != giftList && giftList.size()>0){
            List orgProbList = new ArrayList(giftList.size());
            for(Dictionary gift:giftList){
                //按顺序将概率添加到集合中
            	String g = gift.getTips();
            	int end = g.indexOf("%");
            	double d = 0;
            	if(end > -1){
            		String sg = g.substring(0, end);
            		d = Double.parseDouble(sg);
            	}else{
            		d = Double.parseDouble(g);
            	}
            	double dd = d/100;
                orgProbList.add(dd);
            }
 
            return draw(orgProbList);
 
        }
        return -1;
    }

	private static int draw(List giftProbList) {
		List sortRateList = new ArrayList();
		 
        // 计算概率总和
        Double sumRate = 0D;
        for(Object prob : giftProbList){
        	double d = Double.parseDouble(prob.toString());
            sumRate += d;
        }
 
        if(sumRate != 0){
            double rate = 0D;   //概率所占比例
            for(Object prob : giftProbList){
            	double d = Double.parseDouble(prob.toString());
                rate += d;   
                // 构建一个比例区段组成的集合(避免概率和不为1)
                sortRateList.add(rate / sumRate);
            }
 
            // 随机生成一个随机数，并排序
            double random = Math.random();
            sortRateList.add(random);
            Collections.sort(sortRateList);
 
            // 返回该随机数在比例集合中的索引
            return sortRateList.indexOf(random);
        }
        return -1;
	}

	@Override
	public List<Dictionary> queryDictPageList(Dictionary dictionary, Page page) {
		List<Dictionary> list = dictionaryMapper.queryDictPageList(dictionary,page);
		for(Dictionary d:list){
			String dicName = "";
			DictionaryExample example = new DictionaryExample();
	        Criteria c= example.createCriteria();
	        c.andPidEqualTo(d.getId());
	        List<Dictionary> l = dictionaryMapper.selectByExample(example);
	        for(Dictionary dic:l){
	        	String name = dic.getName();
	        	int num = dic.getNum();
	        	dicName = dicName+(num+":"+name)+",";
	        }
	        d.setDictName(dicName);
		}
		return list;
	}

	@Override
	public int addDict(String dictName, String dictValues,String tips) {
        //解析dictValues
		JSONArray jsonArray = JSONArray.fromObject(dictValues);
        List<Dictionary> items = JSONArray.toList(jsonArray, Dictionary.class);// 过时方法
        //添加字典
        Dictionary dict = new Dictionary();
        dict.setName(dictName);
        int n = dictionaryMapper.queryMaxNumByPid("0");
        dict.setNum(n+1);
        dict.setPid(0+"");
        dict.setId(UUIDUtil.getUUID());
        if(!StringUtil.isEmpty(tips)){
        	dict.setTips(tips);
		}
        int i = dictionaryMapper.insertSelective(dict);
        //添加字典条目
        for (Dictionary item : items) {
        	int num = item.getNum();
            String name = item.getName();
            Dictionary itemDict = new Dictionary();
            itemDict.setPid(dict.getId());
            itemDict.setName(name);
            itemDict.setNum(num);
            itemDict.setId(UUIDUtil.getUUID());
            i = dictionaryMapper.insertSelective(itemDict);
        }
		return i;
	}

	@Override
	public int editDict(String dictId, String dictName, String dictValues,String tips) {
		//删除之前的字典
		DictionaryExample example = new DictionaryExample();
        Criteria c= example.createCriteria();
        c.andPidEqualTo(dictId);
		int i = dictionaryMapper.deleteByExample(example);
		Dictionary record = new Dictionary();
		record.setId(dictId);
		if(!StringUtil.isEmpty(tips)){
			record.setTips(tips);
		}
		record.setName(dictName);
		i = dictionaryMapper.updateByPrimaryKeySelective(record);
        //重新添加新的字典
		JSONArray jsonArray = JSONArray.fromObject(dictValues);
        List<Dictionary> items = JSONArray.toList(jsonArray, Dictionary.class);// 过时方法
        for (Dictionary item : items) {
        	int num = item.getNum();
            String name = item.getName();
            Dictionary itemDict = new Dictionary();
            itemDict.setPid(dictId);
            itemDict.setName(name);
            itemDict.setNum(num);
            itemDict.setId(UUIDUtil.getUUID());
            i = dictionaryMapper.insertSelective(itemDict);
        }
		return i;
	}
	
}