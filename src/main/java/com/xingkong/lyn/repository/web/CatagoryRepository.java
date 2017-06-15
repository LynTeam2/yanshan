package com.xingkong.lyn.repository.web;

import com.xingkong.lyn.model.web.Catagory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lyn on 2017/6/13.
 */
public interface CatagoryRepository extends JpaRepository<Catagory, Long>{
    List<Catagory> findByParentId(Long id);
}
