package com.potatosaucevfx.SpringBootSandbox.controller;

import com.potatosaucevfx.SpringBootSandbox.model.User;
import com.potatosaucevfx.SpringBootSandbox.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
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

    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public ModelAndView reciveNewUserPage(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
        System.out.println(user.toString());
        if (service.isUserAvalible(user)) {
            ModelAndView modelAndView = new ModelAndView("login");
            System.out.println(service.addUserToDatabase(user));
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/newUser");
            modelAndView.addObject("user", user);
            modelAndView.addObject("error", service.getUserErrorMessage(user));
            return modelAndView;
        }

    }

}
