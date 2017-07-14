package com.cx.api;

import com.cx.dto.Order;
import com.cx.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by caixiang on 2017/7/13.
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    public static int times;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    @Override
    public User getUserById(Long userId) {

        //方便测试实例调用情况,实际应用到线上删除
        log.info("port: 《" + port + "》 is called ;" + (++times) + "次");
        //依赖外部服务：从order-center获取order
        Order order = restTemplate.getForObject("http://ORDER/orders?userId={1}", Order.class, userId);
        return new User(userId, order);
    }
}
