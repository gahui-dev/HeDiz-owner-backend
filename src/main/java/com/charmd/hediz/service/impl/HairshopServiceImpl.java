package com.charmd.hediz.service.impl;

import com.charmd.hediz.dao.HairshopDAO;
import com.charmd.hediz.dto.HairshopDTO;
import com.charmd.hediz.service.HairshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("hairshopService")
public class HairshopServiceImpl implements HairshopService{
    @Autowired
    HairshopDAO dao;

    @Override
    public HairshopDTO hairshopFind(int shop_seq) {
        return dao.hairshopFind(shop_seq);
    }

    // hairshop 관련 service
    @Override
    public List<HairshopDTO> hairshopFindAll() {
        return dao.hairshopFindAll();
    }

    @Override
    public int hairshopUpdate(HairshopDTO putData) {
        return dao.hairshopUpdate(putData);
    }

    @Override
    public int hairshopAdd(HairshopDTO postData) {
        return dao.hairshopAdd(postData);
    }

    @Override
    public int hairshopDelete(int shop_seq) {
        return dao.hairshopDelete(shop_seq);
    }
}
