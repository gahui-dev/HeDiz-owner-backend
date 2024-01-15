package com.charmd.hediz.dao;

import com.charmd.hediz.dto.HairshopDTO;
import com.charmd.hediz.dto.StaffDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class StaffDAO {
    @Autowired
    SqlSessionTemplate session;

    public StaffDTO staffFind(int staff_seq){
        return session.selectOne("com.config.StaffMapper.staffFind",staff_seq);
    }
    public List<StaffDTO> staffFindAll(){
        return session.selectList("com.config.StaffMapper.staffFindAll");
    }
    public int staffUpdate(StaffDTO putData) {
        System.out.println("수정 데이터 >" + putData);
        return session.update("com.config.StaffMapper.staffUpdate", putData);
    }
    public int staffAdd(StaffDTO postData) {return session.insert("com.config.StaffMapper.staffAdd", postData);}
    public int staffDelete(int staff_seq){return session.delete("com.config.StaffMapper.staffDelete", staff_seq);}

    public StaffDTO getUserById(String staffId){
        return session.selectOne("com.config.StaffMapper.getUserById", staffId);
    }
    public void signUp(HairshopDTO hairshopDto){
        session.insert("com.config.StaffMapper.signUp", hairshopDto);
    }

    // shop_code를 이용해서 shop_name, shop_seq 얻어오기
    public HashMap<String, Object> findShopSeqAndShopNameUsingShopCode(String shopCode){
        HashMap<String, Object> shopSeqAndShopNameMap = session.selectOne("com.config.StaffMapper.findShopSeqAndShopNameUsingShopCode", shopCode);
        return shopSeqAndShopNameMap;
    }
}
