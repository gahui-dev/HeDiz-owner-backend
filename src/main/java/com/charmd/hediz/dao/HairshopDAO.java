package com.charmd.hediz.dao;

import com.charmd.hediz.dto.HairshopDTO;
import com.charmd.hediz.dto.TempdayDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HairshopDAO {

    @Autowired
    SqlSessionTemplate session;

    public HairshopDTO hairshopFind(int shop_seq){
        return session.selectOne("com.config.HairshopMapper.hairshopFind",shop_seq);
    }
    public List<HairshopDTO> hairshopFindAll(){
        return session.selectList("com.config.HairshopMapper.hairshopFindAll");
    }
    public int hairshopUpdate(HairshopDTO putData) {
        return session.update("com.config.HairshopMapper.hairshopUpdate", putData);
    }
    public int hairshopAdd(HairshopDTO postData) {return session.insert("com.config.HairshopMapper.hairshopAdd", postData);}
    public int hairshopDelete(int shop_seq){return session.delete("com.config.HairshopMapper.hairshopDelete", shop_seq);}

    public List<TempdayDTO> tempdayFind(int shop_seq) {
        return session.selectList("com.config.HairshopMapper.tempdayFind",shop_seq);
    }
}
