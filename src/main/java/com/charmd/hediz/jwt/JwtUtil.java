package com.charmd.hediz.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

<<<<<<< HEAD
// Json Web Token 발급 및 인증, 추출에 관련된 기능을 제공하는 유틸 클래스 
@Service
public class JwtUtil {
	
	//토큰 발급시 서명할 key
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	 public String extractEmail(String token) {
	    	System.out.println("JwtUtil.extractEmail");
	        return extractClaim(token, Claims::getSubject);
	    }
	
    public String extractUsername(String token) {
    	System.out.println("JwtUtil.extractUsername");
        return extractClaim(token, Claims::getSubject);
    }
 
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
 
=======
// Json Web Token 발급 및 인증, 추출에 관련된 기능을 제공하는 유틸 클래스
@Service
public class JwtUtil {

    //토큰 발급시 서명할 key
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String extractId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

>>>>>>> main
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
<<<<<<< HEAD
 
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    //토큰을 만들어서 리턴해주는 메소드 
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        //테스트로 추가 정보도 담아보기
//        claims.put("email", "naver@");
        claims.put("addr", "서울시 강남구");
        return createToken(claims, username);
    }
 
    private String createToken(Map<String, Object> claims, String subject) {
    	//JwtBuilder 객체를 이용해서 토큰을 만든다. 
    	
        return Jwts.builder()
        		.setClaims(claims)  //토큰에 담을 추가 정보
        		.setSubject(subject) //토큰의 주제(사용자명 or 사용자의 id or 기관명 or 기기명) 
        		.setIssuedAt(new Date(System.currentTimeMillis())) // 토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) //토큰 무효화 되는 시간 
                .signWith(SignatureAlgorithm.HS256, secret).compact(); // HS256 알고리즘으로 서명해서 토큰얻어내기
    }
    //토큰 유효성 여부를 리턴하는 메소드 
    public Boolean validateToken(String token, UserDetails userDetails) {
    	//토큰으로 부터 userName 를 얻어내서 
    	System.out.println("JwtUtil.validateToken");
    	System.out.println("UserDetails>>>>>>>>>>>>>>>"+ userDetails);
    	System.out.println("token>>>>>>>>>>>>>>>"+ token);
//        final String username = extractUsername(token);
        final String email = extractEmail(token);
        System.out.println("email>>>>>>>>>>>>>>>"+ email);
       
        //DB 에 저장된 userName 이고 토큰 유효기간이 만료가 안되었는지 확인해서 유효성 여부를 리턴한다. 
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }		
=======

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String createToken(String id, int shopSeq, String shopName) {
        //JwtBuilder 객체를 이용해서 토큰을 만든다.

        // id, shopSeq 해시맵에 저장
        Map<String, Object> claims = new HashMap<>();
        claims.put("shop_seq", shopSeq);
        claims.put("id", id);
        claims.put("shop_name", shopName);

        return Jwts.builder()
                .setClaims(claims)  //토큰에 담을 추가 정보
                .setSubject(id) //토큰의 주제(사용자명 or 사용자의 id or 기관명 or 기기명)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) //토큰 무효화 되는 시간
                .signWith(SignatureAlgorithm.HS256, secret).compact(); // HS256 알고리즘으로 서명해서 토큰얻어내기
    }

    //토큰 유효성 여부를 리턴하는 메소드
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String id = extractId(token);
        //DB 에 저장된 userName 이고 토큰 유효기간이 만료가 안되었는지 확인해서 유효성 여부를 리턴한다.
        return (id.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
>>>>>>> main
}
