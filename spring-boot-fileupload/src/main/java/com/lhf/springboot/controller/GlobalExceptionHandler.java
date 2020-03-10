package com.lhf.springboot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @ClassName: GlobalExceptionHandler
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/25 14:37
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //https://jira.spring.io/browse/SPR-14651
    @ExceptionHandler
    public String handleError(MultipartException e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/uploadStatus";
    }
}
