package com.charmd.hediz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {
    int staff_seq;
    String staff_id;
    String staff_pw;
    String staff_name;
    int staff_role;
    String staff_image;
    String staff_phone;
    String staff_intro;
    String staff_nickname;
    int shop_seq;
}
