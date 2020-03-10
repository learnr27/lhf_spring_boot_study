package com.lhf.springboot.repository;

import com.lhf.springboot.model.Message;

/**
 * @ClassName: MessageRepository
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/28 17:17
 */
public interface MessageRepository {

    Iterable<Message> findAll();

    Message save(Message message);

    Message findMessage(Long id);

    void deleteMessage(Long id);
}
