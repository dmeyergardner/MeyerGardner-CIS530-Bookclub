// File: src/main/java/com/bookclub/web/WishlistController.java
package com.bookclub.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WishlistController {

    @GetMapping("/wishlist")
    public String showWishlist() {
        return "wishlist/list"; // Only returns the view
    }
}
