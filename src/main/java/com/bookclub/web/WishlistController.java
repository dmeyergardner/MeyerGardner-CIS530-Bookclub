// File: src/main/java/com/bookclub/web/WishlistController.java
package com.bookclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookclub.dao.WishlistDao;
import com.bookclub.dao.impl.MongoWishlistDao;
import com.bookclub.model.WishlistItem;

import jakarta.validation.Valid;

/**
 * Controller for managing user Wishlist Items.
 * Developer Note:
 * - Supports creating, viewing, editing, updating, and deleting wishlist items.
 */
@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistDao wishlistDao = new MongoWishlistDao();

    @Autowired
    public void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    /**
     * Displays the wishlist list page.
     *
     * @return the wishlist list view
     */
    @GetMapping
    public String showWishlist() {
        return "wishlist/list";
    }

    /**
     * Displays the wishlist item edit form for a given ID.
     *
     * @param id    the ID of the wishlist item
     * @param model the model to hold attributes
     * @return the wishlist item view page
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showWishlistItem(@PathVariable String id, Model model) {
        WishlistItem item = wishlistDao.find(id);
        model.addAttribute("wishlistItem", item);
        return "wishlist/view";
    }

    /**
     * Updates a wishlist item after form submission.
     *
     * @param wishlistItem   the updated wishlist item
     * @param bindingResult  validation results
     * @param authentication the user's authentication
     * @return redirect to wishlist list on success, or back to form on error
     */
    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public String updateWishlistItem(
            @Valid @ModelAttribute WishlistItem wishlistItem,
            BindingResult bindingResult,
            Authentication authentication) {

        if (bindingResult.hasErrors()) {
            return "wishlist/view";
        }

        String username = authentication.getName();
        wishlistItem.setUsername(username);
        wishlistDao.update(wishlistItem);
        return "redirect:/wishlist";
    }

    /**
     * Deletes a wishlist item by ID.
     *
     * @param id the ID of the wishlist item
     * @return redirect to wishlist list page
     */
    @RequestMapping(method = RequestMethod.GET, path = "/remove/{id}")
    public String removeWishlistItem(@PathVariable String id) {
        wishlistDao.remove(id);
        return "redirect:/wishlist";
    }
}
