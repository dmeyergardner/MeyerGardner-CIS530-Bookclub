package com.bookclub.model;

/**
 * Represents a Book entity with minimal fields.
 * This model is used for both database persistence and external API
 * interaction.
 */
public class Book {

    /**
     * International Standard Book Number (ISBN) for identifying the book.
     */
    private String isbn;

    /**
     * Title of the book.
     */
    private String title;

    /**
     * Brief description or summary of the book.
     */
    private String description;

    /**
     * Web URL with additional information about the book (OpenLibrary link).
     */
    private String infoUrl;

    /**
     * Number of pages in the book.
     */
    private int numOfPages;

    // ================================
    // Getters and Setters
    // ================================

    /**
     * Returns the ISBN of the book.
     * 
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     * 
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Returns the title of the book.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     * 
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the description of the book.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the book.
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the URL with more information about the book.
     * 
     * @return the infoUrl
     */
    public String getInfoUrl() {
        return infoUrl;
    }

    /**
     * Sets the information URL of the book.
     * 
     * @param infoUrl the infoUrl to set
     */
    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    /**
     * Returns the number of pages in the book.
     * 
     * @return the number of pages
     */
    public int getNumOfPages() {
        return numOfPages;
    }

    /**
     * Sets the number of pages in the book.
     * 
     * @param numOfPages the number of pages to set
     */
    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    // ================================
    // Overridden Methods
    // ================================

    /**
     * Returns a string representation of the Book object.
     * Useful for logging and debugging.
     * 
     * @return a formatted string with book details
     */
    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", infoUrl='" + infoUrl + '\'' +
                ", numOfPages=" + numOfPages +
                '}';
    }
}
