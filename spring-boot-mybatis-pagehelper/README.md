##SpringBoot整合Mybatis、Druid和PageHelper 并实现多数据源和分页
来源：https://www.cnblogs.com/xuwujing/p/8964927.html     
Durid官方地址:https://github.com/alibaba/druid    

PageHelper官方地址:https://github.com/pagehelper/Mybatis-PageHelper    

@Primary ：标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean
优先被考虑。多数据源配置的时候注意，必须要有一个主数据源，用 @Primary 标志该 Bean。

@MapperScan： 扫描 Mapper 接口并容器管理。


PageHelper分页是实现
引入依赖：
```$xslt
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.12</version>
</dependency>
```
配置使用方法：
第一种：在application.properties或application.yml添加如下配置即可：     
```$xslt
pagehelper:     
helperDialect: mysql      
offsetAsPageNum: true     
rowBoundsWithCount: true    
reasonable: false      
```

第二种：在mybatis.xml中配置,如resources/doc/mybatis.xml

第三种：在代码中添加，使用@Bean注解在启动程序时初始化
```$xslt
  @Bean
  public PageHelper pageHelper(){
    PageHelper pageHelper = new PageHelper();
   Properties properties = new Properties();
   //数据库
   properties.setProperty("helperDialect", "mysql");
   //是否将参数offset作为PageNum使用
   properties.setProperty("offsetAsPageNum", "true");
   //是否进行count查询
   properties.setProperty("rowBoundsWithCount", "true");
   //是否分页合理化
   properties.setProperty("reasonable", "false");
   pageHelper.setProperties(properties);
  }
```
这里需要注意的是reasonable参数，表示分页合理化，默认值为false。        
如果该参数设置为 true 时，pageNum<=0 时会查询第一页，pageNum>pages（超过总数时），会查询最后一页。      
默认false 时，直接根据参数进行查询。     

设置完PageHelper 之后，使用的话，只需要在查询的sql前面添加PageHelper.startPage(pageNum,pageSize);       
如果是想知道记录总条数的话，在查询的sql语句那里添加 page.getTotal()就可以了。如下：    
```$xslt
public List<T> findByListEntity(T entity) {
    List<T> list = null;
    try {
        Page<?> page =PageHelper.startPage(1,2); 
        System.out.println(getClassName(entity)+"设置第一页两条数据!");
        list = getMapper().findByListEntity(entity);
        System.out.println("总共有:"+page.getTotal()+"条数据,实际返回:"+list.size()+"两条数据!");
    } catch (Exception e) {
        logger.error("查询"+getClassName(entity)+"失败!原因是:",e);
    }
    return list;
}
```
