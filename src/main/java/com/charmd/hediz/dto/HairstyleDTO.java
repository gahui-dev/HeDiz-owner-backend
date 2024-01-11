package com.charmd.hediz.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("HairstyleDTO")
public class HairstyleDTO {
    int style_seq;
    int shop_seq;
    int cate_seq;
    String style_name;
    String style_gender;
    int style_time;
    int style_price;
    String style_intro;
    String style_image;
}
