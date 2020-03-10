package com.lhf.springboot.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @ClassName: MyBatisConfig
 * @Author: liuhefei
 * @Description: MyBatis配置
 * @Date: 2019/8/28 16:54
 */
@EnableTransactionManagement
@Configuration
public class MyBatisConfig {

    @Resource(name = "myRoutingDataSource")
    private DataSource myRoutingDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(myRoutingDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(myRoutingDataSource);
    }

    /*@Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(myRoutingDataSource);
        try {
            SqlSessionFactory session = bean.getObject();
            MapperHelper mapperHelper = new MapperHelper();

            //特殊配置
            Config config = new Config();
            config.setNotEmpty(true);
            mapperHelper.setConfig(config);
            //注册自己项目中使用的通用Mapper接口,这里没有默认值，必须手动注册
            mapperHelper.registerMapper(Mapper.class);

            //配置完成后，执行下面的操作
            mapperHelper.processConfiguration(session.getConfiguration());
            return session;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Bean(name = "sqlSessionTemplate")
    @Autowired
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public MapperScannerConfigurer scannerConfigurer(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
        configurer.setBasePackage("com.lhf.springboot.**.mapper");
        configurer.setMarkerInterface(Mapper.class);
        return configurer;
    }*/
}
