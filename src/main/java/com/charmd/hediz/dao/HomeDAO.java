package com.charmd.hediz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class HomeDAO {
    @Autowired
    SqlSessionTemplate session;

    public int updatePassword(HashMap<String, String> passwordMap){
        return session.update("com.config.HomeMapper.updatePassword", passwordMap);
    }
}
