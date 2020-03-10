package com.lhf.springboot.controller;

import com.lhf.springboot.dto.CardDTO;
import com.lhf.springboot.dto.CustomDTO;
import com.lhf.springboot.dto.GroupCardDTO;
import com.lhf.springboot.dto.UserDTO;
import com.lhf.springboot.validate.ValidList;
import com.lhf.springboot.validate.group.Insert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 10:15
 */
@RestController
public class UserController {

    @PostMapping("/simple")
    public Object simple(@RequestBody @Valid CardDTO cardDTO){
        System.out.println("cardDTO = " + cardDTO);
        return cardDTO;
    }

    @PostMapping("/nested")
    public Object nested(@RequestBody @Valid UserDTO userDTO){
        System.out.println("userDTO = " + userDTO);
        return userDTO;
    }

    @PostMapping("card_list")
    public Object card_list(@RequestBody @Valid ValidList<CardDTO> cardList) {
        System.out.println("cardList = " + cardList);
        return cardList;
    }

    @PostMapping("user_card_list")
    public Object user_card_list(@RequestBody @Valid ValidList<UserDTO> cardList) {
        System.out.println("cardList = " + cardList);
        return cardList;
    }

    @PostMapping("insert_card")
    public Object insert_card(@RequestBody @Validated(Insert.class) GroupCardDTO card){
        System.out.println("card = " + card);
        return card;
    }

    @PostMapping("custom")
    public Object custom(@RequestBody @Validated CustomDTO customDTO){
        System.out.println("customDTO = " + customDTO);
        return customDTO;
    }

}
