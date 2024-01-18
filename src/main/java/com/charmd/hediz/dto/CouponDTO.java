package com.charmd.hediz.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("CouponDTO")
public class CouponDTO {
    private int coup_seq;
    private String coup_name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime coup_expire;
    private int coup_discount;
    private int cust_seq;

}
