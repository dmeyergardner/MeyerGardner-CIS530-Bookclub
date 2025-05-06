// File: src/main/java/com/bookclub/model/BookOfTheMonth.java

package com.bookclub.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;

/**
 * Represents a featured book selected for a particular month.
 * Used in the Bookclub application to support monthly highlights.
 * 
 * Fields:
 * - id: Unique identifier for the book entry
 * - month: Month (1-12) for which the book is featured
 * - isbn: ISBN identifier of the book
 * 
 * This model is persisted in MongoDB and validated for required fields.
 * 
 * @author
 */
public class BookOfTheMonth {

    @Id
    private String id;

    private Integer month;

    @NotEmpty(message = "ISBN is a required field.")
    private String isbn;

    // ===== Constructors =====
    public BookOfTheMonth() {
    }

    public BookOfTheMonth(String id, Integer month, String isbn) {
        this.id = id;
        this.month = month;
        this.isbn = isbn;
    }

    // ===== Getters and Setters =====
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // ===== String Representation =====
    @Override
    public String toString() {
        return String.format(
                "BookOfTheMonth{id='%s', month=%d, isbn='%s'}",
                id, month, isbn);
    }
}
