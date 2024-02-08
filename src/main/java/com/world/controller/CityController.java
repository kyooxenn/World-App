package com.world.controller;


import com.world.request.BaseRequest;
import com.world.services.iface.CityService;
import com.world.vo.ResponseHelper;
import com.world.vo.ResponseVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin()
@RequestMapping("/api/world")
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping("/city")
    public ResponseVO getAllCity(@RequestBody BaseRequest request){
        return ResponseHelper.success(cityService.getAllCity(request));
    }



}
