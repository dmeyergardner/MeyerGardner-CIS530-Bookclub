/******************************************************************************
 * File: MemBookDao.java
 * Author: Deb Meyer-Gardner
 * Created: 2025-03-26
 * Description: This class implements the BookDao interface using in-memory
 *              storage. It provides access to a static list of book data for
 *              display and detail view within the Bookclub application.
 ******************************************************************************/

 package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 
 /**
  * MemBookDao provides a memory-based implementation of the BookDao interface.
  * It simulates a persistent data source by initializing a static list of Book
  * objects within the constructor. This class supports listing all books and
  * finding a book by its ISBN.
  */
 public class MemBookDao implements BookDao {
 
     private List<Book> books;
 
     /**
      * Constructs the MemBookDao and initializes the in-memory list of books.
      * Each Book object contains an ISBN, title, description, number of pages,
      * and a list of authors.
      */
     public MemBookDao() {
         books = new ArrayList<>();
         books.add(new Book("1111", "Spring Boot Essentials", "A beginner's guide to Spring Boot.", 200,
                 Arrays.asList("Jane Doe", "John Smith")));
         books.add(new Book("2222", "Java Fundamentals", "Core concepts of Java programming.", 320,
                 Arrays.asList("Alice Brown")));
         books.add(new Book("3333", "RESTful APIs", "Designing scalable REST APIs.", 280,
                 Arrays.asList("Emily White")));
         books.add(new Book("4444", "Microservices Patterns", "Best practices in microservice design.", 350,
                 Arrays.asList("Robert Black")));
         books.add(new Book("5555", "Thymeleaf in Action", "Practical guide to using Thymeleaf.", 180,
                 Arrays.asList("Sophia Green")));
     }
 
     /**
      * Returns a list of all Book objects in memory.
      *
      * @return a list of Book instances
      */
     @Override
     public List<Book> list() {
         return books;
     }
 
     /**
      * Finds and returns a Book based on its ISBN.
      *
      * @param isbn the unique identifier of the book to search for
      * @return the matching Book if found; otherwise, returns null
      */
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
 