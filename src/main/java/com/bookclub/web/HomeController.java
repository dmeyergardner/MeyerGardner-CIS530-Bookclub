/******************************************************************************
 * File: HomeController.java
 * Author: Deb Meyer-Gardner
 * Created: 2025-03-26
 * Description: This controller handles routing for the Bookclub application,
 *              including the home, about, contact pages, and the dynamic
 *              detail view for individual book selections.
 ******************************************************************************/

package com.bookclub.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookclub.model.Book;
import com.bookclub.service.impl.MemBookDao;

@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * Displays the home page and populates it with a list of Book objects.
     */
    @GetMapping
    public String showHome(Model model) {
        System.out.println("🚀 HomeController: showHome() triggered");
        MemBookDao bookDao = new MemBookDao();
        List<Book> books = bookDao.list();
        model.addAttribute("books", books);
        return "index";
    }

    /**
     * Displays the About Us page.
     */
    @GetMapping("/about")
    public String showAboutUs(Model model) {
        return "about";
    }

    /**
     * Displays the Contact Us page.
     */
    @GetMapping("/contact")
    public String showContactUs(Model model) {
        return "contact";
    }

    /**
     * Displays the detail view for a selected Book by its ISBN.
     */
    @GetMapping("/book/{id}")
    public String getMonthlyBook(@PathVariable String id, Model model) {
        MemBookDao bookDao = new MemBookDao();
        Book book = bookDao.find(id);
        model.addAttribute("book", book);
        return "monthly-books/view";
    }

    /**
     * Optional: Simple test route for debugging.
     */
    @GetMapping("/test")
    public String testRoute() {
        System.out.println("🧪 Test route triggered");
        return "index";
    }
}
