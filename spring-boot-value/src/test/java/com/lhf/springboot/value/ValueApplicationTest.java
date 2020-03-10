package com.lhf.springboot.value;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: ValueApplicationTest
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 17:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= ValueApplication.class)
public class ValueApplicationTest {

    @Autowired
    private BaseValueInject baseValueInject;

    @Autowired
    private ConfigurationFileInject configurationFileInject;

    @Autowired
    private AdvanceValueInject advanceValueInject;

    @Autowired
    private RandomInject randomInject;

    @Test
    public void baseValueInject(){
        System.out.println(baseValueInject.toString());
    }

    @Test
    public void configurationFileInject(){
        System.out.println(configurationFileInject.toString());
    }

    @Test
    public void advanceValueInject(){
        System.out.println(advanceValueInject.toString());
    }

    @Test
    public void randomInject(){

        System.out.println(randomInject.toString());
    }

}
