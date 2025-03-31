/******************************************************************************
 * File: Book.java
 * Author: Deb Meyer-Gardner
 * Created: 2025-03-26
 * Description: This class represents a Book entity in the Bookclub application.
 *              It includes metadata such as ISBN, title, description, page count,
 *              and a list of authors.
 ******************************************************************************/

 package com.bookclub.model;

import java.util.List;
 
 /**
  * Book is a domain model class that represents a book in the Bookclub system.
  * Each book has an ISBN, title, description, number of pages, and a list of authors.
  */
 public class Book {
 
     private String isbn;
     private String title;
     private String description;
     private int numOfPages;
     private List<String> authors;
 
     /**
      * Default no-argument constructor.
      * Required for frameworks that instantiate objects via reflection.
      */
     public Book() {}
 
     /**
      * Constructs a fully-initialized Book object.
      *
      * @param isbn         the International Standard Book Number
      * @param title        the title of the book
      * @param description  a short description of the book
      * @param numOfPages   the total number of pages
      * @param authors      a list of authors for the book
      */
     public Book(String isbn, String title, String description, int numOfPages, List<String> authors) {
         this.isbn = isbn;
         this.title = title;
         this.description = description;
         this.numOfPages = numOfPages;
         this.authors = authors;
     }
 
     /**
      * Gets the book's ISBN.
      *
      * @return the ISBN value
      */
     public String getIsbn() {
         return isbn;
     }
 
     /**
      * Sets the book's ISBN.
      *
      * @param isbn the new ISBN to assign
      */
     public void setIsbn(String isbn) {
         this.isbn = isbn;
     }
 
     /**
      * Gets the title of the book.
      *
      * @return the book title
      */
     public String getTitle() {
         return title;
     }
 
     /**
      * Sets the title of the book.
      *
      * @param title the title to assign
      */
     public void setTitle(String title) {
         this.title = title;
     }
 
     /**
      * Gets the description of the book.
      *
      * @return the description text
      */
     public String getDescription() {
         return description;
     }
 
     /**
      * Sets the description of the book.
      *
      * @param description the description to assign
      */
     public void setDescription(String description) {
         this.description = description;
     }
 
     /**
      * Gets the total number of pages in the book.
      *
      * @return the number of pages
      */
     public int getNumOfPages() {
         return numOfPages;
     }
 
     /**
      * Sets the number of pages in the book.
      *
      * @param numOfPages the number of pages to assign
      */
     public void setNumOfPages(int numOfPages) {
         this.numOfPages = numOfPages;
     }
 
     /**
      * Gets the list of authors who contributed to the book.
      *
      * @return a list of author names
      */
     public List<String> getAuthors() {
         return authors;
     }
 
     /**
      * Sets the list of authors for the book.
      *
      * @param authors the authors to assign
      */
     public void setAuthors(List<String> authors) {
         this.authors = authors;
     }
 
     /**
      * Returns a string representation of the Book object.
      *
      * @return a formatted string containing book metadata
      */
     @Override
     public String toString() {
         return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", numOfPages=" + numOfPages +
                ", authors=" + authors +
                '}';
     }
 }
 