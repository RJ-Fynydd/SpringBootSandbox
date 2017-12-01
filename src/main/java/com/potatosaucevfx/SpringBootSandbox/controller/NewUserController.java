/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.potatosaucevfx.SpringBootSandbox.controller;

import com.potatosaucevfx.SpringBootSandbox.model.User;
import com.potatosaucevfx.SpringBootSandbox.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class NewUserController {

    @Autowired
    UserService service;

    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public ModelAndView showNewUserPage(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView("newUser");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView reciveNewUserPage(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {

        if (service.isUserValid(user)) {
            ModelAndView modelAndView = new ModelAndView("home");
            service.addUserToDatabase(user);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("newUser");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("error", service.getUserErrorMessage(user));
            return modelAndView;
        }

    }

}
