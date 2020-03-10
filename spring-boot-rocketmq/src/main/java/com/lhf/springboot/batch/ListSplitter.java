package com.lhf.springboot.batch;

import org.apache.rocketmq.common.message.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ListSplitter
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/24 11:11
 */
public class ListSplitter implements Iterator<List<Message>> {

    private final int SIZE_LIMIT = 1000 * 1000;//1M
    private final List<Message> messages;
    private int currIndex;
    public ListSplitter(List<Message> messages) {
        this.messages = messages;
    }


    @Override
    public boolean hasNext() {
        return currIndex < messages.size();
    }

    /**
     * 获取下一批消息，一批消息总共不超过1M
     * @return
     */
    @Override
    public List<Message> next() {
        int nextIndex = currIndex;
        int totalSize = 0;
        for(; nextIndex < messages.size();nextIndex++){
            Message message = messages.get(nextIndex);
            int tmpSize = message.getTopic().length() + message.getBody().length;
            Map<String, String> properties = message.getProperties();
            for(Map.Entry<String, String> entry : properties.entrySet()){
                tmpSize += entry.getKey().length() + entry.getValue().length();
            }
            tmpSize = tmpSize + 20;
            if(tmpSize > SIZE_LIMIT){
                if(nextIndex - currIndex == 0){
                    nextIndex++;
                }
                break;
            }
            if(tmpSize + totalSize > SIZE_LIMIT){
                break;
            }else {
                totalSize += tmpSize;
            }
        }
        List<Message> subList = messages.subList(currIndex, nextIndex);
        currIndex = nextIndex;
        return subList;
    }

    @Override
    public void remove() {

    }
}
