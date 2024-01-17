package com.charmd.hediz.service;

import com.charmd.hediz.dto.HairshopDTO;

public interface AuthService {
    public void signUp(HairshopDTO hairshopDto);
    public int duplicateCheck(String shopId);
    public HairshopDTO getUserById(String shopId);
    public String findId(String shopRegister);
}
