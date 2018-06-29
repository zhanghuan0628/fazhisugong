package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgThemeMapper;
import com.ffxl.cloud.model.SgTheme;
import com.ffxl.cloud.model.SgThemeExample;
import com.ffxl.cloud.model.base.BaseSgThemeExample.Criteria;
import com.ffxl.cloud.service.SgThemeService;
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
public class SgThemeServiceImpl extends GenericServiceImpl<SgTheme, SgThemeExample, String> implements SgThemeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgThemeServiceImpl.class);

    @Autowired
    private SgThemeMapper sgThemeMapper;

    @Override
    public GenericMapper<SgTheme, SgThemeExample, String> getGenericMapper() {
        return sgThemeMapper;
    }

    public SgTheme queryByModel(SgTheme sgTheme) {
        SgThemeExample example = new SgThemeExample();
        Criteria c= example.createCriteria();
        List<SgTheme> modelList =  sgThemeMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }
}