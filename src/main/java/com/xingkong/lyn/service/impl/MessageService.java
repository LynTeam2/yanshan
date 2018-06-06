package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Message;
import com.xingkong.lyn.repository.web.MessageRepository;
import com.xingkong.lyn.service.IMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Bug Man on 2017-07-05.
 */
@Service
public class MessageService implements IMessage {

    @Resource
    private MessageRepository messageRepository;

    @Override
    public List<Message> getMessageList() {
        return messageRepository.findAll();
    }


    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findOne(id);
    }

    @Override
    public Page<Message> getMessageByPageable(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    @Override
    public boolean deleteOne(Long id) {
        messageRepository.delete(id);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteList(List<Long> ids) {
        messageRepository.deleteInBatch(messageRepository.findAll(ids));
        return true;
    }

    @Override
    public boolean addMessage(Message message) {
        messageRepository.saveAndFlush(message);
        return true;
    }
}
