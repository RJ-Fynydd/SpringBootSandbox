package com.potatosaucevfx.SpringBootSandbox.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Richard Nader <richard.nader@fynydd.com>
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showHomePage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("welcome");

        return mav;
    }

}
