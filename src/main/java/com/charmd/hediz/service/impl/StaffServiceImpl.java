package com.charmd.hediz.service.impl;

import com.charmd.hediz.dao.StaffDAO;
import com.charmd.hediz.dto.StaffDTO;
import com.charmd.hediz.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("staffService")
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffDAO dao;

    // staff_pw 안맞아서 초기 설정을 해줌
    // 추후에 암호화 넣으면 삭제할 코드
//    @PostConstruct
//    public void initUsers(){
//        String samplePwd= new BCryptPasswordEncoder().encode("1234");
//        StaffDTO staffDto = new StaffDTO();
//        staffDto.setStaff_id("cc");
//        staffDto.setStaff_pw(samplePwd);
//        staffDto.setStaff_name("이가희어제술먹음");
//        staffDto.setStaff_role("디자이너");
//        staffDto.setStaff_image("img2");
//        staffDto.setStaff_phone("010-7593-6191");
//        staffDto.setStaff_intro("로그인테스트");
//        staffDto.setStaff_nickname("이주");
//        staffDto.setShop_seq(1);
//
////        dao.signUp(staffDto);
//    }
    @Override
    public List<StaffDTO> staffFindAll(int shopSeq) {
        return dao.staffFindAll(shopSeq);
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
