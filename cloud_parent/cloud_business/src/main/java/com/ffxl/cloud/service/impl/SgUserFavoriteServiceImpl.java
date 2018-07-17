package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgUserFavoriteMapper;
import com.ffxl.cloud.model.SgUserFavorite;
import com.ffxl.cloud.model.SgUserFavoriteExample;
import com.ffxl.cloud.model.base.BaseSgUserFavoriteExample.Criteria;
import com.ffxl.cloud.service.SgUserFavoriteService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SgUserFavoriteServiceImpl extends GenericServiceImpl<SgUserFavorite, SgUserFavoriteExample, String> implements SgUserFavoriteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgUserFavoriteServiceImpl.class);

    @Autowired
    private SgUserFavoriteMapper sgUserFavoriteMapper;

    @Override
    public GenericMapper<SgUserFavorite, SgUserFavoriteExample, String> getGenericMapper() {
        return sgUserFavoriteMapper;
    }

    public SgUserFavorite queryByModel(SgUserFavorite sgUserFavorite) {
        SgUserFavoriteExample example = new SgUserFavoriteExample();
        Criteria c= example.createCriteria();
        List<SgUserFavorite> modelList =  sgUserFavoriteMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SgUserFavorite> queryMyFavorite(String userId, String sourceType,Page page) {
		return sgUserFavoriteMapper.queryMyFavorite(userId,sourceType,page);
	}
}