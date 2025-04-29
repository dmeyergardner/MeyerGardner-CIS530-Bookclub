// File: src/main/java/com/bookclub/web/WishlistRestController.java
package com.bookclub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.service.impl.MongoWishlistDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api/wishlist", produces = "application/json")
@CrossOrigin(origins = "*")
@Tag(name = "Wishlist API", description = "API for managing wishlist items")
public class WishlistRestController {

    private WishlistDao wishlistDao = new MongoWishlistDao();

    @Autowired
    public void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    @GetMapping
    @Operation(summary = "Get all wishlist items", description = "Retrieve a full list of all wishlist items in the system.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    public List<WishlistItem> showWishlist() {
        return wishlistDao.list();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find wishlist item by ID", description = "Retrieve a specific wishlist item by its ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved item")
    @ApiResponse(responseCode = "404", description = "Item not found")
    public WishlistItem findById(@PathVariable String id) {
        return wishlistDao.find(id);
    }
}
