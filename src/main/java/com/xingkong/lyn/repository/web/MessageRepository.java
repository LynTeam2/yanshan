package com.xingkong.lyn.repository.web;

import com.xingkong.lyn.model.web.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bug Man on 2017-07-05.
 */
public interface MessageRepository extends JpaRepository<Message,Long>{
}
