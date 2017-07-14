package com.cx.api;

import com.cx.dto.Order;

import java.util.List;

/**
 * Created by caixiang on 2017/7/13.
 */
public interface OrderService {

    Order getOrdersByUserId(Long userId);
}
