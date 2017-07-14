package com.cx.controller;

import com.cx.api.OrderService;
import com.cx.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caixiang on 2017/7/13.
 */
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    Order getOrderByUserId(@RequestParam(value = "userId") Long id) {
        return orderService.getOrdersByUserId(id);
    }

}
