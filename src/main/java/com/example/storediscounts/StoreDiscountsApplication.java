package com.example.storediscounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Store Discounts application.
 * This class uses Spring Boot to bootstrap the application.
 *
 * <p>
 * The {@code main} method runs the application by calling
 * {@link SpringApplication#run(Class, String...)}.
 * </p>
 *
 * <p>
 * This application provides services for calculating store discounts
 * based on various criteria such as employee status,
 * affiliate status, and customer loyalty.
 * </p>
 */
@SpringBootApplication
public class StoreDiscountsApplication {

    /**
     * The main method that serves as the entry point for the Spring Boot
     * application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(StoreDiscountsApplication.class, args);
    }

}
