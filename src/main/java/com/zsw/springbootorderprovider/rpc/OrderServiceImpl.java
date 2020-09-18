package com.zsw.springbootorderprovider.rpc;

import com.zsw.example.OrderService;
import com.zsw.springbootorderprovider.rpc.annotation.ZswRemoteService;

/**
 * OrderServiceImpl 订单实现类
 *
 * @author zhangshiwei
 * @since 2020年9月15日 下午1:12:25
 */
@ZswRemoteService // rpc自定义注解,自动发布此实现类
public class OrderServiceImpl implements OrderService {
    @Override
    public String findOrderList() {
        return "OrderServiceImpl findOrderList 这就是 orderList !";
    }

    @Override
    public String findOrderById() {
        return "OrderServiceImpl findOrderById 这就是 order !";
    }
}
