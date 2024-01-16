package com.charmd.hediz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("HairshopDTO")
public class HairshopDTO {
    private int shop_seq;
    private String shop_register;
    private String shop_name;
    private String shop_id;
    private String shop_pw;
    private String shop_address;
    private String shop_intro;
    private String shop_image;
    private Time shop_start;
    private Time shop_end;
    private String shop_phone;
    private String shop_off;
    private String shop_tag;
}
