package com.lhf.springboot.dao;

import com.lhf.springboot.pojo.Girl;

import java.util.List;

/**
 * @ClassName: GirlDao
 * @Author: liuhefei
 * @Description: Girl接口
 * @Date: 2019/7/25 16:39
 */
public interface GirlDao {

    /**
     * 添加
     * @param girl
     */
    void addGirl(Girl girl);

    /**
     * 修改
     * @param girl
     */
    void updateGirl(Girl girl);

    /**
     * 删除
     * @param id
     */
    void deleteGirl(int id);

    /**
     * 查询
     * @param id
     * @return
     */
    Girl findById(int id);

    /**
     * 查询
     * @return
     */
    List<Girl> findByGirlList();


}
