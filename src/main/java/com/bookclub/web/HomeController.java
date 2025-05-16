package com.bookclub.web;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookclub.dao.BookOfTheMonthDao;
import com.bookclub.model.Book;
import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookDao;

/**
 * HomeController handles routing for the Bookclub application,
 * specifically the homepage and loading dynamic book data for the current
 * month.
 */
@Controller
public class HomeController {

    private BookOfTheMonthDao bookOfTheMonthDao = null;
    private BookDao bookDao = null;

    /**
     * Injects the BookOfTheMonthDao bean.
     * 
     * @param dao injected DAO for accessing monthly books
     */
    @Autowired
    public void setBookOfTheMonthDao(BookOfTheMonthDao dao) {
        this.bookOfTheMonthDao = dao;
    }

    /**
     * Injects the BookDao bean for accessing OpenLibrary API.
     * 
     * @param dao REST-based book DAO
     */
    @Autowired
    public void setBookDao(BookDao dao) {
        this.bookDao = dao;
    }

    /**
     * Displays the homepage and fetches books for the current month.
     *
     * @param model Spring model to pass attributes to the view
     * @return index.html
     */
    @GetMapping("/")
    public String showHome(Model model) {
        int currentMonth = LocalDate.now().getMonthValue();

        // Get book metadata for the current month from MongoDB
        List<BookOfTheMonth> booksOfTheMonth = bookOfTheMonthDao.list(String.valueOf(currentMonth));

        // Build the ISBN query string (e.g., "ISBN:123,ISBN:456")
        String isbnString = booksOfTheMonth.stream()
                .map(book -> "ISBN:" + book.getIsbn())
                .collect(Collectors.joining(","));

        // Query OpenLibrary for book details
        List<Book> books = bookDao.list(isbnString);

        model.addAttribute("books", books);
        return "index";
    }
}
