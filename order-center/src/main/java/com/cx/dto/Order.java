package com.cx.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by caixiang on 2017/7/13.
 */
@Data
@NoArgsConstructor
public class Order {

    private Long id;

    private Long userId;

    private BigDecimal price;

    private City city;

    public Order(Long id, Long userId, BigDecimal price, City city) {
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.city = city;
    }
}
