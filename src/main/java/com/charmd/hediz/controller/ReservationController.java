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
    @GetMapping("total")
    public List<ReservationDTO> total(){
        List<ReservationDTO> reservationList;
        reservationList = reservationService.reservationFindAll();
        return reservationList;
    }

    // 예약 상태 수정 (reserv_stat)
    @PutMapping("total/{reserv_seq}")
    public ReservationDTO reservationUpdate(@RequestBody int reservStat, @PathVariable("reserv_seq") int reservSeq){
        // 일단 되는지 수정되는지 확인

        // 두 개의 파라미터를 저장할 HashMap 객체 생성
        HashMap<String, Integer> reservationStatAndSeqMap = new HashMap<>();
        reservationStatAndSeqMap.put("reserv_stat", reservStat);
        reservationStatAndSeqMap.put("reserv_seq", reservSeq);

        // service 부분에 reservStat 넘기기
        int n = reservationService.reservationUpdate(reservationStatAndSeqMap);

        // 수정된 DTO 반환
        // reqest_seq를 이용해서 특정 예약 찾게하기
        // reservationSelect(int reserv_seq) 으로 Service에서 호출할 것

        ReservationDTO reservationDTO = reservationService.reservationFind(reservSeq);
        return reservationDTO;
    }

    /*
     * 리뷰 관리 (/review)
     * */
    // 전체 리뷰 조회
    @GetMapping("review")
    public List<ReviewDTO> reviewFindAll(){
        List<ReviewDTO> reviewList;
        reviewList = reviewService.reviewFindAll();
        return reviewList;
    }

    // 특정 리뷰 조회
    @GetMapping("review/{review_seq}")
    public ReviewDTO reviewFind(@PathVariable("review_seq") int reviewSeq){
        ReviewDTO reviewDto = reviewService.reviewFind(reviewSeq);
        return reviewDto;
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
        return "리뷰 삭제 완료";
    }

    /*
     * 알림 서비스 (/alarm)
     * */
}
