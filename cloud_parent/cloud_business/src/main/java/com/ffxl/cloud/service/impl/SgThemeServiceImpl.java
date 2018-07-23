package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgThemeMapper;
import com.ffxl.cloud.model.SgTheme;
import com.ffxl.cloud.model.SgThemeExample;
import com.ffxl.cloud.model.SgThmemeAnswerLogExample;
import com.ffxl.cloud.model.base.BaseSgThemeExample.Criteria;
import com.ffxl.cloud.service.SgThemeService;
import com.ffxl.cloud.service.SgThmemeAnswerLogService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
    @Autowired
    private SgThmemeAnswerLogService sgThemeAnswerLogService;

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

	@Override
	public List<SgTheme> queryPageList(SgTheme sgTheme, Page page) {
		if(sgTheme != null){
			if(!StringUtil.isEmpty(sgTheme.getStage())){
				String str = sgTheme.getStage();
				int n = Integer.parseInt(str);
				sgTheme.setNum(n);
			}
		}
		List<SgTheme> list = sgThemeMapper.queryPageList(sgTheme,page);
		for(SgTheme st:list){
			SgThmemeAnswerLogExample example = new SgThmemeAnswerLogExample();
	        com.ffxl.cloud.model.base.BaseSgThmemeAnswerLogExample.Criteria c= example.createCriteria();
	        c.andThemeIdEqualTo(st.getId());
	        int count = sgThemeAnswerLogService.countByExample(example);
			st.setStage("第"+st.getNum()+"期");
			st.setPersonNum(count);
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String n = sdf.format(now);
			try {
				Date start =  sdf.parse(st.getStartDate());
				Date endd =  sdf.parse(st.getEndDate());
				Date noww =  sdf.parse(n);
				if (noww.compareTo(endd)>0){  
			        st.setState("1");//活动已结束
				}else if(start.compareTo(noww)>0){
					st.setState("2");//活动未开始
				}else{
					st.setState("3");//活动进行中
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int selectMaxNum() {
		return sgThemeMapper.selectMaxNum();
	}

	@Override
	public SgTheme selectMaxEndDate(String num) {
		return sgThemeMapper.selectMaxEndDate(num);
	}
}