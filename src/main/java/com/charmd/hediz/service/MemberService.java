package com.charmd.hediz.service;

import java.util.List;

import com.charmd.hediz.dto.MemberDTO;
import com.charmd.hediz.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class MemberService {

    @Autowired
    private MemberMapper userMapper;

    @PostConstruct
    public void initUsers() {
        String samplePwd= new BCryptPasswordEncoder().encode("1234");

        MemberDTO dto = new MemberDTO();
        dto.setStaff_id("aaa");
        dto.setStaff_pw(samplePwd);
        dto.setStaff_name("양명진");
        dto.setStaff_role("1");
        dto.setStaff_image("img2");
        dto.setStaff_phone("010-7593-6191");
        dto.setStaff_intro("로그인테스트");
        dto.setStaff_nickname("이주");
        dto.setShop_seq("1");


        userMapper.insertUser(dto);
    }

    public MemberDTO auth(MemberDTO dto) {
        return userMapper.auth(dto);
    }

    public List<MemberDTO> getUserList() {
        return userMapper.getUserList();
    }

    public MemberDTO getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    public MemberDTO getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    // 암호화 객체 생성
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void signup(MemberDTO memberDTO) { // 회원 가입
        // password는 암호화해서 DB에 저장
        memberDTO.setStaff_pw(passwordEncoder().encode(memberDTO.getStaff_pw()));
        userMapper.insertUser(memberDTO);

    }

    public void edit(MemberDTO memberDTO) { // 회원 정보 수정
        // password는 암호화해서 DB에 저장
        memberDTO.setStaff_pw(passwordEncoder().encode(memberDTO.getStaff_pw()));
        userMapper.updateUser(memberDTO);
    }

    public void delete(String email) { // 회원 탈퇴
        userMapper.deleteUser(email);
    }


}