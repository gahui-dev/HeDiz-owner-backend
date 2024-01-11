package com.charmd.hediz.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("ReservationDTO")
public class ReservationDTO {
    int reserv_seq;
    int style_seq;
    int cust_seq;
    int staff_seq;
    int shop_seq;
    String reserv_name;
    String reserv_phone;
    String reserv_request;
    Date reserv_time;
    int reserv_stat;
}
