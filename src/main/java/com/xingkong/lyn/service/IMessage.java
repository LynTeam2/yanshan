package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Bug Man on 2017-07-05.
 */
public interface IMessage {
    List<Message> getMessageList();
    Message getMessageById(Long id);
    Page<Message> getMessageByPageable(Pageable pageable);
    boolean deleteOne(Long id);
    boolean deleteList(List<Long> id);
    boolean addMessage(Message message);
}
