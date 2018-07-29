package com.tdmobile.template;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringJpaApplication extends SpringBootServletInitializer implements CommandLineRunner {
    // public class SpringJpaApplication implements CommandLineRunner {

    /*
     * @Autowired private BCryptPasswordEncoder passwordEncoder;
     */

    public static void main(String[] args) {
	SpringApplication.run(SpringJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	/*
	 * String pass = "1234"; for (int i = 0; i < 10; i++)
	 * System.out.println(passwordEncoder.encode(pass));
	 */
    }
}
