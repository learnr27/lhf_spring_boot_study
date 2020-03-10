#SpringBoot整合mysql实现多数据源操作

在application.yml中设置了是否开启多数据源的开关，如下：               
muti-datasource-open: true #是否开启多数据源(true/false)          
实现单数据源与多数据源的切换    

使用方法：      
在service类中定义操作数据源的方法，然后在方法上加上注解如下：    
@DataSource(name = DSEnum.DATA_SOURCE_CORE)    主库      
@DataSource(name = DSEnum.DATA_SOURCE_BIZ)     业务库     
来实现具体操作哪个数据库。       
     
