package com.charmd.hediz.dao;

import com.charmd.hediz.dto.HairshopDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthDAO {
    @Autowired
    SqlSessionTemplate session;

    public void signUp(HairshopDTO hairshopDto){
        session.insert("com.config.AuthMapper.signUp", hairshopDto);
    }

    public int duplicateCheck(String shopId){
        return session.selectOne("com.config.AuthMapper.duplicateCheck", shopId);
    }

    public HairshopDTO getUserById(String shopId){
        return session.selectOne("com.config.AuthMapper.getUserById", shopId);
    }

    public String findId(String shopRegister){
        return session.selectOne(("com.config.AuthMapper.findId"), shopRegister);
    }
}
