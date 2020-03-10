package com.lhf.springboot.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhf.springboot.dao.BaseDao;
import com.lhf.springboot.pojo.Student;
import com.lhf.springboot.pojo.User;
import com.lhf.springboot.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @ClassName: BaseServiceImpl
 * @Author: liuhefei
 * @Description: 数据层公共实现类
 * @Date: 2019/7/26 11:36
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    protected abstract BaseDao<T> getMapper();

    private String getClassName(T t){
        String str="";
        if(t instanceof User){
            str="Girl";
        }
        else if(t instanceof Student){
            str="Fruits";
        }
        return str;
    }


    @Override
    public boolean insert(T entity) {
        logger.info("插入数据：{}", entity.toString());
        boolean flag = false;
        try{
            getMapper().insert(entity);
            flag = true;
            logger.info("新增数据成功！");
        }catch (Exception e){
            logger.error("新增数据： " + getClassName(entity) + " 失败！原因是：" + e);
        }

        return flag;
    }

    @Override
    public boolean update(T entity) {
        logger.info("更新数据：{}", entity.toString());
        boolean flag = false;
        try{
            getMapper().update(entity);
            flag = true;
            logger.info("更新数据成功！");
        }catch (Exception e){
            logger.error("更新数据：" + getClassName(entity) + " 失败！原因是：" + e);
        }
        return flag;
    }

    @Override
    public boolean deleteByPrimaryKey(int id) {
        boolean flag = false;
        try{
            getMapper().deleteByPrimaryKey(id);
            flag = true;
            logger.info("删除数据成功！");
        }catch (Exception e){
            logger.error("id: "+ id + " 删除失败！原因是：" + e);
        }
        return flag;
    }

    @Override
    public boolean delete(T entity) {
        logger.info("删除数据：{}", entity.toString());
        boolean flag = false;
        try{
            getMapper().delete(entity);
            flag = true;
            logger.info("删除数据成功！");
        }catch (Exception e){
            logger.error("删除数据：" + getClassName(entity) + " 失败!原因是：" + e);
        }
        return flag;
    }

    @Override
    public T findByPrimaryKey(int id) {
        T obj = null;
        try{
            obj = getMapper().findByPrimaryKey(id);
            logger.info("查询数据成功！ {}", obj);
        }catch (Exception e){
            logger.error("id: " + id +  " 查询失败！原因是：" + e);
        }
        return obj;
    }

    @Override
    public T findByEntity(T entity) {
        T obj = null;
        try{
            obj = getMapper().findByEntity(entity);
            logger.info("查询数据成功！{}", obj);
        }catch (Exception e){
            logger.error("查询" + getClassName(entity) + "失败！原因是：" + e);
        }
        return obj;
    }

    @Override
    public List<T> findByListEntity(T entity) {
        List<T> list = null;
        try{
            Page<?> page = PageHelper.startPage(1, 2);
            System.out.println(getClassName(entity) + "设置第一页两条数据！");
            list = getMapper().findByListEntity(entity);
            System.out.println("总共有：" + page.getTotal() + "条数据，实际返回：" + list.size() + "条数据！");
            logger.info("分页数据为：{}", list);
        }catch (Exception e){
            logger.error("查询数据: " + getClassName(entity) + "失败！原因是：" + e );
        }
        return list;
    }

    @Override
    public List<T> findAll() {
        List<T> list = null;
        try{
            list = getMapper().findAll();
            logger.info("查询所有数据：{}", list);
        }catch (Exception e){
            logger.info("查询所有数据失败！原因是：{}", e);
        }
        return list;
    }

    @Override
    public Object findByObject(Object obj) {
        Object result = null;
        try{
            result = getMapper().findByObject(obj);
        } catch (Exception e) {
        logger.error("查询"+obj+" 失败!原因是:",e);
    }
		return result;
    }
}
