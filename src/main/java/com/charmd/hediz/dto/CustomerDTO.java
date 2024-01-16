package com.charmd.hediz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("CustomerDTO")
public class CustomerDTO {
    private int cust_seq;
    private String cust_name;
    private String cust_id;
    private String cust_phone;
    private String cust_gender;
    private int cust_visit;
}