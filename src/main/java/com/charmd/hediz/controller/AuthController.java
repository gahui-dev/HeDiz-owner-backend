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

    // 수정된 staff 관련 메서드
    @PostMapping("/sign-in")
    public ResponseEntity<TokenDTO> signIn(@RequestBody StaffDTO staffDto) {

        System.out.println("AuthController.login:>>>> " + staffDto);
        //사용자 인증정보 저장
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(staffDto.getStaff_id(), staffDto.getStaff_pw());

        System.out.println(authenticationToken);
        //성공적으로 인증된 사용자를 현재 스레드의 보안 컨텍스트에 설정, 스프링 시큐리티가 인식 가능
        System.out.println("디버그 3");

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("디버그1");
        // staff의 id를 받아와서 토큰 생성해주기
        String jwt = jwtUtil.generateToken(staffDto.getStaff_id());
        System.out.println("디버그2");
        System.out.println("AuthController.jwt: >>>" + jwt);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(tokenKey, "Bearer+" + jwt);

        System.out.println("AuthController.jwt: >>>" + jwt);
        System.out.println(httpHeaders);
        return new ResponseEntity<>(new TokenDTO("Bearer+" + jwt), httpHeaders, HttpStatus.OK);

    }

    @PostMapping("/sign-up")
    public ResponseEntity<HairshopDTO> signUp(@RequestBody HairshopDTO hairshopDto) { // 회원 가입
        // 입력된 dto 그대로 반환되기 때문에 따로 변수에 저장은 없음
        String newPw = new BCryptPasswordEncoder().encode(hairshopDto.getShop_pw());
        hairshopDto.setShop_pw(newPw);
        System.out.println("AuthController.signup: " + hairshopDto);
        authService.signUp(hairshopDto);
        return ResponseEntity.ok(hairshopDto);
    }
}