package com.zsw.springbootorderprovider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author zhangshiwei
 * @since 2020年9月16日 下午5:04:57
 */
@RestController
public class TestController {

    @RequestMapping("hello")
    public String hello() {
        return "hello world";
    }
}
