package com.charmd.hediz.controller;

import com.charmd.hediz.dto.HairshopDTO;
import com.charmd.hediz.service.HairshopService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/hairshop")
public class HairshopController {
    @Autowired
    private HairshopService hairshopService;

    // 모든 미용실 정보 조회
    @GetMapping("info")
    public List<HairshopDTO> allHairshopSelect() {
        List<HairshopDTO> hairshopList;
        hairshopList = hairshopService.hairshopFindAll();
        System.out.println(hairshopList);
        return hairshopList;
    }

    // 특정 미용실 정보 조회
    @GetMapping("info/{shop_seq}")
    public HairshopDTO hairshopSelect(@PathVariable("shop_seq") int shopSeq) {
        HairshopDTO hairshopDto = hairshopService.hairshopFind(shopSeq);
        System.out.println("조회할 미용실 식별자 > " + shopSeq);
        System.out.println("식별자로 식별한 미용실 정보 > " + hairshopDto);
        return hairshopDto;
    }

    // 미용실 데이터 수정
    @PutMapping("info")
    public String hairshopUpdate(@RequestBody HairshopDTO putData) {
        int n = hairshopService.hairshopUpdate(putData);
//        System.out.println(putData);
        System.out.println(n + " " + putData);
        return "미용실 수정 완료";
    }

    // 미용실 데이터 삭제
    @DeleteMapping("info/{shop_seq}")
    public String hairshopDelete(@PathVariable("shop_seq") int shopSeq) {
        int n = hairshopService.hairshopDelete(shopSeq);
        return "미용실 삭제 완료";
    }
}
