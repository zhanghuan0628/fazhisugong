package com.ffxl.admin.core.beetl;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import com.ffxl.admin.core.common.constant.factory.ConstantFactory;
import com.ffxl.admin.core.shiro.ShiroKit;
import com.ffxl.admin.core.util.DictKit;
import com.ffxl.admin.core.util.KaptchaUtil;
import com.ffxl.platform.util.ToolUtil;

/**
 * beetl拓展配置,绑定一些工具类,方便在模板中直接调用
 * 
 * @author jiawei
 * 2018年6月29日
 */
public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    @Override
    public void initOther() {
        groupTemplate.registerFunctionPackage("shiro", new ShiroKit());
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
        groupTemplate.registerFunctionPackage("kaptcha", new KaptchaUtil());
        groupTemplate.registerFunctionPackage("dict", new DictKit());
    }
}
