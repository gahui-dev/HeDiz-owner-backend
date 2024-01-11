package com.charmd.hediz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("ReservationDTO")
public class ReservationDTO {
    private int reserv_seq;
    private int style_seq;
    private int cust_seq;
    private int staff_seq;
    private int shop_seq;
    private String reserv_name;
    private String reserv_phone;
    private String reserv_request;
    private Date reserv_time;
    private int reserv_stat;
}
