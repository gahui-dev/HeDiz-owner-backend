package com.charmd.hediz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("TempdayDTO")
public class TempdayDTO {
    private int temp_seq;
    private int shop_seq;
    private int staff_seq;
    private String temp_offday;

    // join column
    private String title;
    private String date;
    private String staff_name;
}

