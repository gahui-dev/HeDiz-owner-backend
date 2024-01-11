package com.charmd.hediz.dao;

import com.charmd.hediz.dto.HairstyleDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HairstyleDAO {
    @Autowired
    SqlSessionTemplate session;

    public List<HairstyleDTO> hairstyleFindAll() {
        return session.selectList("com.config.HairstyleMapper.hairstyleFindAll");
    }

    public HairstyleDTO hairstyleFind(int style_seq) {
        return session.selectOne("com.config.HairstyleMapper.hairstyleFind", style_seq);
    }

    public int hairstyleAdd(HairstyleDTO postData) {
        return session.insert("com.config.HairstyleMapper.hairstyleAdd", postData);
    }

    public int hairstyleUpdate(HairstyleDTO putData){
        return session.update("com.config.HairstyleMapper.hairstyleUpdate", putData);
    }

    public int hairstyleDelete(int style_seq){
        return session.delete("com.config.HairstyleMapper.hairstyleDelete", style_seq);
    }
}
