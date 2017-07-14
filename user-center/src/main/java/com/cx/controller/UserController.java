package com.cx.controller;

import com.cx.api.UserService;
import com.cx.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Request;

/**
 * Created by caixiang on 2017/7/13.
 */
@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User getUserByUserId(@PathVariable(value = "id") Long userId){
        return userService.getUserById(userId);
    }
}
