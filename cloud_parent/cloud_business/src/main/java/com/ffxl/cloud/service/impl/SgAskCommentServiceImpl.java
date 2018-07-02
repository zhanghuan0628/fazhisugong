package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgAskCommentMapper;
import com.ffxl.cloud.model.SgAskComment;
import com.ffxl.cloud.model.SgAskCommentExample;
import com.ffxl.cloud.model.base.BaseSgAskCommentExample.Criteria;
import com.ffxl.cloud.service.SgAskCommentService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SgAskCommentServiceImpl extends GenericServiceImpl<SgAskComment, SgAskCommentExample, String> implements SgAskCommentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgAskCommentServiceImpl.class);

    @Autowired
    private SgAskCommentMapper sgAskCommentMapper;

    @Override
    public GenericMapper<SgAskComment, SgAskCommentExample, String> getGenericMapper() {
        return sgAskCommentMapper;
    }

    public SgAskComment queryByModel(SgAskComment sgAskComment) {
        SgAskCommentExample example = new SgAskCommentExample();
        Criteria c= example.createCriteria();
        List<SgAskComment> modelList =  sgAskCommentMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }
}