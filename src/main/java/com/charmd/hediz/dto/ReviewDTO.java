package com.charmd.hediz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("ReviewDTO")
public class ReviewDTO {
    private int review_seq;
    private int shop_seq;
    private int reserv_seq;
    private BigDecimal review_score;
    private String review_content;
    private String review_photo;
    private Date review_date;
    private String review_reply;
}