package com.zsw.springbootorderprovider.rpc.annotation;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 初始化 Mediator
 *
 * @author zhangshiwei
 * @since 2020年9月15日 下午9:41:40
 */
@Component // 交给spring管理
public class InitMediator implements BeanPostProcessor {

    /**
     * bean装载完成运行方法
     *
     * @param bean 装载bean: 服务实现类
     * @param beanName bean名称
     * @return 结果
     * @throws BeansException 异常
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean.getClass().isAnnotationPresent(ZswRemoteService.class)) {
            System.out.println("InitMediator postProcessAfterInitialization - 初始化Mediator - bean装载完成之后运行方法");
            System.out.println("服务实现类上 加了服务发布标记: 自定义rpc注解ZswRemoteService");
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                // 接口全名 + . + 方法名
                String key = bean.getClass().getInterfaces()[0].getName() + "." + method.getName();
                System.out.println("Mediator.map.key :" + key);

                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);

                // 需要发布的服务方法的存储
                Mediator.map.put(key, beanMethod);
            }
        }
        return bean;
    }

}
