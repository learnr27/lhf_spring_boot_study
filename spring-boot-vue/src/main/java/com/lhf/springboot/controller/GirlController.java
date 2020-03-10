package com.lhf.springboot.controller;

import com.lhf.springboot.entity.Girl;
import com.lhf.springboot.entity.GirlVO;
import com.lhf.springboot.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: GirlController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/30 16:15
 */
@RestController
@RequestMapping("/girl")
public class GirlController {

    @Autowired
    private GirlService girlService;

    @GetMapping("/findByPage/{page}")
    public GirlVO findByPage(@PathVariable("page")Integer page){
        return girlService.findByPage(page);
    }

    @PostMapping("/saveGirl")
    public Integer save(@RequestBody Girl girl){
        return girlService.save(girl);
    }

    @DeleteMapping("/deleteById/{id}")
    public Integer deleteById(@PathVariable("id")Integer id){
        return girlService.deleteById(id);
    }

    @GetMapping("/findById/{id}")
    public Girl findById(@PathVariable("id")Integer id){
        return girlService.findById(id);
    }

    @PutMapping("/updateGirl")
    public Integer update(@RequestBody Girl book){
        return girlService.update(book);
    }

}
