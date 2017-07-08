package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.PageHtml;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface IPageHtml {
    PageHtml getPageHtml(String page);
    Page<PageHtml> getPageHtmls(Pageable pageable);
    PageHtml getPageHtml(Long id);
    boolean addPageHtml(PageHtml pageHtml);
    boolean updatePageHtml(PageHtml pageHtml);
    boolean deletePageHtml(List<Long> id);
}
