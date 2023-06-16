package com.jpdev.main

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
class Application {

    static void main(String[] args) {
        SpringApplication.run(Application, args)
    }
}
