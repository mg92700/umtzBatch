package com.utmz.centreon;

import org.jtransfo.JTransfo;
import org.jtransfo.spring.JTransfoSpringFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication


@EnableJpaRepositories("com.umtz.centreon.dao")
@EntityScan("com.umtz.centreon.model")
@ComponentScan("com.utmz.centreon")
//@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class })
public class CentreonWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentreonWebServiceApplication.class, args);
	}
	
	@Bean
	@Scope("singleton")
	public JTransfoSpringFactory jTransfoFactory() {
							
		JTransfoSpringFactory c = new JTransfoSpringFactory();
		
		return c;
	}
	
	@Bean
	@Scope("singleton")
	public JTransfo jTransfo() {
		
		return this.jTransfoFactory().get();
	} 
}
