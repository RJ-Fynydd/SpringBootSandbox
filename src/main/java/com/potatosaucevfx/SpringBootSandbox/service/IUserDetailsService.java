package com.potatosaucevfx.SpringBootSandbox.service;

import com.potatosaucevfx.SpringBootSandbox.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author PotatoSauceVFX
 */
@Component
public class IUserDetailsService implements UserDetailsService {

    @Autowired
    UserService service;

    @Override
    public User loadUserByUsername(String string) throws UsernameNotFoundException {

        try {
            User user = service.getUserByUsername(string);
            if (user == null) {
                System.out.println("User " + user + " does not exist.");
                throw new UsernameNotFoundException("User not found!");
            }
            System.out.println("Logged in: " + user.toString());
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User not found!");
        }

    }

}
