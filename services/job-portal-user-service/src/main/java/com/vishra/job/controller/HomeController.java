package com.vishra.job.controller;

import com.vishra.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("role")
    public String homeController(){
        return "applicaiton run successfully" + UserRole
                .ROLE_ADMIN;
    }
}
