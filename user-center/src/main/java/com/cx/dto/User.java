package com.cx.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by caixiang on 2017/7/13.
 */
@Data
@NoArgsConstructor
public class User {
    private Long id;
    private Order order;

    public User(Long id, Order order) {
        this.id = id;
        this.order = order;
    }
}
