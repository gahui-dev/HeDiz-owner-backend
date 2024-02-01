package com.charmd.hediz.controller;

import com.charmd.hediz.dto.CustomerDTO;
import com.charmd.hediz.dto.ReservationDTO;
import com.charmd.hediz.service.HomeService;
import com.charmd.hediz.service.ReservationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("dashboard")
    public String dashboard(){

        //return "가희 테스트";


        //return "대시보드 페이지11";

        System.out.println("톰캣테스트");
        return "대시보드 페이123123지11";

    }

    // 현재 예약 상태 조회
    @GetMapping("realtime-reservation/{shop_seq}")
    public ResponseEntity<?> realtimeReservation(@PathVariable("shop_seq") int shopSeq){
        List<ReservationDTO> reservationList;
        reservationList = reservationService.realtimeFindAll(shopSeq);
        return ResponseEntity.ok().body(reservationList);
    }

    @GetMapping("realtime-reservation/{shop_seq}/current")
    public ResponseEntity<?> realtimeReservationCurrent(@PathVariable("shop_seq") int shopSeq){
        List<ReservationDTO> reservationList;
        reservationList = reservationService.realtimeFindCurrent(shopSeq);
        return ResponseEntity.ok().body(reservationList);
    }

    // 예약 상태 수정 (reserv_stat)
    @PutMapping("realtime-reservation/{reserv_seq}") // 수정 대기
    public ResponseEntity<?> reservationUpdate(@RequestBody int reservStat, @PathVariable("reserv_seq") int reservSeq){
//        HashMap<String, Integer> reservationStatAndSeqMap = new HashMap<>();
//        reservationStatAndSeqMap.put("reserv_stat", reservStat);
//        reservationStatAndSeqMap.put("reserv_seq", reservSeq);
//        int n = reservationService.reservationUpdate(reservationStatAndSeqMap);
//        ReservationDTO reservationDTO = reservationService.reservationFind(reservSeq);
//        return ResponseEntity.ok().body(reservationDTO);
        return null;
    }

    // 비밀번호 수정
    // shop_name, 수정 전 패스워드, 수정 후 패스워드 요청된다.
    @PostMapping("mypage/{shop_seq}")
    public ResponseEntity<?> mypage(@PathVariable("shop_seq") int shopSeq, @RequestBody HashMap<String, Object> passwordMap){
        passwordMap.put("shop_seq", shopSeq);
        // DB에서 실제 pw 가져오기
        String pw = homeService.getPw(shopSeq);
        // pw를 가지고 입력한 것과 비교(match)
        boolean matchPw = new BCryptPasswordEncoder().matches((CharSequence) passwordMap.get("before_password"), pw);
        // 일치한다면 비밀번호 변경
        if (matchPw){
            String afterPw = new BCryptPasswordEncoder().encode(passwordMap.get("after_password").toString());
            passwordMap.put("after_password", afterPw);
            int n = homeService.updatePassword(passwordMap);
            return ResponseEntity.ok().body(n==1);
        }else{
            return ResponseEntity.ok().body(false);
        }
    }
}