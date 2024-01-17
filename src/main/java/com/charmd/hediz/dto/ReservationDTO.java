package com.charmd.hediz.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("ReservationDTO")
public class ReservationDTO {
    private int reserv_seq;
    private int style_seq;
    private String style_name;
    private int cust_seq;
    private String cust_name;
    private int staff_seq;
    private String staff_nickname;
    private int shop_seq;
    private String reserv_request;
    private Date reserv_time;
    private int reserv_stat;
}
