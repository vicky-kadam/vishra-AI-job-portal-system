package com.vishra.job.controller;

import com.vishra.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @GetMapping
    public String check(){
        return "This is out company service which is running successfully" + UserRole.ROLE_ADMIN;
    }
}
