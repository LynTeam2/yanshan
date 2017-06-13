package com.xingkong.lyn.repository.web;

import com.xingkong.lyn.model.web.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface BannerRepository extends JpaRepository<Banner, Long>{
    List<Banner> findByPosition(String position);
}
