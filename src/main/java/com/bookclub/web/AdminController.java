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
 * AdminController handles administrative functions for managing Books of the
 * Month.
 * It supports viewing, adding, and removing monthly book entries.
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
     * Displays a list of all books designated as Book of the Month.
     *
     * @param model Spring Model for passing attributes to the view
     * @return the list view template name
     */
    @GetMapping
    public String showBookOfTheMonth(Model model) {
        List<BookOfTheMonth> books = bookOfTheMonthDao.list("999");
        model.addAttribute("books", books);
        return "monthly-books/list";
    }

    /**
     * Displays the form for adding a new Book of the Month.
     *
     * @param model Spring Model to hold attributes
     * @return the new book form view
     */
    @GetMapping("/new")
    public String bookOfTheMonthForm(Model model) {
        model.addAttribute("months", getMonths());
        model.addAttribute("bookOfTheMonth", new BookOfTheMonth());
        return "monthly-books/new";
    }

    /**
     * Handles form submission for adding a new Book of the Month.
     *
     * @param bookOfTheMonth the form-backed BookOfTheMonth object
     * @param bindingResult  validation results
     * @param model          Spring Model for re-rendering form if needed
     * @return redirect to the list view if successful, otherwise return form view
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
     * Removes a Book of the Month by its ID.
     *
     * @param id the ID of the entry to remove
     * @return redirect to the list view
     */
    @GetMapping("/remove/{id}")
    public String removeBookOfTheMonth(@PathVariable String id) {
        bookOfTheMonthDao.remove(id);
        return "redirect:/monthly-books";
    }

    /**
     * Returns a map of month numbers to month names for select input.
     *
     * @return ordered map of month values
     */
    public Map<Integer, String> getMonths() {
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
