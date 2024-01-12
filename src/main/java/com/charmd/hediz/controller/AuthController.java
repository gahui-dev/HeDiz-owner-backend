package com.charmd.hediz.controller;

import com.charmd.hediz.dto.MemberDTO;
import com.charmd.hediz.dto.TokenDTO;
import com.charmd.hediz.service.MemberService;
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

    }
}