package com.charmd.hediz.controller;

import com.charmd.hediz.dto.CouponDTO;
import com.charmd.hediz.dto.CustomerDTO;
import com.charmd.hediz.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // 모든 고객 조회
    @GetMapping("total/{shop_seq}")
    public List<CustomerDTO> allCustomerSelect(@PathVariable("shop_seq") String shopSeq){
        List<CustomerDTO> customerList;
        customerList = customerService.allCustomerSelect(shopSeq);
        return customerList;
    }

    // 쿠폰 발급
    @PostMapping("coupon-issue")
    public String couponIssue(@RequestBody CouponDTO couponDto){
        System.out.println(couponDto);
        int n = customerService.couponIssue(couponDto);
        System.out.println(n);
        if (n==1) return "쿠폰이 발급되었습니다.";
        else return "쿠폰이 발급되지 않았습니다.";
    }

}
