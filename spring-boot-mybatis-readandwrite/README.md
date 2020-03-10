#SpringBoot+MyBatis+MySQL读写分离
主库进行插入、修改、删除操作；从库进行查询操作；

注意：一定要在启动类上加上注解：@MapperScan("com.lhf.springboot.mapper")   

来源：https://mp.weixin.qq.com/s/dSAxt9LW4agl-5N-HrGzyw
https://mp.weixin.qq.com/s/p-LS0zqbArW_42w3_K-KTQ
