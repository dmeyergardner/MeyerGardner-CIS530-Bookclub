// File: src/main/java/com/bookclub/service/impl/MemWishlistDao.java

package com.bookclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

/**
 * In-memory implementation of the WishlistDao interface.
 * Simulates a data store for WishlistItem entities.
 */
public class MemWishlistDao implements WishlistDao {

    private List<WishlistItem> wishlist;

    /**
     * Initializes the in-memory wishlist with example data.
     */
    public MemWishlistDao() {
        wishlist = new ArrayList<>();
        wishlist.add(new WishlistItem("0001", "Refactoring"));
        wishlist.add(new WishlistItem("0002", "Clean Architecture"));
    }

    /**
     * Returns the complete wishlist.
     *
     * @return List of WishlistItem objects
     */
    @Override
    public List<WishlistItem> list() {
        return wishlist;
    }

    /**
     * Finds a WishlistItem by its ISBN.
     *
     * @param isbn the ISBN of the item to find
     * @return the matching WishlistItem, or null if not found
     */
    @Override
    public WishlistItem find(String isbn) {
        return wishlist.stream()
                       .filter(item -> item.getIsbn().equals(isbn))
                       .findFirst()
                       .orElse(null);
    }
}
