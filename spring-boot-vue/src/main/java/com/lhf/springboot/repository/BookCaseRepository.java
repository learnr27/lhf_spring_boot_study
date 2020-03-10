package com.lhf.springboot.repository;

import com.lhf.springboot.entity.BookCase;

/**
 * @ClassName: BookCaseRepository
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/28 14:55
 */
public interface BookCaseRepository {

    public BookCase findById(Integer id);

}
