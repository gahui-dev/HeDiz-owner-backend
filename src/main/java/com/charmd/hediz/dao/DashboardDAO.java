package com.charmd.hediz.dao;

import com.charmd.hediz.dto.DashboardDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DashboardDAO {
    @Autowired
    SqlSessionTemplate session;

    public List<DashboardDTO> countByReservationStatusPerDay(int shop_seq){
        return session.selectList("com.config.DashboardMapper.countByReservationStatusPerDay", shop_seq);
    }
}
