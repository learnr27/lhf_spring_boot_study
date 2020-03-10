package com.lhf.springboot.spel.multiway.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//com.lhf.springboot/spel.xml
@ImportResource("classpath:/com.lhf.springboot/spel.xml")
public class SpELXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpELXmlApplication.class, args);
	}
}
