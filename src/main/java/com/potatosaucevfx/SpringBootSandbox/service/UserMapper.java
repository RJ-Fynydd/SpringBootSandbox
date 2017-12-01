/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

        return user;
    }
}
