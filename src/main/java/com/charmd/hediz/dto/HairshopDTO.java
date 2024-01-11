package com.charmd.hediz.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Time;

@Data
@Alias("HairshopDTO")
public class HairshopDTO {
    int shop_seq;
    String shop_name;
    String shop_register;
    String shop_code;
    String shop_address;
    String shop_phone;
    String shop_intro;
    String shop_image;
    Time shop_start;
    Time shop_end;
    int shop_off;
}
