// File: src/main/java/com/bookclub/web/WishlistController.java

package com.bookclub.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.impl.MemWishlistDao;

import jakarta.validation.Valid;

/**
 * Controller for managing user interactions related to wishlist items.
 * Provides endpoints to view, create, and validate wishlist entries.
 */
@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    /**
     * Displays the list of wishlist items.
     *
     * @param model the Spring Model object used to pass data to the view
     * @return the name of the Thymeleaf template for the wishlist view
     */
    @GetMapping
    public String showWishlist(Model model) {
        MemWishlistDao dao = new MemWishlistDao();
        List<WishlistItem> wishlist = dao.list();
        model.addAttribute("wishlist", wishlist);
        return "wishlist/list";
    }

    /**
     * Displays the form to create a new wishlist item.
     *
     * @param model the Spring Model object used to initialize form fields
     * @return the name of the Thymeleaf template for the new wishlist item form
     */
    @GetMapping("/new")
    public String wishlistForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }
/**
 * Handles form submission for a new WishlistItem.
 * Validates the input and redisplays the form if errors exist.
 * If valid, redirects to the wishlist list view.
 *
 * @param wishlistItem the form-bound WishlistItem
 * @param bindingResult the validation result object (must follow @Valid parameter)
 * @return the name of the view to render or redirect path
 */
@PostMapping
public String addWishlistItem(
        @Valid @ModelAttribute WishlistItem wishlistItem,
        BindingResult bindingResult) {

    // Check if the form has validation errors
    if (bindingResult.hasErrors()) {
        // Re-display the form with error messages
        return "wishlist/new";
    }

    // Normally, you'd persist wishlistItem here. For now, simulate success.
    return "redirect:/wishlist";  // Forward user to the updated list
}

}
