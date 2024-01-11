package com.charmd.hediz.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Alias("ReviewDTO")
public class ReviewDTO {
    int review_seq;
    int shop_seq;
    int reserv_seq;
    BigDecimal review_score;
    String review_content;
    String review_photo;
    Date review_date;
    String review_reply;
}