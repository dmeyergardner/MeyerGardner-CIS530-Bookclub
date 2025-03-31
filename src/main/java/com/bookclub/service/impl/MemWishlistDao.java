package com.bookclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

public class MemWishlistDao implements WishlistDao {
    private List<WishlistItem> wishlist;

    public MemWishlistDao() {
        wishlist = new ArrayList<>();
        wishlist.add(new WishlistItem("0001", "Refactoring"));
        wishlist.add(new WishlistItem("0002", "Clean Architecture"));
    }

    @Override
    public List<WishlistItem> list() {
        return wishlist;
    }

    @Override
    public WishlistItem find(String isbn) {
        return wishlist.stream()
                       .filter(item -> item.getIsbn().equals(isbn))
                       .findFirst()
                       .orElse(null);
    }
}
