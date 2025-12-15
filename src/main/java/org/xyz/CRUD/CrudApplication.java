package org.xyz.CRUD;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {

        Dotenv.configure()
                .systemProperties()
                .load();
        SpringApplication.run(CrudApplication.class, args);
    }


    @PostConstruct
    public void checkName() {
        System.out.println("APP NAME = " + System.getProperty("spring.application.name"));
    }

}
