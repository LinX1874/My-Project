package com.tangly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 发布版本
 *
 * @author tangly
 * @since JDK 1.7
 */
@SpringBootApplication
public class ProductionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductionApplication.class, args);
    }
}
