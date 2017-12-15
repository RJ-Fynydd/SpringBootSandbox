package com.potatosaucevfx.SpringBootSandbox.service;

import com.potatosaucevfx.SpringBootSandbox.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PotatoSauceVFX
 */
@Repository
public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean isUserAvalible(User user) {
        if (!user.getUsername().equalsIgnoreCase("") && user.getUsername() != null) {
            if (!user.getPassword().equalsIgnoreCase("") && user.getPassword() != null) {
                String SQL = "SELECT username FROM users WHERE username='" + user.getUsername() + "'";
                List<User> users = jdbcTemplate.query(SQL, new UserMapper());
                if (users.isEmpty()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public String getUserErrorMessage(User user) {
        String SQL = "SELECT username FROM users WHERE username='" + user.getUsername() + "'";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper());
        if (!users.isEmpty()) {
            return "Username already in use";
        } else if (user.getUsername().equalsIgnoreCase("") || user.getUsername() == null) {
            return "Username can't be blank";
        } else if (user.getPassword().equalsIgnoreCase("") || user.getPassword() == null) {
            return "Password can't be blank";
        }

        return "";
    }

    public User getUserByUsername(String username) {
        String SQL = "SELECT users.username, users.password, users.enabled, user_roles.role FROM users INNER JOIN user_roles ON users.username = user_roles.username WHERE users.username = '" + username + "'";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper());
        return !users.isEmpty() ? users.get(0) : null;
    }

    public boolean addUserToDatabase(User user) {

        if (user.getPermission() == null || user.getPermission().equalsIgnoreCase("")) {
            user.setPermission("USER");
        }

        try {

            if (isUserAvalible(user)) {
                String SQL = "INSERT INTO users(username,password,enabled) VALUES ('" + user.getUsername() + "','" + passwordEncoder.encode(user.getPassword()) + "'," + user.isEnabled() + ")";
                jdbcTemplate.execute(SQL);
                SQL = "INSERT INTO user_roles (username, role) VALUES ('" + user.getUsername() + "', 'ROLE_" + user.getPermission() + "')";
                jdbcTemplate.execute(SQL);
                return true;
            } else {
                return false;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

}
