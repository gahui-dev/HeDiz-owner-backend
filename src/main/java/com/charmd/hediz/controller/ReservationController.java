package com.charmd.hediz.controller;

import com.charmd.hediz.dto.ReservationDTO;
import com.charmd.hediz.dto.ReviewDTO;
import com.charmd.hediz.service.ReservationService;
import com.charmd.hediz.service.ReviewService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api
@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReviewService reviewService;

    /*
     * 전체 예약 (/reservation)
     * */
    // 전체 예약 데이터 조회
    @GetMapping("total/{shop_seq}")
    public List<ReservationDTO> total(@PathVariable("shop_seq") int shopSeq){
        List<ReservationDTO> reservationList;
        reservationList = reservationService.reservationFindAll(shopSeq);
        return reservationList;
    }

    /*
     * 리뷰 관리 (/review)
     * */
    // 전체 리뷰 조회
    @GetMapping("review/{shop_seq}")
    public List<ReviewDTO> reviewFindAll(@PathVariable("shop_seq") int shopSeq){
        List<ReviewDTO> reviewList;
        reviewList = reviewService.reviewFindAll(shopSeq);
        return reviewList;
    }

    // 특정 리뷰 답글달기
    @PutMapping("review/{review_seq}")
    public ReviewDTO reviewUpdate(@RequestBody String reviewReply, @PathVariable("review_seq") int reviewSeq){
        HashMap<String, Object> reviewReplyAndSeqMap = new HashMap<>();
        reviewReplyAndSeqMap.put("review_reply", reviewReply);
        reviewReplyAndSeqMap.put("review_seq", reviewSeq);
        int n = reviewService.reviewUpdate(reviewReplyAndSeqMap);
        ReviewDTO reviewDto = reviewService.reviewFind(reviewSeq);
        return reviewDto;
    }

    // 특정 리뷰 삭제
    @DeleteMapping("review/{review_seq}")
    public String reviewDelete(@PathVariable("review_seq") int reviewSeq){
        int n = reviewService.reviewDelete(reviewSeq);
        return "리뷰 권동혁 삭제 완료";
    }

    /*
     * 알림 서비스 (/alarm)
     * */
}
