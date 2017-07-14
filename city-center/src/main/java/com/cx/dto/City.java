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
public class City {

    private Long id;

    private String name;

    private Long parentId;

    public City(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }
}
