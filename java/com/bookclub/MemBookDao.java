// File: src/main/java/com/bookclub/model/Book.java
package com.bookclub.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemBookDao implements BookDao {
    private List<Book> books;

    public MemBookDao() {
        books = new ArrayList<>();
        books.add(new Book("1111", "Spring Boot Essentials", "A beginner's guide to Spring Boot.", 200, Arrays.asList("Jane Doe", "John Smith")));
        books.add(new Book("2222", "Java Fundamentals", "Core concepts of Java programming.", 320, Arrays.asList("Alice Brown")));
        books.add(new Book("3333", "RESTful APIs", "Designing scalable REST APIs.", 280, Arrays.asList("Emily White")));
        books.add(new Book("4444", "Microservices Patterns", "Best practices in microservice design.", 350, Arrays.asList("Robert Black")));
        books.add(new Book("5555", "Thymeleaf in Action", "Practical guide to using Thymeleaf.", 180, Arrays.asList("Sophia Green")));
    }

    @Override
    public List<Book> list() {
        return books;
    }

    @Override
    public Book find(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}
