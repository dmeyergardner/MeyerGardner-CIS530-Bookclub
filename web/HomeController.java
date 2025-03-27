package com.example.bookclub.controller;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String showHome() {
        return "index";
    }

    @RequestMapping("/about")
    public String showAboutUs() {
        return "about";
    }

    @RequestMapping("/contact")
    public String showContactUs() {
        return "contact";
    }
}
