package com.charmd.hediz.controller;

import com.charmd.hediz.dto.HairshopDTO;
import com.charmd.hediz.dto.HairstyleDTO;
import com.charmd.hediz.dto.StaffDTO;
import com.charmd.hediz.service.HairshopService;
import com.charmd.hediz.service.HairstyleService;
import com.charmd.hediz.service.StaffService;
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

    @Autowired
    private HairstyleService hairstyleService;

    @Autowired
    private StaffService staffService;

    /*
    * 미용실 정보 (/info)
    * */
    // 모든 미용실 조회
    @GetMapping("info")
    public List<HairshopDTO> allHairshopSelect() {
        List<HairshopDTO> hairshopList;
        hairshopList = hairshopService.hairshopFindAll();
        System.out.println(hairshopList);
        return hairshopList;
    }

    // 특정 미용실 조회
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

    /*
     * 헤어스타일 (/hairstyle)
     * */
    // 모든 헤어스타일 조회
    @GetMapping("hairstyle")
    public List<HairstyleDTO> allHairstyleSelect() {
        List<HairstyleDTO> hairList;
        hairList = hairstyleService.hairstyleFindAll();
        return hairList;
    }

    // 특정 헤어스타일 조회
    @GetMapping("hairstyle/{style_seq}")
    public HairstyleDTO hairstyleSelect(@PathVariable("style_seq") int styleSeq) {
        HairstyleDTO hairstyleDto = hairstyleService.hairstyleFind(styleSeq);
        return hairstyleDto;
    }

    // 헤어스타일 데이터 추가
    @PostMapping("hairstyle")
    public String hairstyleInsert(@RequestBody HairstyleDTO postData) {
        int n = hairstyleService.hairstyleAdd(postData);
        return "헤어스타일 추가 완료";
    }

    // 헤어스타일 데이터 수정
    @PutMapping("hairstyle/{style_seq}")
    public String hairstyleUpdate(@RequestBody HairstyleDTO putData) {
        int n = hairstyleService.hairstyleUpdate(putData);
        return "헤어스타일 수정 완료";
    }

    // 헤어스타일 데이터 삭제
    @DeleteMapping("hairstyle/{style_seq}")
    public String hairstyleDelete(@PathVariable("style_seq") int styleSeq) {
        int n = hairstyleService.hairstyleDelete(styleSeq);
        return "헤어스타일 삭제 완료";
    }

    /*
     * 직원 관리 (/staff)
     * */
    // 모든 직원 조회
    @GetMapping("staff")
    public List<StaffDTO> allStaffSelect() {
        List<StaffDTO> staffList;
        staffList = staffService.staffFindAll();
        System.out.println(staffList);
        return staffList;
    }
    
    // 특정 직원 정보 조회
    @GetMapping("staff/{staff_seq}")
    public StaffDTO staffSelect(@PathVariable("staff_seq") int staffSeq) {
        StaffDTO staffDto = staffService.staffFind(staffSeq);
        return staffDto;
    }
    // 헤어스타일 데이터 추가
    @PostMapping("staff")
    public String staffInsert(@RequestBody StaffDTO postData) {
        int n = staffService.staffAdd(postData);
        return "직원 추가 완료";
    }

    // 직원 데이터 수정
    @PutMapping("staff/{staff_seq}")
    public String staffUpdate(@RequestBody StaffDTO putData) {
        int n = staffService.staffUpdate(putData);
        return "직원 수정 완료";
    }


    // 직원 데이터 삭제
    @DeleteMapping("staff/{staff_seq}")
    public String staffDelete(@PathVariable("staff_seq") int staffSeq) {
        int n = staffService.staffDelete(staffSeq);
        return "직원 삭제 완료";
    }

    /*
     * 임시 휴무일 (/closed-day)
     * */

}
