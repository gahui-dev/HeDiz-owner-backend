package com.charmd.hediz.service;

import com.charmd.hediz.dto.HairshopDTO;
import com.charmd.hediz.dto.TempdayDTO;

import java.util.List;

public interface HairshopService {
    public HairshopDTO hairshopFind(int shop_seq);
    public List<HairshopDTO> hairshopFindAll();
    public int hairshopUpdate(HairshopDTO putData);
    public int hairshopAdd(HairshopDTO postData);
    public int hairshopDelete(int shop_seq);

    List<TempdayDTO> tempdayFind(int shopSeq);
}
