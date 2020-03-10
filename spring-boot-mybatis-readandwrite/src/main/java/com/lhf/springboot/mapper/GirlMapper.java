package com.lhf.springboot.mapper;


import com.lhf.springboot.entity.Girl;

import java.util.List;

/**
 * @ClassName: GirlMapper
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/28 10:53
 */
public interface GirlMapper{

    Girl findByGrilId(int id);
    /**
     * 查询所有女孩--使用从库
     * @return
     */
    List<Girl> selectAll();

    /**
     * 添加女孩信息--使用主库
     * @param girl
     * @return
     */
    boolean addGirl(Girl girl);

    /**
     * 更新女孩信息--使用主库
     * @param girl
     * @return
     */
    boolean updateGirl(Girl girl);

    /**
     * 删除女孩--使用从库
     * @param id
     * @return
     */
    boolean deleteById(int id);
}
