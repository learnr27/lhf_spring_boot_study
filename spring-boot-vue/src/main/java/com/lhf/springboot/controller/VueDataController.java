package com.lhf.springboot.controller;

import com.lhf.springboot.entity.Book;
import com.lhf.springboot.entity.Girl;
import com.lhf.springboot.service.BookService;
import com.lhf.springboot.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: VueDataController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/31 15:46
 */
@Controller
@RequestMapping("/data")
public class VueDataController {

    @Autowired
    private GirlService girlService;

    @Autowired
    private BookService bookService;

    /**
     * GET 请求 + 普遍变量传参
     * axios 异步 GET 请求的方法为 axios.get(url,params).then()
     * url：请求的 URL。
     * params：参数，格式为 {params:{name:value,name:value}}
     * then()：请求成功的回调函数。
     *
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/getData")
    @ResponseBody
    public String getData(@RequestParam(value = "id") Integer id, @RequestParam(value = "name") String name){
        Girl girl = girlService.findByIdAndName(id, name);
        System.out.println(girl);
        return girl.toString();
    }

    /**
     * GET请求 + JSON传参
     * axios异步GET请求的方法为：axios.get(url, params).then()
     * url: 请求的URL
     * params：参数，格式为：{params: json}
     * then(): 请求成功的回调函数
     *
     * @param girl
     * @return
     */
    @GetMapping("/getJson")
    @ResponseBody
    public Girl getJson(Girl girl){
        System.out.println(girl);
        return girl;
    }

    /**
     * PSOT 请求 + 普遍变量传参
     * axios 异步 POST 请求的方法为 axios.post(url,params).then()
     * url：请求的 URL
     * params：参数，POST 请求中，参数格式不再是  {params:{name:value,name:value}} ，而需要将参数封装到 URLSearchParams 对象中。
     * then()：请求成功的回调函数。
     *
     * @param id
     * @param name
     * @return
     */
    @PostMapping("/postData")
    @ResponseBody
    public Girl postData(Integer id, String name){
        System.out.println(id + " : " + name);
        Girl girl = girlService.findByIdAndName(id, name);
        System.out.println("girl = " + girl);
        return girl;
    }

    /**
     * PSOT 请求 + JSON传参
     * axios 异步 POST 请求的方法为 axios.post(url,params).then()
     * url：请求的 URL
     * params：参数，POST 请求中，参数格式不再是  {params:{name:value,name:value}} ，而需要将参数封装到 URLSearchParams 对象中。
     * then()：请求成功的回调函数。
     *
     * @param girl
     * @return
     */
    @PostMapping("/postJson")
    public Integer postJson(Girl girl){
        System.out.println(girl);
        return girlService.save(girl);
    }


    /**
     * 基于 RESTful GET 请求 + 普遍变量传参
     * 基于 RESTful 的 axios 异步 GET 请求的方法为 axios.gett(url).then()
     * url：请求的 URL，直接追加参数。
     * then()：请求成功的回调函数。
     *
     * @param id
     * @return
     */
    @GetMapping("/restfulGetData/{id}")
    @ResponseBody
    public Girl restfulGetData(@PathVariable(value = "id")Integer id){
        Girl girl = girlService.findById(id);
        System.out.println("girl = " + girl);
        return girl;
    }

    /**
     * 基于 RESTful GET 请求 + JSON 传参
     * 基于 RESTful 的 axios 异步 GET 请求的方法为 axios.get(url,params).then()
     * url：请求的 URL。
     * params：参数，格式为  {params:{name:value,name:value}} 。
     * then()：请求成功的回调函数。
     *
     * @return
     */
    @GetMapping("/restfulGetJson")
    public Integer restfulGetJson(Book book){
        System.out.println("book = " + book);
        return bookService.save(book);
    }

    /**
     * 基于 RESTful POST 请求 + 普通变量传参
     * 基于 RESTful 的 axios 异步 POST 请求的方法为 axios.post(url).then()
     * url：请求的 URL，直接追加参数。
     * then()：请求成功的回调函数。
     *
     * @param id
     * @return
     */
    @PostMapping("/restfulPostData/{id}")
    @ResponseBody
    public Girl restfulPostData(@PathVariable("id")Integer id){
        Girl girl = girlService.findById(id);
        System.out.println("girl = " + girl);
        return girl;
    }

    /**
     * 基于 RESTful POST 请求 + JSON 传参
     * 基于 RESTful 的 axios 异步 POST 请求的方法为 axios.post(url,params).then()
     * url：请求的 URL。
     * params：参数，需要将参数封装到 URLSearchParams 对象中。
     * then()：请求成功的回调函数。
     *
     * @param girl
     * @return
     */
    @PostMapping("/restfulPostJson")
    @ResponseBody
    public Girl restfulPostJson(Girl girl){
        Integer id = girl.getId();
        String name = girl.getName();
        Girl gg = girlService.findByIdAndName(id, name);
        System.out.println("girl = " + gg);
        return gg;
    }

    /**
     * 基于 RESTful PUT 请求 + 普通变量传参
     * 基于 RESTful 的 axios 异步 POST 请求的方法为 axios.put(url).then()
     * url：请求的 URL，直接追加参数。
     * then()：请求成功的回调函数。
     *
     * @param id
     * @return
     */
    @PutMapping("/restfulPutData/{id}")
    @ResponseBody
    public Book restfulPutData(@PathVariable("id")Integer id){
       Book book = bookService.findById(id);
        System.out.println("book = " + book);
        return book;
    }

    /**
     * 基于 RESTful PUT 请求 + JSON 传参
     * 基于 RESTful 的 axios 异步 POST 请求的方法为 axios.put(url,params).then()
     * url：请求的 URL。
     * params：参数，需要将参数封装到 URLSearchParams 对象中。
     * then()：请求成功的回调函数。
     *
     * @param girl
     * @return
     */
    @PutMapping("/restfulPutJson")
    @ResponseBody
    public Girl restfulPutJson(Girl girl){
        Girl gg = girlService.findByIdAndName(girl.getId(), girl.getName());
        System.out.println("girl = " + gg);
        return  gg;
    }

    /**
     * 基于 RESTful DELETE 请求 + 普通变量传参
     * 基于 RESTful 的 axios 异步 POST 请求的方法为 axios.delete(url).then()
     * url：请求的 URL，直接追加参数。
     * then()：请求成功的回调函数。
     *
     * @param id
     * @return
     */
    @DeleteMapping("/restfulDeleteData/{id}")
    @ResponseBody
    public Integer restfulDeleteData(@PathVariable("id")int id){
        Integer num = bookService.deleteById(id);
        System.out.println("num = " + num);
        if(num < 0){
            return 0;
        }
        return 1;
    }

    /**
     * 基于 RESTful DELETE 请求 + JSON 传参
     * 基于 RESTful 的 axios 异步 POST 请求的方法为 axios.delete(url,params).then()
     * url：请求的 URL。
     * params：参数，格式为  {params:{name:value,name:value}} 。
     * then()：请求成功的回调函数。
     *
     * @return
     */
    @DeleteMapping("/restfulDeleteJson")
    @ResponseBody
    public String restfulDeleteJson(Book book){
        int id = book.getId();
        String name = book.getName();
        Book bb = bookService.findByIdAndName(id, name);
        System.out.println("book = " + bb);
        if(bb == null){
            return "不存在该条记录";
        }
        Integer num = bookService.deleteById(id);
        if(num < 0){
            return name + " 删除失败";
        }
        return name + " 删除成功";
    }
}
