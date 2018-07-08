package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgBannerMapper;
import com.ffxl.cloud.model.SgBanner;
import com.ffxl.cloud.model.SgBannerExample;
import com.ffxl.cloud.model.base.BaseSgBannerExample.Criteria;
import com.ffxl.cloud.service.SgBannerService;
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
public class SgBannerServiceImpl extends GenericServiceImpl<SgBanner, SgBannerExample, String> implements SgBannerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgBannerServiceImpl.class);

    @Autowired
    private SgBannerMapper sgBannerMapper;

    @Override
    public GenericMapper<SgBanner, SgBannerExample, String> getGenericMapper() {
        return sgBannerMapper;
    }

    public SgBanner queryByModel(SgBanner sgBanner) {
        SgBannerExample example = new SgBannerExample();
        Criteria c= example.createCriteria();
        List<SgBanner> modelList =  sgBannerMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SgBanner> queryPageList(SgBanner sgBanner, Page page) {
		return sgBannerMapper.queryPageList(sgBanner, page);
	}

	@Override
	public int queryMaxNum() {
		return sgBannerMapper.queryMaxNum();
	}

	@Override
	public int updateSort(SgBanner sb) {
		return sgBannerMapper.updateSort(sb);
	}
}