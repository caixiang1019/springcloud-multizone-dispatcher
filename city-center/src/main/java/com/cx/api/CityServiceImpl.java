package com.cx.api;

import com.cx.dto.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by caixiang on 2017/7/13.
 */
@Slf4j
@Service
public class CityServiceImpl implements CityService {

    public static int times;

    @Value("${server.port}")
    private String port;
    @Override
    public City getCityDetailByOrderId(Long orderId) {
        //方便测试实例调用情况,实际应用到线上删除
        log.info("port: 《" + port + "》 is called ;" + (++times) + "次");
        return new City(orderId + 10, "上海", orderId + 20);
    }
}
