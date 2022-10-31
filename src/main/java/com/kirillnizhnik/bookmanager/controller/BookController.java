package com.kirillnizhnik.bookmanager.controller;

import com.kirillnizhnik.bookmanager.dao.BookDAO;
import com.kirillnizhnik.bookmanager.dao.PersonDAO;
import com.kirillnizhnik.bookmanager.models.Book;
import com.kirillnizhnik.bookmanager.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/book")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String bookIndex(Model model) {
        List<Book> bookList = bookDAO.index();

        System.out.println(bookList);;
        model.addAttribute("bookList", bookList);
        return "book/indexBook";

    }

    @GetMapping("/create")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "book/new-book";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book")@Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "book/new-book";
        bookDAO.saveBook(book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/book";
    }

    @GetMapping("/{id}")
    public String showBook(@ModelAttribute("people")Person people,@PathVariable("id")int id, Model model){
        Book book = bookDAO.showBook(id);
        Boolean bookIsBusy = book.getPersonId() == null;
        if(book.getPersonId() == null){
            List<Person> personList = personDAO.index();
            model.addAttribute("personList", personList);
        }
        else {
            Person person = personDAO.showPerson(id);
            model.addAttribute("person", person);
        }

        model.addAttribute("bookIsBusy", bookIsBusy);
        model.addAttribute("book", book);
        return "book/showBook";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id")int id, Model model){
        Book book = bookDAO.showBook(id);
        model.addAttribute("book" , book);
        return "book/editBook";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult, @PathVariable("id")int id){
        if(bindingResult.hasErrors())
            return "book/editBook";
        bookDAO.update(id, book);
        return ("redirect:/book");
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id")int id){
        bookDAO.releaseBook(id);
        return "redirect:/book/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assignBook(@PathVariable("id")int id, @ModelAttribute("person")Person person){
        bookDAO.assignBook(id, person);
        return "redirect:/book/" + id;
    }


}
