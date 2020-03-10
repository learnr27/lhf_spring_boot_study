package com.lhf.springboot.service;

import com.lhf.springboot.model.Girl;

import java.util.List;

/**
 * @ClassName: GirlService
 * @Author: liuhefei
 * @Description: 女孩相关业务接口
 * @Date: 2019/8/28 12:28
 */
public interface GirlService {

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

    /**
     * 测试事务，先插入，然后更新抛异常
     * @param girl
     * @return
     */
    boolean insertAndUpdate(Girl girl);


}
