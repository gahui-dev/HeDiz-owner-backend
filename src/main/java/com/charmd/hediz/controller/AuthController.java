package com.charmd.hediz.controller;


import com.charmd.hediz.dto.MemberDTO;
import com.charmd.hediz.dto.TokenDTO;
import com.charmd.hediz.service.MemberService;

import com.charmd.hediz.dto.HairshopDTO;
import com.charmd.hediz.dto.StaffDTO;
import com.charmd.hediz.dto.TokenDTO;
import com.charmd.hediz.service.AuthService;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.charmd.hediz.jwt.JwtUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.charmd.hediz.jwt.JwtUtil;

import java.util.HashMap;

@Api
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired
    JwtUtil jwtUtil;
    @Value("${jwt.name}")
    String tokenKey;

    @Autowired
    private MemberService userService;

    @GetMapping("dashboard")
    public String dashboard(){
        return "가희 테스트";
    }

    @GetMapping("realtime-reservation")
    public String realtimeReservation(){
        return "권동혁 예약 페이지";
    }

    @GetMapping("mypage")
    public String mypage(){
        return "박찬웅 마이페이지";
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenDTO> signIn(@RequestBody MemberDTO dto) {

        System.out.println("MemberController.login:>>>> " + dto);
        //사용자 인증정보 저장
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getStaff_id(), dto.getStaff_pw());

        System.out.println(authenticationToken);
        //성공적으로 인증된 사용자를 현재 스레드의 보안 컨텍스트에 설정, 스프링 시큐리티가 인식 가능
        System.out.println("디버그 3");
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("디버그1");
        // 사용자의 이메일을 받아와서 토큰 생성해주기
        String jwt = jwtUtil.generateToken(dto.getStaff_id());
        System.out.println("디버그2");
        System.out.println("MemberController.jwt: >>>" + jwt);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(tokenKey, "Bearer+" + jwt);

        System.out.println("MemberController.jwt: >>>" + jwt);
        System.out.println(httpHeaders);
        return new ResponseEntity<>(new TokenDTO("Bearer+" + jwt), httpHeaders, HttpStatus.OK);


    private AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<TokenDTO> signIn(@RequestBody HashMap<String, String> signInMap) {
        // id 값
        String id = signInMap.get("shop_id");
        String pw = signInMap.get("shop_pw");
        //사용자 인증정보 저장
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(id, pw);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        int shopSeq = authService.getUserById(id).getShop_seq();
        String shopName = authService.getUserById(id).getShop_name();
        String jwt = jwtUtil.createToken(id, shopSeq, shopName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(tokenKey, "Bearer+" + jwt);
        return new ResponseEntity<>(new TokenDTO("Bearer+" + jwt, shopSeq, shopName), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/duplicate-check")
    public ResponseEntity<?> duplicateCheck(@RequestBody HashMap<String, String> shopIdMap) {
        String shopId = shopIdMap.get("shop_id");
        if (shopId == null || shopId.trim().isEmpty()) {
            return ResponseEntity.ok().body(false);
        }
        int n = authService.duplicateCheck(shopId);
        System.out.println("id 개수 : " + n);
        return ResponseEntity.ok().body(n == 0);
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody HairshopDTO hairshopDto) {
        String newPw = new BCryptPasswordEncoder().encode(hairshopDto.getShop_pw());
        hairshopDto.setShop_pw(newPw);
        authService.signUp(hairshopDto);
        return ResponseEntity.ok().body(hairshopDto);
    }

    // ID 찾기
    @PostMapping("/find-id")
    public ResponseEntity<?> findId(@RequestBody HashMap<String, String> shopRegisterMap) {
        String shopRegister = shopRegisterMap.get("shop_register");
        String id = authService.findId(shopRegister);

        return id;

        return ResponseEntity.ok().body(id);

    }

    // shop_id, shop_name을 통해 계정있는지 확인
    @PostMapping("/check-password")
    public ResponseEntity<?> checkPassword(@RequestBody HashMap<String, String> shopIdAndNameMap) {
        int n = authService.checkPassword(shopIdAndNameMap);
        return ResponseEntity.ok().body(n == 1);
    }

    // 비밀번호 변경
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody HashMap<String, String> shopPwMap) {
        String newPw = new BCryptPasswordEncoder().encode(shopPwMap.get("shop_pw"));
        shopPwMap.put("shop_pw", newPw);
        int n = authService.changePassword(shopPwMap);
        if (n == 1) return ResponseEntity.ok().body(n);
        else return ResponseEntity.ok().body(n);
    }
}