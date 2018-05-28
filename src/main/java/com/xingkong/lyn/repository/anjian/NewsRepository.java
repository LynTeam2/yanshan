package com.xingkong.lyn.repository.anjian;

import com.xingkong.lyn.entity.anjian.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findByTitleLike(String title, Pageable pageable);
}
