// File: src/main/java/com/bookclub/BookclubApplication.java

package com.bookclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Spring Boot entry point.
 */
@SpringBootApplication(scanBasePackages = "com.bookclub")
public class BookclubApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookclubApplication.class, args);
    }
}
