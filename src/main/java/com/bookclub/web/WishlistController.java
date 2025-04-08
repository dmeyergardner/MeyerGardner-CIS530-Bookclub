// File: src/main/java/com/bookclub/web/WishlistController.java

package com.bookclub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

import jakarta.validation.Valid;

/**
 * Controller for managing wishlist items using MongoDB.
 */
@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistDao wishlistDao;

    /**
     * Setter-based dependency injection for WishlistDao.
     *
     * @param wishlistDao the DAO implementation to be injected
     */
    @Autowired
    private void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    /**
     * Displays the wishlist page with all wishlist items.
     *
     * @param model the Spring model for passing attributes to the view
     * @return the wishlist list view
     */
    @GetMapping
    public String showWishlist(Model model) {
        List<WishlistItem> wishlist = wishlistDao.list();
        model.addAttribute("wishlist", wishlist);
        return "wishlist/list";
    }

    /**
     * Displays the form to add a new wishlist item.
     *
     * @param model the Spring model
     * @return the form view
     */
    @GetMapping("/new")
    public String wishlistForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }

    /**
     * Handles form submission for a new wishlist item.
     *
     * @param wishlistItem  the wishlist item submitted
     * @param bindingResult validation results
     * @return view name for redirection or form redisplay on error
     */
    @PostMapping
    public String addWishlistItem(
            @Valid @ModelAttribute WishlistItem wishlistItem,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        wishlistDao.add(wishlistItem);
        return "redirect:/wishlist";
    }
}
