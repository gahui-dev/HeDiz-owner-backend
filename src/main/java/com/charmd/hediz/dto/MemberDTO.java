package com.charmd.hediz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("MemberDTO")
public class MemberDTO {

    private int staff_seq;
    private String staff_id;
    private String staff_pw;
    private String staff_name;
    private String staff_role;
    private String staff_image;
    private String staff_phone;
    private String staff_intro;
    private String staff_nickname;
    private String shop_seq;


}
