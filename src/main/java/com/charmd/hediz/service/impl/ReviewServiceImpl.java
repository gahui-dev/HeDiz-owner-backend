package com.charmd.hediz.service.impl;

import com.charmd.hediz.dao.ReviewDAO;
import com.charmd.hediz.dto.ReviewDTO;
import com.charmd.hediz.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service("reviewService")
@Transactional
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewDAO dao;

    @Override
    public List<ReviewDTO> reviewFindAll() {
        return dao.reviewFindAll();
    }

    @Override
    public ReviewDTO reviewFind(int review_seq) {
        return dao.reviewFind(review_seq);
    }

    @Override
    public int reviewUpdate(HashMap<String, Object> reviewReplyAndSeqMap) {
        return dao.reviewUpdate(reviewReplyAndSeqMap);
    }

    @Override
    public int reviewDelete(int review_seq) {
        return dao.reviewDelete(review_seq);
    }
}
