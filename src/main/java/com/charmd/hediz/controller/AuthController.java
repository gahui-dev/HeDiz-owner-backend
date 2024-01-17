package com.charmd.hediz.controller;

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

    // id 중복확인
    @PostMapping("/duplicate-check")
    public boolean duplicateCheck(@RequestBody HashMap<String, String> shopIdMap) {
        String shopId = shopIdMap.get("shop_id");
        if (shopId == null || shopId.trim().isEmpty()) {
            return false;
        }
        int n = authService.duplicateCheck(shopId);
        System.out.println("id 개수 : " + n);
        return n == 0;
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<HairshopDTO> signUp(@RequestBody HairshopDTO hairshopDto) {
        String newPw = new BCryptPasswordEncoder().encode(hairshopDto.getShop_pw());
        hairshopDto.setShop_pw(newPw);
        authService.signUp(hairshopDto);
        return ResponseEntity.ok(hairshopDto);
    }

    // ID 찾기
    @PostMapping("/find-id")
    public String findId(@RequestBody HashMap<String, String> shopRegisterMap){
        String shopRegister = shopRegisterMap.get("shop_register");

        // 값 보내야함
        // 뭘로 확인해야 하나,,,, select shop_id from t_hairshop where shop_register=#{shop_register}
        // 쿼리 결과가 1이어야 한다.
        String id = authService.findId(shopRegister);
        return id;
    }
}