package com.kirillnizhnik.bookmanager.dao;

import com.kirillnizhnik.bookmanager.models.Book;
import com.kirillnizhnik.bookmanager.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper<Person>(Person.class));
    }

    public void savePerson(Person person){
       jdbcTemplate.update
               ("INSERT INTO Person(name, surname, patronymic, dateOfBirth) VALUES(?,?,?,?)",
                person.getName(), person.getSurname(), person.getPatronymic(), person.getDateOfBirth());
    }

    public Person showPerson(int id) {
        return jdbcTemplate.query
                ("SELECT * FROM Person Where person_id = ?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE  FROM PERSON where person_id = ?", id);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE PERSON set name = ?, surname=?, patronymic=?, dateOfBirth=?  where person_id = ?",
                person.getName(), person.getSurname(), person.getPatronymic(), person.getDateOfBirth(), id );

    }

    public List<Book> getPersonBook(int id){
        return jdbcTemplate.query("SELECT * FROM book where person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

}
