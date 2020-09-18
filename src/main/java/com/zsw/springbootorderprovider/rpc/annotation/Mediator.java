package com.zsw.springbootorderprovider.rpc.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.zsw.example.RpcRequestDto;

/**
 * 存储发布的服务实例
 *
 * @author zhangshiwei
 * @since 2020年9月15日 下午9:33:10
 */
public class Mediator {

    /**
     * 存储发布的服务实例: 作为服务调度的路由
     */
    public static Map<String, BeanMethod> map = new ConcurrentHashMap<>();

    private Mediator() {
    }

    private volatile static Mediator instance;

    public static Mediator getInstance() {
        if (null == instance) {
            synchronized (Mediator.class) {
                if (null == instance) {
                    instance = new Mediator();
                }
            }
        }
        return instance;
    }

    public Object processor(RpcRequestDto rpcRequestDto) throws InvocationTargetException, IllegalAccessException {
        String key = rpcRequestDto.getClassName() + "." + rpcRequestDto.getMethodName();
        BeanMethod beanMethod = map.get(key);

        if (null != beanMethod) {
            Object bean = beanMethod.getBean();
            Method method = beanMethod.getMethod();
            return method.invoke(bean, rpcRequestDto.getArgs());
        }

        return null;
    }

}
