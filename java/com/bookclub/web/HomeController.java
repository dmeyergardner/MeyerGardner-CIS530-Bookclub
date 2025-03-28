/******************************************************************************
 * File: HomeController.java
 * Author: Deb Meyer-Gardner
 * Created: 2025-03-26
 * Description: This controller handles routing for the Bookclub application,
 *              including the home, about, and contact pages.
 ******************************************************************************/
package java.com.bookclub.web;


/**
 * Controller for handling navigation routes
 */
@Controller
@RequestMapping("/")
public class HomeController {
/**
     * Displays the landing page
     * @return index view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {
        return "index";
    }
// About Us page route
    @RequestMapping(method = RequestMethod.GET, path = "/about")
    public String showAboutUs(Modal model) {
        return "about";
    }
// Contact Us page route
    @RequestMapping(method = RequestMethod.GET, path = "/contact")
    public String showContactUs(Model model) {
        return "contact";
    }
}
@RequestMapping("/")
    public String showHome(Model model) {
        MemBookDao bookDao = new MemBookDao();
        List<Book> books = bookDao.list();
        model.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getMonthlyBook(@PathVariable("id") String id, Model model) {
        MemBookDao bookDao = new MemBookDao();
        Book book = bookDao.find(id);
        model.addAttribute("book", book);
        return "monthly-books/view";
    }