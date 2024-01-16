package com.charmd.hediz.service.impl;

import com.charmd.hediz.dao.AuthDAO;
import com.charmd.hediz.dto.HairshopDTO;
import com.charmd.hediz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthDAO dao;
    @Override
    public void signUp(HairshopDTO hairshopDto) {
        dao.signUp(hairshopDto);
    }

    @Override
    public int duplicateCheck(String shopId) {
        return dao.duplicateCheck(shopId);
    }
}
