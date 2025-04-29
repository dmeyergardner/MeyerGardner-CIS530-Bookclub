// File: src/main/java/com/bookclub/model/WishlistItem.java

package com.bookclub.model;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents a user's Wishlist Item.
 * Developer Note:
 * - Now includes a 'username' property to associate items with a specific user.
 */
public class WishlistItem {

    @NotBlank(message = "ISBN must not be blank")
    private String isbn;

    @NotBlank(message = "Title must not be blank")
    private String title;

    // New field for user association
    private String username;

    public WishlistItem() {
        // Default constructor
    }

    // ======= Getters and Setters ========

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
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
