package com.potatosaucevfx.SpringBootSandbox.service;

import com.potatosaucevfx.SpringBootSandbox.model.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author PotatoSauceVFX
 */
public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int i) throws SQLException {

        Book book = new Book();
        book.setBookID(rs.getInt("bookID"));
        book.setBookNumber(rs.getString("bookNumber"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setPublishYear(rs.getInt("publishYear"));
        book.setPublisher(rs.getString("publisher"));
        book.setImagePath(rs.getString("imagePath"));
        book.setUserAdded(rs.getString("userAdded"));
        return book;
    }

}
