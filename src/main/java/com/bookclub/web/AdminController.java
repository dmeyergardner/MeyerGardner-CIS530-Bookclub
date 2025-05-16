// File: src/main/java/com/bookclub/web/AdminController.java

package com.bookclub.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookclub.dao.BookOfTheMonthDao;
import com.bookclub.model.BookOfTheMonth;

import jakarta.validation.Valid;

/**
 * AdminController handles all administrative operations related to the
 * "Book of the Month" feature. It allows admins to view, add, and
 * delete monthly book entries. This controller ensures that only
 * authorized users can modify this data through form handling and routing.
 * 
 * <p>
 * Key responsibilities include:
 * - Displaying current book-of-the-month entries
 * - Presenting a form to add new entries
 * - Handling the submission and validation of new entries
 * - Allowing removal of specific book entries by ID
 * - Providing a month selection map for form population
 * </p>
 * 
 * This controller assumes access is restricted via Spring Security to ADMIN
 * roles.
 * 
 * @author
 */
@Controller
@RequestMapping("/monthly-books")
public class AdminController {

    private BookOfTheMonthDao bookOfTheMonthDao;

    /**
     * Setter-based dependency injection for BookOfTheMonthDao.
     * 
     * @param dao the injected BookOfTheMonthDao implementation
     */
    @Autowired
    public void setBookOfTheMonthDao(BookOfTheMonthDao dao) {
        this.bookOfTheMonthDao = dao;
    }

    /**
     * Displays the list of all Book of the Month entries.
     * Uses "999" as a filter key to retrieve all records.
     * 
     * @param model Spring model to pass data to the view
     * @return name of the Thymeleaf template for listing books
     */
    @GetMapping
    public String showBookOfTheMonth(Model model) {
        List<BookOfTheMonth> books = bookOfTheMonthDao.list("999");
        model.addAttribute("books", books);
        return "monthly-books/list";
    }

    /**
     * Displays the form for entering a new Book of the Month.
     * Adds a blank BookOfTheMonth object and a list of months to the model.
     * 
     * @param model Spring model for form rendering
     * @return name of the Thymeleaf template for the form
     */
    @GetMapping("/new")
    public String bookOfTheMonthForm(Model model) {
        model.addAttribute("months", getMonths());
        model.addAttribute("bookOfTheMonth", new BookOfTheMonth());
        return "monthly-books/new";
    }

    /**
     * Processes form submission to add a new Book of the Month.
     * Validates the submitted data and persists it if valid.
     * 
     * @param bookOfTheMonth a validated BookOfTheMonth object from the form
     * @param bindingResult  result of validation
     * @param model          Spring model for error redisplay
     * @return redirect to the list view or redisplay form if errors occur
     */
    @PostMapping
    public String addBookOfTheMonth(
            @Valid @ModelAttribute BookOfTheMonth bookOfTheMonth,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("months", getMonths());
            return "monthly-books/new";
        }

        bookOfTheMonthDao.add(bookOfTheMonth);
        return "redirect:/monthly-books";
    }

    /**
     * Removes a Book of the Month entry by its ID.
     * 
     * @param id the identifier of the book to remove
     * @return redirect to the list page after deletion
     */
    @GetMapping("/remove/{id}")
    public String removeBookOfTheMonth(@PathVariable String id) {
        bookOfTheMonthDao.remove(id);
        return "redirect:/monthly-books";
    }

    /**
     * Returns a map of month numbers to month names in order,
     * used to populate the select dropdown in the form view.
     * 
     * @return ordered map of month values and names
     */
    private Map<Integer, String> getMonths() {
        Map<Integer, String> months = new LinkedHashMap<>();
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");
        return months;
    }
}
