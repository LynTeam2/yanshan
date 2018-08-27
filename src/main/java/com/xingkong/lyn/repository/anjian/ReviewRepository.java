package com.xingkong.lyn.repository.anjian;

import com.xingkong.lyn.entity.anjian.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyn on 2018/8/27.
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByReviewResult(int reviewResult, Pageable pageable);
}
