package com.cx.api;


import com.cx.dto.City;

/**
 * Created by caixiang on 2017/7/13.
 */
public interface CityService {
    /**
     * 根据订单号获得City
     * @param orderId
     * @return
     */
    City getCityDetailByOrderId(Long orderId);
}
