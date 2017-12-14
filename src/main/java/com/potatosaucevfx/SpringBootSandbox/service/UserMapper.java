package com.potatosaucevfx.SpringBootSandbox.service;

import com.potatosaucevfx.SpringBootSandbox.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author PotatoSauceVFX
 */
public class UserMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        User user = new User();

        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setPermission(rs.getString("role"));

        return user;
    }
}
