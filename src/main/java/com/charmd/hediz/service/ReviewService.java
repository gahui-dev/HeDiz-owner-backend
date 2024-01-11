package com.charmd.hediz.service;

import com.charmd.hediz.dto.ReviewDTO;

import java.util.HashMap;
import java.util.List;

public interface ReviewService {
    public List<ReviewDTO> reviewFindAll();

    public ReviewDTO reviewFind(int review_seq);

    public int reviewUpdate(HashMap<String, Object> reviewReplyAndSeqMap);

    public int reviewDelete(int review_seq);
}
