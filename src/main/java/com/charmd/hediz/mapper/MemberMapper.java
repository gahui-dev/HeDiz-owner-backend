package com.charmd.hediz.mapper;

import java.util.List;

import com.charmd.hediz.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {
	
	MemberDTO auth(MemberDTO userVo);
	
    List<MemberDTO> getUserList(); // User 테이블 가져오기
    void insertUser(MemberDTO userVo); // 회원 가입
    MemberDTO getUserByEmail(String email); // 회원 정보 가져오기
    MemberDTO getUserById(Long id);
    void updateUser(MemberDTO userVo); // 회원 정보 수정
    void deleteUser(String email); // 회원 탈퇴
    void deleteAll(); // 회원 모두 삭제
}