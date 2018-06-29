package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgLawCommentMapper;
import com.ffxl.cloud.model.SgLawComment;
import com.ffxl.cloud.model.SgLawCommentExample;
import com.ffxl.cloud.model.base.BaseSgLawCommentExample.Criteria;
import com.ffxl.cloud.service.SgLawCommentService;
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
public class SgLawCommentServiceImpl extends GenericServiceImpl<SgLawComment, SgLawCommentExample, String> implements SgLawCommentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgLawCommentServiceImpl.class);

    @Autowired
    private SgLawCommentMapper sgLawCommentMapper;

    @Override
    public GenericMapper<SgLawComment, SgLawCommentExample, String> getGenericMapper() {
        return sgLawCommentMapper;
    }

    public SgLawComment queryByModel(SgLawComment sgLawComment) {
        SgLawCommentExample example = new SgLawCommentExample();
        Criteria c= example.createCriteria();
        List<SgLawComment> modelList =  sgLawCommentMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }
}