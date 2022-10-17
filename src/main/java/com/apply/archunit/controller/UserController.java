package com.apply.archunit.controller;

import com.apply.archunit.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
