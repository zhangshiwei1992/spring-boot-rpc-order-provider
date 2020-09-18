package com.zsw.springbootorderprovider.bill;

import com.zsw.example.springbootrpcorderapi.rpc.BillService;
import com.zsw.springbootorderprovider.rpc.annotation.ZswRemoteService;

/**
 * bill 账单实现类
 *
 * @author zhangshiwei
 * @since 2020年9月15日 下午11:19:55
 */
@ZswRemoteService // 自定义rpc注解: 自动发布
public class BillServiceImpl implements BillService {
    @Override
    public String findBillById(Long id) {
        return "BillServiceImpl findBillById id: " + id + " 查询结果: bill";
    }

    @Override
    public String findListByOrderCode(String orderCode) {
        return "BillServiceImpl findBillById orderCode: " + orderCode + " 查询结果: billList";
    }
}
