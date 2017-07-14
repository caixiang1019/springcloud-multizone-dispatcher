package com.cx.controller;

import com.cx.api.CityService;
import com.cx.dto.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by caixiang on 2017/7/13.
 */
@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    City getCityByOrderId(@RequestParam(value = "orderId") Long id) {
        return cityService.getCityDetailByOrderId(id);
    }
}
