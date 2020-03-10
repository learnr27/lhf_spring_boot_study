package com.lhf.springboot.service.impl;

import com.lhf.springboot.model.Message;
import com.lhf.springboot.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName: MessageServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/22 16:24
 */
@Service
public class MessageServiceImpl implements MessageService {

    private static AtomicLong counter = new AtomicLong();

    private final ConcurrentMap<Long, Message> messages = new ConcurrentHashMap<>();

    @Override
    public List<Message> findAll() {
        List<Message> messages = new ArrayList<Message>(this.messages.values());
        return messages;
    }

    @Override
    public Message save(Message message) {
        Long id = message.getId();
        if(id == null){
            id = counter.incrementAndGet();
            message.setId(id);
        }
        this.messages.put(id, message);
        return message;
    }

    @Override
    public Message update(Message message) {
        this.messages.put(message.getId(), message);
        return message;
    }

    @Override
    public Message updateText(Message message) {
        Message msg = this.messages.get(message.getId());
        msg.setText(message.getText());
        this.messages.put(msg.getId(), msg);
        return msg;
    }

    @Override
    public Message findMessage(Long id) {
        return this.messages.get(id);
    }

    @Override
    public void deleteMessage(Long id) {
       this.messages.remove(id);
    }
}
