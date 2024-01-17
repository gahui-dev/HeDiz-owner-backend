package com.charmd.hediz.controller;

import com.charmd.hediz.dto.CustomerDTO;
import com.charmd.hediz.dto.ReservationDTO;
import com.charmd.hediz.service.HomeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;

@Api
@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping("dashboard")
    public String dashboard(){
<<<<<<< HEAD
        return "가희 테스트";
=======

        return "대시보드 페이지11";
>>>>>>> main
    }

    @GetMapping("realtime-reservation")
    public String realtimeReservation(){
        return "권동혁 예약 페이지";
    }

    // 예약 상태 수정 (reserv_stat)
    @PutMapping("realtime-reservation/{reserv_seq}") // 수정 대기
    public ReservationDTO reservationUpdate(@RequestBody int reservStat, @PathVariable("reserv_seq") int reservSeq){
//        HashMap<String, Integer> reservationStatAndSeqMap = new HashMap<>();
//        reservationStatAndSeqMap.put("reserv_stat", reservStat);
//        reservationStatAndSeqMap.put("reserv_seq", reservSeq);
//        int n = reservationService.reservationUpdate(reservationStatAndSeqMap);
//        ReservationDTO reservationDTO = reservationService.reservationFind(reservSeq);
//        return reservationDTO;
        return null;
    }

    // 비밀번호 수정
    // shop_name, 수정 전 패스워드, 수정 후 패스워드 요청된다.
    @PostMapping("mypage/{shop_name}")
    public String mypage(@PathVariable("shop_name") String shopName, @RequestBody HashMap<String, String> passwordMap){
        passwordMap.put("shopName", shopName);
        System.out.println("해시맵 >>> " + passwordMap);
        int n = homeService.updatePassword(passwordMap);
        return n + "건 수정되었습니다.";
    }

}