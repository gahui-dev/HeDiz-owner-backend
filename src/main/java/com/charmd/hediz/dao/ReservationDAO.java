package com.charmd.hediz.dao;

import com.charmd.hediz.dto.ReservationDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ReservationDAO {
    @Autowired
    SqlSessionTemplate session;

    public List<ReservationDTO> reservationFindAll(){
        return session.selectList("com.config.ReservationMapper.reservationFindAll");
    }

    public int reservationUpdate(HashMap<String, Integer> reservationStatAndSeqMap){
        return session.update("com.config.ReservationMapper.reservationUpdate", reservationStatAndSeqMap);
    }

    public ReservationDTO reservationFind(int reservSeq){
        return session.selectOne("com.config.ReservationMapper.reservationFind", reservSeq);
    }

}
