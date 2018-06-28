package com.ffxl.admin.core.common.constant.dictmap.factory;

import java.lang.reflect.Method;

import com.ffxl.admin.core.common.constant.factory.ConstantFactory;
import com.ffxl.admin.core.common.constant.factory.IConstantFactory;
import com.ffxl.platform.core.exception.BusinessException;

/**
 * 字典字段的包装器(从ConstantFactory中获取包装值)
 * 
 * @author jiawei
 * 2018年6月28日
 */
public class DictFieldWarpperFactory {

    public static Object createFieldWarpper(Object parameter, String methodName) {
        IConstantFactory constantFactory = ConstantFactory.me();
        try {
            Method method = IConstantFactory.class.getMethod(methodName, parameter.getClass());
            return method.invoke(constantFactory, parameter);
        } catch (Exception e) {
            try {
                Method method = IConstantFactory.class.getMethod(methodName, Integer.class);
                return method.invoke(constantFactory, Integer.parseInt(parameter.toString()));
            } catch (Exception e1) {
                throw new BusinessException("包装字典属性失败");
            }
        }
    }

}
