package com.xingkong.lyn.repository.web;

import com.xingkong.lyn.model.web.PageHtml;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface PageHtmlRepository extends JpaRepository<PageHtml, Long>{
    PageHtml findByPage(String page);
    List<PageHtml> findByPage(List<String> pages);
}
