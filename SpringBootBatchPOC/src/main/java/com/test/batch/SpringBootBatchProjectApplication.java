package com.test.batch;

import com.test.batch.constants.Constants;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories({"com.test.batch.repository"})
@ComponentScan(basePackages = "com.test.batch")
public class SpringBootBatchProjectApplication {

    public static void main(String[] args) {
        if (args.length > 0) {
            Constants.fileToRead = args[0];
        }
        SpringApplication.run(SpringBootBatchProjectApplication.class, args);
    }

}
