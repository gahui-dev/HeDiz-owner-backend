package com.charmd.hediz.controller;

import com.charmd.hediz.dto.HairshopDTO;
import com.charmd.hediz.dto.HairstyleDTO;
import com.charmd.hediz.dto.StaffDTO;
import com.charmd.hediz.dto.TempdayDTO;
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

    // 특정 미용실 조회
    @GetMapping("info/{shop_seq}")
    public HairshopDTO hairshopSelect(@PathVariable("shop_seq") int shopSeq) {
        HairshopDTO hairshopDto = hairshopService.hairshopFind(shopSeq);
        return hairshopDto;
    }

    // 미용실 데이터 수정
    @PutMapping("info")
    public String hairshopUpdate(@RequestBody HairshopDTO putData) {
        int n = hairshopService.hairshopUpdate(putData);
        return "미용실 수정 완료";
    }

    /*
     * 헤어스타일 (/hairstyle)
     * */
    // 모든 헤어스타일 조회
    @GetMapping("hairstyle/{shop_seq}")
    public List<HairstyleDTO> allHairstyleSelect(@PathVariable("shop_seq") int shopSeq) {
        List<HairstyleDTO> hairList;
        hairList = hairstyleService.hairstyleFindAll(shopSeq);
        return hairList;
    }

    // 헤어스타일 데이터 추가
    @PostMapping("hairstyle")
    public String hairstyleInsert(@RequestBody HairstyleDTO postData) {
        int n = hairstyleService.hairstyleAdd(postData);
        return "헤어스타일 추가 완료";
    }

    // 헤어스타일 데이터 수정
    @PutMapping("hairstyle")
    // pathvariable 추가
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
    @GetMapping("staff/{shop_seq}")
    public List<StaffDTO> allStaffSelect(@PathVariable("shop_seq") int shopSeq) {
        List<StaffDTO> staffList;
        staffList = staffService.staffFindAll(shopSeq);
        return staffList;
    }

    // 직원 데이터 수정
    @PutMapping("staff")
    public String staffUpdate(@RequestBody StaffDTO putData) {
        int n = staffService.staffUpdate(putData);
        return "직원 수정 완료";
    }

    // 직원 데이터 추가
    @PostMapping("staff")
    public String staffInsert(@RequestBody StaffDTO postData) {
        int n = staffService.staffAdd(postData);
        return "직원 추가 완료";
    }

    // 직원 데이터 삭제
    @DeleteMapping("staff/{staff_seq}")
    public String staffDelete(@PathVariable("staff_seq") int staffSeq) {
        int n = staffService.staffDelete(staffSeq);
        return "직원 삭제 완료";
    }

   // 임시휴무일 조회
    @GetMapping("closed-day/{shop_seq}")
    public List<TempdayDTO> tempdaySelect(@PathVariable("shop_seq") int shopSeq) {
        List<TempdayDTO> tempdayDto = hairshopService.tempdayFind(shopSeq);
        return tempdayDto;
    }

    //임시휴무일 등록
    @PostMapping("closed-day")
    public String tempdayInsert(@RequestBody TempdayDTO postData) {
        int n = hairshopService.tempdayAdd(postData);
        return "휴무일 추가 완료";
    }
}
