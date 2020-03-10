package com.lhf.springboot.dao;

import java.util.List;

/**
 * @ClassName: BaseDao
 * @Author: liuhefei
 * @Description: 基础数据层
 * @Date: 2019/7/26 11:20
 */
public interface BaseDao<T> {

    /**
     * 单条新增插入数据
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    void insert(T entity) throws Exception;


    /**
     * 批量新增据插入数据
     * @param entityList
     * @return
     * @throws Exception
     * @throws
     */
    int insertBatch(List<T> entityList) throws Exception;

    /**
     * 更新数据
     *
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    void update(T entity) throws Exception;

    /**
     * 根据ID删除数据
     * @param id
     * @throws Exception
     * @throws
     */
    void deleteByPrimaryKey(int id) throws Exception;

    /**
     * 删除数据
     * @param entity
     * @throws Exception
     * @throws
     */
    void delete(T entity) throws Exception;


    /**
     * 根据id查询单个记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    T findByPrimaryKey(int id);

    /**
     * 根据对象查询单个记录
     *
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    T findByEntity(T entity);



    /**
     * 根据对象查询多条记录
     * @param entity
     * @return
     */
    List<T> findByListEntity(T entity);

    /**
     * 查询所有记录
     * @return
     */
    List<T> findAll();

    /**
     * 根据对象查询信息
     *
     * @param obj
     * @return
     * @throws Exception
     * @throws
     */
    Object findByObject(Object obj);



}
