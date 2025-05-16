package com.bookclub.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents a user's Wishlist Item.
 * Developer Note:
 * - Now includes an ID and a username to associate items with a specific user.
 */
public class WishlistItem {

    @Id
    private String id;

    @NotBlank(message = "ISBN must not be blank")
    private String isbn;

    @NotBlank(message = "Title must not be blank")
    private String title;

    private String username;

    public WishlistItem() {
        // Default constructor
    }

    // ======= Getters and Setters ========

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // ======= toString() Override =========

    @Override
    public String toString() {
        return "WishlistItem{" +
                "id='" + id + '\'' +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
