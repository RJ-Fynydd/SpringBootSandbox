package com.potatosaucevfx.SpringBootSandbox.controller;

import com.potatosaucevfx.SpringBootSandbox.model.User;
import com.potatosaucevfx.SpringBootSandbox.service.BookService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author PotatoSauceVFX
 */
@Controller
public class BooksController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView showBooksList(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books", bookService.getBooks());
        return mav;
    }

}
