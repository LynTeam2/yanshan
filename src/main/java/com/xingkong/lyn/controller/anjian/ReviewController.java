package com.xingkong.lyn.controller.anjian;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.Review;
import com.xingkong.lyn.service.anjian.IReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lyn on 2018/8/27.
 */
@RestController
public class ReviewController {
    @Resource
    private IReview reviewService;

    @RequestMapping(value = "/web/manage/review/list")
    public Object webManageReviewList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.ASC)
                                                  Pageable pageable, int reviewResult) {
        AjaxResults ajaxResults = new AjaxResults();
        Page<Review> reviews = reviewService.findByResult(reviewResult, pageable);
        ajaxResults.put("reviews", reviews);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/review/update")
    public Object webManageReviewUpdate(@RequestBody Review review) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == review.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数异常");
        } else {
            reviewService.updateReview(review);
        }
        return ajaxResults;
    }
}
