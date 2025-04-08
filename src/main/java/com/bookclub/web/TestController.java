// File: src/main/java/com/bookclub/web/TestController.java

package com.bookclub.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "index";
    }
}
