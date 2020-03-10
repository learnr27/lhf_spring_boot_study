#SpringBoot读取数据库Cron表达式实现定时任务

SQL如下：
```java
CREATE TABLE `cron`  (
  `cron_id` varchar(30),
  `cron` varchar(30) 
);
INSERT INTO `cron` VALUES ('1', '0/5 * * * * ?');
```


