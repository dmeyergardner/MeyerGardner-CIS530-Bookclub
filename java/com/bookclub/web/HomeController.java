/******************************************************************************
 * File: HomeController.java
 * Author: Deb Meyer-Gardner
 * Created: 2025-03-26
 * Description: This controller handles routing for the Bookclub application,
 *              including the home, about, contact pages, and the dynamic
 *              detail view for individual book selections.
 ******************************************************************************/

 package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.service.impl.MemBookDao;
import java.util.List;


 
 /**
  * Controller class for handling user navigation routes in the Bookclub application.
  * Routes include the landing page, static content pages (About, Contact), and
  * dynamic views for individual book selections.
  */
 @Controller
 @RequestMapping("/")
 public class HomeController {
 
     /**
      * Displays the home page and populates it with a list of Book objects
      * retrieved from the memory-based DAO implementation.
      *
      * @param model the Spring model used to pass attributes to the view
      * @return the name of the Thymeleaf template to render (index.html)
      */
     @RequestMapping(method = RequestMethod.GET)
     public String showHome(Model model) {
         MemBookDao bookDao = new MemBookDao();
         List<Book> books = bookDao.list();
         model.addAttribute("books", books);
         return "index";
     }
 
     /**
      * Displays the About Us page.
      *
      * @param model the Spring model used to pass attributes to the view (if needed)
      * @return the name of the Thymeleaf template to render (about.html)
      */
     @RequestMapping(method = RequestMethod.GET, path = "/about")
     public String showAboutUs(Model model) {
         return "about";
     }
 
     /**
      * Displays the Contact Us page.
      *
      * @param model the Spring model used to pass attributes to the view (if needed)
      * @return the name of the Thymeleaf template to render (contact.html)
      */
     @RequestMapping(method = RequestMethod.GET, path = "/contact")
     public String showContactUs(Model model) {
         return "contact";
     }
 
     /**
      * Displays the detail view for a selected Book based on its ISBN.
      *
      * @param id the ISBN of the selected book (retrieved from the URL path)
      * @param model the Spring model used to pass the book to the view
      * @return the name of the Thymeleaf template to render (monthly-books/view.html)
      */
     @RequestMapping(value = "/{id}", method = RequestMethod.GET)
     public String getMonthlyBook(@PathVariable("id") String id, Model model) {
         MemBookDao bookDao = new MemBookDao();
         Book book = bookDao.find(id);
         model.addAttribute("book", book);
         return "monthly-books/view";
     }
 }
 