package com.charmd.hediz.controller;

import com.charmd.hediz.dto.CustomerDTO;
import com.charmd.hediz.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // 모든 고객 조회
    // shop_name 입력으로 들어감
    @GetMapping("{shop_name}")
    public List<CustomerDTO> allCustomerSelect(@PathVariable("shop_name") String shopName){
        List<CustomerDTO> customerList;
        customerList = customerService.allCustomerSelect(shopName);
        return customerList;
    }

}
