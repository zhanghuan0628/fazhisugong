package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgUserMapper;
import com.ffxl.cloud.model.SgUser;
import com.ffxl.cloud.model.SgUserExample;
import com.ffxl.cloud.model.base.BaseSgUserExample.Criteria;
import com.ffxl.cloud.service.SgUserService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;
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
public class SgUserServiceImpl extends GenericServiceImpl<SgUser, SgUserExample, String> implements SgUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgUserServiceImpl.class);

    @Autowired
    private SgUserMapper sgUserMapper;

    @Override
    public GenericMapper<SgUser, SgUserExample, String> getGenericMapper() {
        return sgUserMapper;
    }

    public SgUser queryByModel(SgUser sgUser) {
        SgUserExample example = new SgUserExample();
        Criteria c= example.createCriteria();
        if(ToolUtil.isNotEmpty(sgUser.getLoginName())){
            c.andLoginNameEqualTo(sgUser.getLoginName());
        }
        List<SgUser> modelList =  sgUserMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SgUser> queryPageList(SgUser sgUser, Page page) {
		return sgUserMapper.queryPageList(sgUser, page);
	}

	@Override
	public int queryMyInfo(String userId) {
		return sgUserMapper.queryMyInfo(userId);
	}

	@Override
	public List<SgUser> queryMyInfoList(String userId,Page page) {
		return sgUserMapper.queryMyInfoList(userId,page);
	}

	@Override
	public int updateTokenNull(String userId) {
		return sgUserMapper.updateTokenNull(userId);
	}
}