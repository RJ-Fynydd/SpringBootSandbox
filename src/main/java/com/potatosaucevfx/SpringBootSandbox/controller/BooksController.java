package com.potatosaucevfx.SpringBootSandbox.controller;

import com.potatosaucevfx.SpringBootSandbox.model.Book;
import com.potatosaucevfx.SpringBootSandbox.model.User;
import com.potatosaucevfx.SpringBootSandbox.service.BookService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public ModelAndView showBooksAddBook(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        ModelAndView mav = new ModelAndView("newBook");
        mav.addObject("book", new Book());
        return mav;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ModelAndView showPostAddBook(HttpServletRequest request, HttpServletResponse response, Authentication authentication, @ModelAttribute("book") Book book) {
        boolean success = false;
        User user = (User) authentication.getPrincipal();
        if (bookService.isBookValid(book)) {
            success = bookService.addBook(book, user);
        }

        if (success) {
            ModelAndView mav = new ModelAndView("redirect:/books");
            mav.addObject("books", bookService.getBooks());
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("newBook");
            mav.addObject("error", bookService.getBookErrorMessage(book));
            mav.addObject("book", book);
            return mav;
        }

    }

}
