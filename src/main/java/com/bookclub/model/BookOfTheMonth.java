package com.bookclub.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * BookOfTheMonth represents a selected book for a specific month.
 * Used for role-based admin features and stored in MongoDB.
 *
 * Fields:
 * - id: Unique MongoDB document ID.
 * - month: Numeric value representing the calendar month (1–12).
 * - isbn: Required field representing the book's ISBN.
 *
 * Example toString output:
 * BookOfTheMonth{id=abc123, month=4, isbn=9780140328721}
 */
public class BookOfTheMonth {

    @Id
    private String id;

    @NotNull(message = "Month selection is required.")
    private Integer month;

    @NotEmpty(message = "ISBN is a required field.")
    private String isbn;

    // Constructors
    public BookOfTheMonth() {
    }

    public BookOfTheMonth(String id, Integer month, String isbn) {
        this.id = id;
        this.month = month;
        this.isbn = isbn;
    }

    // Getters and Setters
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

    // toString override for debugging and logging
    @Override
    public String toString() {
        return "BookOfTheMonth{" +
                "id='" + id + '\'' +
                ", month=" + month +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
