package com.charmd.hediz.auth;


import com.charmd.hediz.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.CookieRequestCache;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    //jwt 를 쿠키로 저장할때 쿠키의 이름
    @Value("${jwt.name}")
    private String jwtName;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           CookieRequestCache cookCache
    ) throws Exception {
        System.out.println("SecurityConfig.filterChain");
        // 권한에 따라 허용하는 url 설정
        // /login, /signup 페이지는 모두 허용, 다른 페이지는 인증된 사용자만 허용
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/login", "/signup").permitAll();
//                .antMatchers("/update/**").hasRole("ADMIN") // 수정은 ROLE_ADMIN 만 가능
//                .anyRequest().authenticated();


        //세션을 사용하지 않도록 설정한다.
        http
                .sessionManagement(config->config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //토큰을 검사하는 필터를 UsernamePasswordAuthenticationFilter 가 동작하기 이전에 동작하도록 설정 한다.
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                //세션을 사용할수 없기때문에 쿠키케시를 사용하도록 설정한다.
                .requestCache(config->config.requestCache(cookCache));

        return http.build();
    }

    //비밀번호를 암호화 해주는 객체를 bean 으로 만든다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //인증 메니저 객체를 bean 으로 만든다.
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService) throws Exception {
        System.out.println("SecurityConfig.authenticationManager>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    //쿠키 케시를 bean 으로 만든다.
    @Bean
    public CookieRequestCache getCookieRequestCache() {
        System.out.println("SecurityConfig.getCookieRequestCache>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
        return new CookieRequestCache();
    }


}