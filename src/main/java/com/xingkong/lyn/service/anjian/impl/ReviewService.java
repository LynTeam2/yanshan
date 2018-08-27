package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.Review;
import com.xingkong.lyn.repository.anjian.ReviewRepository;
import com.xingkong.lyn.service.anjian.IReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lyn on 2018/8/27.
 */
@Service
public class ReviewService implements IReview {

    @Resource
    private ReviewRepository reviewDao;

    @Override
    public Page<Review> findAll(Pageable pageable) {
        return reviewDao.findAll(pageable);
    }

    @Override
    public Page<Review> findByResult(int reviewResult, Pageable pageable) {
        return reviewDao.findAllByReviewResult(reviewResult, pageable);
    }

    @Override
    public boolean addReview(Review review) {
        reviewDao.saveAndFlush(review);
        return true;
    }

    @Override
    public boolean updateReview(Review review) {
        reviewDao.saveAndFlush(review);
        return true;
    }
}
