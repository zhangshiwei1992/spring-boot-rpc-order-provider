package com.zsw.springbootorderprovider.rpc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * rpc 自定义注解, 被注解的实现类自动发布
 *
 * @author zhangshiwei
 * @since 2020年9月15日 下午3:39:48
 */
@Retention(RetentionPolicy.RUNTIME) // 注解保留的时间, RetentionPolicy.RUNTIME表示运行时保留
@Target(ElementType.TYPE) // 注解的作用范围, ElementType.TYPE表示作用于类/接口/枚举
@Component // 表明此注解需要被spring扫描到
public @interface ZswRemoteService {
}
