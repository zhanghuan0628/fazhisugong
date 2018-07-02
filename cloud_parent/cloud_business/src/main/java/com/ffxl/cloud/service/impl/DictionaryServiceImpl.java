package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.DictionaryMapper;
import com.ffxl.cloud.model.Dictionary;
import com.ffxl.cloud.model.DictionaryExample;
import com.ffxl.cloud.model.base.BaseDictionaryExample.Criteria;
import com.ffxl.cloud.service.DictionaryService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.util.ToolUtil;

import java.util.List;

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
}