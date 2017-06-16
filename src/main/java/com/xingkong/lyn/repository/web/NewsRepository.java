package com.xingkong.lyn.repository.web;

import com.xingkong.lyn.model.web.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface NewsRepository extends JpaRepository<News, Long>{
}
