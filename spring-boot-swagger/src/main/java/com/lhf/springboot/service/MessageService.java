package com.lhf.springboot.service;

import com.lhf.springboot.model.Message;

import java.util.List;

/**
 * @ClassName: MessageRepository
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/22 16:21
 */
public interface MessageService {
    List<Message> findAll();

    Message save(Message message);

    Message update(Message message);

    Message updateText(Message message);

    Message findMessage(Long id);

    void deleteMessage(Long id);
}
