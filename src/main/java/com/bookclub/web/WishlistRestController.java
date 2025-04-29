// File: src/main/java/com/bookclub/web/WishlistRestController.java

package com.bookclub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookclub.dao.WishlistDao;
import com.bookclub.dao.impl.MongoWishlistDao;
import com.bookclub.model.WishlistItem;

/**
 * REST Controller for managing Wishlist items.
 * Exposes endpoints to list and retrieve wishlist items over HTTP.
 */
@RestController
@RequestMapping(path = "/api/wishlist", produces = "application/json")
@CrossOrigin(origins = "*")
public class WishlistRestController {

    private WishlistDao wishlistDao = new MongoWishlistDao();

    /**
     * Setter-based dependency injection for the WishlistDao.
     * 
     * @param wishlistDao the DAO implementation for wishlist operations
     */
    @Autowired
    public void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    /**
     * Displays the list of wishlist items for the authenticated user.
     *
     * @param authentication the Authentication object containing user details
     * @return List of WishlistItem objects belonging to the authenticated user
     */
    @GetMapping
    public List<WishlistItem> showWishlist(Authentication authentication) {
        String username = authentication.getName(); // Get logged-in user's name
        return wishlistDao.list(username); // Fetch wishlist items for this user
    }

    /**
     * Retrieves a specific WishlistItem by its ID.
     *
     * @param id the ID of the wishlist item
     * @return the matching WishlistItem object
     */
    @GetMapping("/{id}")
    public WishlistItem findById(@PathVariable String id) {
        return wishlistDao.find(id);
    }
}
