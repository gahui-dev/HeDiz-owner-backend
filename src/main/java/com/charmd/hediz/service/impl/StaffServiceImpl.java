package com.charmd.hediz.service.impl;

import com.charmd.hediz.dao.StaffDAO;
import com.charmd.hediz.dto.MemberDTO;
import com.charmd.hediz.dto.StaffDTO;
import com.charmd.hediz.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("staffService")
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffDAO dao;

    // staff_pw 안맞아서 초기 설정을 해줌
    // 추후에 암호화 넣으면 삭제할 코드
    @PostConstruct
    public void initUsers(){
        String samplePwd= new BCryptPasswordEncoder().encode("1234");
        MemberDTO dto = new MemberDTO();
        dto.setStaff_id("bbb");
        dto.setStaff_pw(samplePwd);
        dto.setStaff_name("양명진");
        dto.setStaff_role("1");
        dto.setStaff_image("img2");
        dto.setStaff_phone("010-7593-6191");
        dto.setStaff_intro("로그인테스트");
        dto.setStaff_nickname("이주");
        dto.setShop_seq("1");
    }
    @Override
    public StaffDTO staffFind(int staff_seq) {
        return dao.staffFind(staff_seq);
    }

    @Override
    public List<StaffDTO> staffFindAll() {
        return dao.staffFindAll();
    }

    @Override
    public int staffUpdate(StaffDTO putData) {
        return dao.staffUpdate(putData);
    }

    @Override
    public int staffAdd(StaffDTO postData) {
        return dao.staffAdd(postData);
    }

    @Override
    public int staffDelete(int staff_seq) {
        return dao.staffDelete(staff_seq);
    }
}
