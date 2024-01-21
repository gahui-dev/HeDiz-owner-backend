package com.charmd.hediz.service.impl;

import com.charmd.hediz.dao.ReservationDAO;
import com.charmd.hediz.dto.ReservationDTO;
import com.charmd.hediz.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service("reservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    ReservationDAO dao;
    @Override
    public List<ReservationDTO> reservationFindAll(int shopSeq) {

        return dao.reservationFindAll(shopSeq);
    }

    @Override
    public List<ReservationDTO> realtimeFindAll(int shopSeq) {
        return dao.realtimeFindAll(shopSeq);
    }

    @Override
    public int reservationUpdate(HashMap<String, Integer> reservationStatAndSeqMap) {
        return dao.reservationUpdate(reservationStatAndSeqMap);
    }

    @Override
    public ReservationDTO reservationFind(int reservSeq) {
        return dao.reservationFind(reservSeq);
    }
}
