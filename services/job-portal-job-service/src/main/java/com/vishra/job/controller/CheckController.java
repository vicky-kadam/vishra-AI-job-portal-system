package com.vishra.job.controller;

import com.vishra.job.domain.UserRole;
import com.vishra.job.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CheckController {
    @GetMapping
    public ApiResponse CheckController(){
        return new ApiResponse("Applicaiton running successfully on 7003" + UserRole.ROLE_ADMIN, true);
    }
}
