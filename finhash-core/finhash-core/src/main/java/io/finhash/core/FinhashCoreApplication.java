package io.finhash.core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = {"io.finhash.core.repository"})
public class FinhashCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinhashCoreApplication.class, args);
    }
}
