package com.charmd.hediz;

import com.charmd.hediz.dto.MemberDTO;
import com.charmd.hediz.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;


@SpringBootApplication
@PropertySource(value = "classpath:custom.properties")
public class HedizApplication extends SpringBootServletInitializer {


//	@Autowired
//	private MemberMapper userMapper;
//	@PostConstruct
//	public void initUsers() {
//		String samplePwd= new BCryptPasswordEncoder().encode("1234");
//
//		MemberDTO dto = new MemberDTO();
//		dto.setStaff_id("aaa15154");
//		dto.setStaff_pw(samplePwd);
//		dto.setStaff_name("양명진");
//		dto.setStaff_role("1");
//		dto.setStaff_image("img2");
//		dto.setStaff_phone("010-7593-6191");
//		dto.setStaff_intro("로그인테스트");
//		dto.setStaff_nickname("이주");
//		dto.setShop_seq("1");
//
//
//		userMapper.insertUser(dto);
//	}


	public static void main(String[] args) {

		SpringApplication.run(HedizApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(HedizApplication.class);
	}
}
