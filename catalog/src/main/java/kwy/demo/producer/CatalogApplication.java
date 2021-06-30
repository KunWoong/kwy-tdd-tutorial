package kwy.demo.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CatalogApplication.class);
        app.run(args);
    }
}
