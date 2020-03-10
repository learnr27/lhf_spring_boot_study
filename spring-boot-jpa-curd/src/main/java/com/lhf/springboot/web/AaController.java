package com.lhf.springboot.web;

import com.lhf.springboot.model.Aa;
import com.lhf.springboot.service.AaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: AController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 15:53
 */
@Controller
public class AaController {

    @Autowired
    private AaService aService;

    @RequestMapping("/a")
    public void add(@RequestParam String SeatPosition){
        Aa a = new Aa();
        a.setSeatPosition(SeatPosition);
        aService.save(a);
    }
}
