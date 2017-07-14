package com.cx.api;

import com.cx.dto.City;
import com.cx.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by caixiang on 2017/7/13.
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    public static int times;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    @Override
    public Order getOrdersByUserId(Long userId) {

        //方便测试实例调用情况,实际应用到线上删除
        log.info("port: 《" + port + "》 is called ;" + (++times) + "次");

        //对外部无依赖:内部service查询到orderId
        Long orderId = 1000L;
        if (userId > 100) {
            orderId = 7L;
        }

        //对外部有依赖:从city-center获取city
        City city = restTemplate.getForObject("http://CITY/cities?orderId={1}", City.class, orderId);
        return new Order(orderId, userId, new BigDecimal("100.00"), city);
    }
}
