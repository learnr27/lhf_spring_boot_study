package com.lhf.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.lhf.springboot.dto.MessageDTO;
import com.lhf.springboot.exception.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @ClassName: MessageController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 11:39
 */
@RestController
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @PostMapping("/message")
    public Object message(@RequestBody @Valid MessageDTO messageDTO){
        System.out.println("messageDTO = " + messageDTO);
         return messageDTO;
    }


    @RequestMapping(value = "/message/info", method = RequestMethod.POST)
    public Result saveCustomerPage(@RequestBody @Validated MessageDTO messageDTO) {
        logger.info("Good" + messageDTO.getTime());
        Result okResult = new Result();
        okResult.setCode(200);

        System.out.println("message = " + JSONObject.toJSON(messageDTO).toString());
        okResult.setMessage(JSONObject.toJSON(messageDTO).toString());
        return okResult;
    }
}
