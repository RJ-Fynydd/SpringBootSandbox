package com.potatosaucevfx.SpringBootSandbox.service;

import com.potatosaucevfx.SpringBootSandbox.model.Book;
import com.potatosaucevfx.SpringBootSandbox.model.User;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author PotatoSauceVFX
 */
@Repository
public class BookService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List getBooks() {
        List books = new ArrayList();
        String SQL = "SELECT * FROM books";
        books = jdbcTemplate.query(SQL, new BookMapper());

        if (books.isEmpty()) {
            return null;
        }
        return books;

    }

    public boolean addBook(Book book, User user) {

        String SQL = "INSERT INTO books (bookNumber, name, author, publishYear, publisher, imagePath, userAdded) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[]{book.getBookNumber(), book.getName(), book.getAuthor(), book.getPublishYear(), book.getPublisher(), book.getImagePath(), user.getUsername()};

        int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

        try {
            jdbcTemplate.update(SQL, params, types);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

}
