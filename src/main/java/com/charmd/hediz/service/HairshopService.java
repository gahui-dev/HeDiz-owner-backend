package com.charmd.hediz.service;

import com.charmd.hediz.dto.HairshopDTO;
import com.charmd.hediz.dto.TempdayDTO;

import java.util.List;

public interface HairshopService {
    public HairshopDTO hairshopFind(int shop_seq);
    public int hairshopUpdate(HairshopDTO putData);
    List<TempdayDTO> tempdayFind(int shopSeq);
    public int tempdayAdd(TempdayDTO postData);

}
