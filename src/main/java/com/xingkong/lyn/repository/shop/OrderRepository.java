package com.xingkong.lyn.repository.shop;

import com.xingkong.lyn.model.shop.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyn on 2017/6/5.
 */
public interface OrderRepository extends JpaRepository<Order,Long>{
}
