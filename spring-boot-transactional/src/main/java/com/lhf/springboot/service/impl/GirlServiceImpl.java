package com.lhf.springboot.service.impl;

import com.lhf.springboot.dao.GirlDao;
import com.lhf.springboot.pojo.Girl;
import com.lhf.springboot.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;

/**
 * @ClassName: GirlServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/2 11:05
 */
@Service
public class GirlServiceImpl implements GirlService {
    @Autowired
    private GirlDao girlDao;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;


    @Override
    @Transactional  //声明式事务
    public boolean girl1(Girl girl) throws Exception {
        /**
         * 简单的事务回滚，由Spring框架进行回滚
         */
        Integer id = girl.getId();
        System.out.println("查询的数据1：" + girlDao.findByGirlId(id));
        //新增两次，会出现主键Id冲突，看是否可以回滚该条数据
        girlDao.insert(girl);
        System.out.println("查询的数据2：" + girlDao.findByGirlId(id));
        girlDao.insert(girl);

        return false;
    }

    @Override
    @Transactional  //声明式事务
    public boolean girl2(Girl girl) {
        /**
         * 简单的事务回滚，自己捕获该异常进行手动回滚
         */
        Integer id = girl.getId();
        try{
            System.out.println("查询数据1：" + girlDao.findByGirlId(id));
            //新增两次，会出现主键Id冲突，看是否可以回滚该条数据
            girlDao.insert(girl);
            System.out.println("查询数据2：" + girlDao.findByGirlId(id));
            girlDao.insert(girl);
        }catch (Exception e){
            System.out.println("发生异常，进行手动回滚");
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            //注意手动回滚事务要在异常抛出之前
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional  //声明式事务
    public boolean girl3(Girl girl) {
        /**
         * 子方法出现异常进行回滚
         */
        try{
            System.out.println("查询的数据1：" + girlDao.findByGirlId(girl.getId()));
            deal1(girl);
            deal2(girl);
            deal3(girl);
        }catch (Exception e){
            System.out.println("发生异常，进行手动回滚");
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            //注意手动回滚事务要在异常抛出之前
            e.printStackTrace();
        }

        return false;
    }

    public void deal1(Girl girl) throws Exception{
        girlDao.insert(girl);
        System.out.println("查询数据2：" + girlDao.findByGirlId(girl.getId()));
    }

    public void deal2(Girl girl) throws SQLException{
        if(girl.getAge() < 20){
            //SQL异常  主键冲突
            girlDao.insert(girl);
        }else {
            girl.setAge(22);
            girlDao.update(girl);
            System.out.println("查询的数据3：" + girlDao.findByGirlId(girl.getId()));
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    public void deal3(Girl girl){
        if(girl.getAge() > 20){
            //SQL异常
            girlDao.insert(girl);
        }
    }

    //编程式事务
    @Override
    public boolean girl4(Girl girl) {
        /**
         * 手动进行事务控制
         */
        TransactionStatus transactionStatus = null;
        boolean isCommit = false;
        try{
            transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            System.out.println("查询的数据1：" + girlDao.findByGirlId(girl.getId()));
            //进行新增/修改
            girlDao.insert(girl);
            System.out.println("查询的数据2：" + girlDao.findByGirlId(girl.getId()));
            if(girl.getAge() < 20){
                girl.setAge(girl.getAge() + 2);
                girlDao.update(girl);
                System.out.println("查询的数据3：" + girlDao.findByGirlId(girl.getId()));
            }else {
                throw new Exception("模拟一个异常！");
            }
            //手动提交
            dataSourceTransactionManager.commit(transactionStatus);
            isCommit = true;
            System.out.println("手动提交事务成功！");
            throw  new Exception("模拟第二个异常");
        }catch (Exception e){
            //如果未提交就进行回滚
            if(!isCommit){
                System.out.println("发送异常，进行手动回滚！");
                //手动回滚事务
                dataSourceTransactionManager.rollback(transactionStatus);
            }
            e.printStackTrace();
        }
        return false;
    }
}
