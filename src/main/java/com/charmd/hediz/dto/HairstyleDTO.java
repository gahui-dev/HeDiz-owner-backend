package com.charmd.hediz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("HairstyleDTO")
public class HairstyleDTO {

    private int style_seq;
    private int shop_seq;
    private String cate_seq;
    private String style_name;
    private String style_gender;
    private int style_time;
    private int style_price;
    private String style_intro;
    private String style_image;
}