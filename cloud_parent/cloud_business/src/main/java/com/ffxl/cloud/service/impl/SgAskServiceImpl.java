package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgAskMapper;
import com.ffxl.cloud.model.SgAsk;
import com.ffxl.cloud.model.SgAskExample;
import com.ffxl.cloud.model.SgLawComment;
import com.ffxl.cloud.model.SgLawCommentExample;
import com.ffxl.cloud.model.base.BaseSgAskExample.Criteria;
import com.ffxl.cloud.service.SgAskCommentService;
import com.ffxl.cloud.service.SgAskService;
import com.ffxl.cloud.service.SgLawCommentService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SgAskServiceImpl extends GenericServiceImpl<SgAsk, SgAskExample, String> implements SgAskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgAskServiceImpl.class);

    @Autowired
    private SgAskMapper sgAskMapper;
    
    @Autowired
	private SgAskCommentService sgAskCommentService;
    
    @Autowired
	private SgLawCommentService sgLawCommentService;

    @Override
    public GenericMapper<SgAsk, SgAskExample, String> getGenericMapper() {
        return sgAskMapper;
    }

    public SgAsk queryByModel(SgAsk sgAsk) {
        SgAskExample example = new SgAskExample();
        Criteria c= example.createCriteria();
        List<SgAsk> modelList =  sgAskMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SgAsk> queryPageList(SgAsk sgAsk, Page page) {
		return sgAskMapper.queryPageList(sgAsk, page);
	}

	@Override
	public SgAsk queryUserAsk(String id) {
		return sgAskMapper.queryUserAsk(id);
	}

	@Override
	public SgAsk queryUserAskById(String topicId) {
		SgAsk sgAsk = sgAskMapper.queryUserAskById(topicId);
		List list = sgAskCommentService.queryAllAskComment(topicId,null);
		if(list != null && list.size() > 0){
			sgAsk.setBackList(list);
		}
		return sgAsk;
	}

	@Override
	public List<SgAsk> queryMyAsk(String userId,Page page) {
		SgAskExample example = new SgAskExample();
        Criteria c= example.createCriteria();
        c.andUserIdEqualTo(userId);
        example.setOrderByClause(" create_date desc ");
        example.setPage(page);
		List<SgAsk> list = sgAskMapper.selectByExample(example);
		for(SgAsk sa:list){
			SgLawCommentExample e = new SgLawCommentExample();
	        com.ffxl.cloud.model.base.BaseSgLawCommentExample.Criteria cc= e.createCriteria();
	        cc.andTopicIdEqualTo(sa.getId());
	        cc.andTopicTypeEqualTo("ask");
	        List<SgLawComment> l = sgLawCommentService.selectByExample(e);
	        if(l!=null && l.size()>0){
	        	sa.setIsAnswer("1");//已回答
	        }else{
	        	sa.setIsAnswer("0");//未回答
	        }
		}
		return list;
	}
}