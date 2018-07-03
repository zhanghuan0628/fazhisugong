package com.ffxl.api.auth.validator;

import com.ffxl.api.auth.validator.dto.Credence;


/**
 * <p>验证请求/auth接口时,请求参数的正确性</p>
 * <p>
 * <p>如果想拓展验证方法只需实现这个接口,然后在AuthenticationRestController类中注意相应实现的本接口的类即可</p>
 * 
 * @author jiawei
 * 2018年7月2日
 */
public interface IReqValidator {

    /**
     * 通过请求参数验证
     * 
     * jaiwei
     * 2018年7月2日下午8:49:30
     * @param credence
     * @return
     */
    boolean validate(Credence credence);
}
