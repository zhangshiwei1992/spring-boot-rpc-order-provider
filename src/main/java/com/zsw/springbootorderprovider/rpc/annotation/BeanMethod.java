package com.zsw.springbootorderprovider.rpc.annotation;

import java.lang.reflect.Method;

import lombok.Data;

/**
 * 发布实例,方法
 *
 * @author zhangshiwei
 * @since 2020年9月15日 下午9:35:30
 */
@Data
public class BeanMethod {
    /**
     * 发布实例
     */
    private Object bean;
    /**
     * 方法
     */
    private Method method;
}
