package com.kirillnizhnik.bookmanager.dao;

import com.kirillnizhnik.bookmanager.models.Book;
import com.kirillnizhnik.bookmanager.models.Person;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveBook(Book book) {
        jdbcTemplate.update
        ("INSERT INTO BOOK(bookName, yearOfIssue) VALUES(?,?)", book.getBookName(), book.getYearOfIssue());
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT*FROM book", new BeanPropertyRowMapper<Book>(Book.class));

    }

    public Book showBook(int id){
        return jdbcTemplate.query("SELECT * FROM BOOK where book_id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book where book_id = ?", id);
    }


    public void update(int id,Book book) {
        jdbcTemplate.update("UPDATE book set bookName = ?, yearOfIssue = ? where book_id = ?", book.getBookName(), book.getYearOfIssue(), id );
    }

    public void releaseBook(int id) {
        jdbcTemplate.update("UPDATE book set person_id = null where book_id = ?", id);
    }

    public void assignBook(int id, Person person) {
        jdbcTemplate.update("UPDATE book set  person_id = ? where  book_id= ?", person.getPerson_id(), id);
    }
}
