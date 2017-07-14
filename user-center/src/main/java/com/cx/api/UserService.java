package com.cx.api;

import com.cx.dto.User;

/**
 * Created by caixiang on 2017/7/13.
 */
public interface UserService {

    User getUserById(Long userId);
}
