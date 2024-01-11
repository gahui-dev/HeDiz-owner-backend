package com.charmd.hediz.service;

import com.charmd.hediz.dto.HairstyleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("hairstyleService")
public interface HairstyleService {
    public List<HairstyleDTO> hairstyleFindAll();
    public HairstyleDTO hairstyleFind(int style_seq);
    public int hairstyleAdd(HairstyleDTO postData);
    public int hairstyleUpdate(HairstyleDTO putData);
    public int hairstyleDelete(int style_seq);
}
