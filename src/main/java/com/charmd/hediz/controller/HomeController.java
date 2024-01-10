package com.charmd.hediz.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("dashboard")
    public String dashboard(){
        return "대시보드 페이지";
    }

    @GetMapping("realtime-reservation")
    public String realtimeReservation(){
        return "실시간 예약 페이지";
    }

    @GetMapping("mypage")
    public String mypage(){
        return "마이페이지";
    }
}