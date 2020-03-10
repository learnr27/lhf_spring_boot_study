package com.lhf.springboot.service;


import com.lhf.springboot.pojo.Girl;
import com.lhf.springboot.pojo.GirlVO;

import java.util.List;

/**
 * @ClassName: GirlService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/30 16:17
 */
public interface GirlService {

    /**
     * 批量新增女孩
     * @param girlList
     * @return
     */
    boolean insertBatch(List<Girl> girlList);


    /**
     * 查询用于
     * @param girl
     * @return
     */
    List<Girl> findByGirl(Girl girl);

    public GirlVO findByPage(Integer page);

    public Integer save(Girl girl);

    public Integer deleteById(Integer id);

    public Girl findById(Integer id);

    public Integer update(Girl girl);

    public Girl findByIdAndName(Integer id, String name);
}
