package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgLawMapper;
import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgLawExample;
import com.ffxl.cloud.model.SgUserFavoriteExample;
import com.ffxl.cloud.model.base.BaseSgLawExample.Criteria;
import com.ffxl.cloud.service.SgLawService;
import com.ffxl.cloud.service.SgUserFavoriteService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;

import java.text.SimpleDateFormat;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SgLawServiceImpl extends GenericServiceImpl<SgLaw, SgLawExample, String> implements SgLawService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgLawServiceImpl.class);

    @Autowired
    private SgLawMapper sgLawMapper;
    
    @Autowired
    private SgUserFavoriteService sgUserFavoriteService;

    @Override
    public GenericMapper<SgLaw, SgLawExample, String> getGenericMapper() {
        return sgLawMapper;
    }

    public SgLaw queryByModel(SgLaw sgLaw) {
        SgLawExample example = new SgLawExample();
        Criteria c= example.createCriteria();
        c.andTitleEqualTo(sgLaw.getTitle());
        c.andCategoryEqualTo(sgLaw.getCategory());
        List<SgLaw> modelList =  sgLawMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SgLaw> queryPageList(SgLaw sgLaw, Page page) {
		List<SgLaw> list = sgLawMapper.queryPageList(sgLaw, page);
		for(SgLaw sl:list){
			SgUserFavoriteExample example = new SgUserFavoriteExample();
	        com.ffxl.cloud.model.base.BaseSgUserFavoriteExample.Criteria c= example.createCriteria();
	        c.andSourceTypeEqualTo(sgLaw.getCategory());
	        c.andSourceIdEqualTo(sl.getId());
			int count = sgUserFavoriteService.countByExample(example);
			sl.setCount(count);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(sl.getCreateDate());
			sl.setCreateTime(dateString);
		}
		return list;
	}

	@Override
	public int updateSort(SgLaw bb) {
		return sgLawMapper.updateSort(bb);
	}

	@Override
	public int selectMaxSort(String id,String code, String category) {
		return sgLawMapper.selectMaxSort(id,code,category);
	}

	@Override
	public List<SgLaw> querySgLawByPage(String category, Page page) {
		return sgLawMapper.querySgLawByPage(category,page);
	}

	@Override
	public int selectMaxSortNum(String string) {
		return sgLawMapper.selectMaxSortNum(string);
	}

	@Override
	public SgLaw queryNextLawRoom(SgLaw sl) {
		return sgLawMapper.queryNextLawRoom(sl);
	}

	@Override
	public SgLaw queryPreLawRoom(SgLaw sl) {
		return sgLawMapper.queryPreLawRoom(sl);
	}

	@Override
	public SgLaw queryNextLawMagic(SgLaw sl) {
		return sgLawMapper.queryNextLawMagic(sl);
	}

	@Override
	public SgLaw queryPreLawMagic(SgLaw sl) {
		return sgLawMapper.queryPreLawMagic(sl);
	}

	@Override
	public List<SgLaw> queryRandLawRisk(SgLaw sgLaw) {
		return sgLawMapper.queryRandLawRisk(sgLaw);
	}
}