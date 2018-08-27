package com.xingkong.lyn.service.anjian;

import com.xingkong.lyn.entity.anjian.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lyn on 2018/8/27.
 */
public interface IReview {
    Page<Review> findAll(Pageable pageable);
    Page<Review> findByResult(int reviewResult, Pageable pageable);
    boolean addReview(Review review);
    boolean updateReview(Review review);
}
