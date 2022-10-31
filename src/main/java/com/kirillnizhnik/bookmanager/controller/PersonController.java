package com.kirillnizhnik.bookmanager.controller;

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
@RequestMapping("/person")
public class PersonController {
    private final PersonDAO personDao;

    public PersonController(PersonDAO personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String indexPerson(Model model){
        List<Person> personList = personDao.index();
        model.addAttribute("personList", personList);
        return "person/indexPerson";
    }

    @GetMapping("/create")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "person/new-person";

    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person")@Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "person/new-person";}
        personDao.savePerson(person);
        return "redirect:/person";

    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id" )int id, Model model){
        Person person = personDao.showPerson(id);
        model.addAttribute("person", person);
        List<Book> bookList= personDao.getPersonBook(id);
        boolean bool= bookList.size() != 0;
        model.addAttribute("isEmpty", bool );
        model.addAttribute("bookList", bookList);
        return "person/showPerson";

    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id")int id){
        personDao.delete(id);
        return "redirect:/person";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id")int id, Model model){
        Person person = personDao.showPerson(id);
        model.addAttribute("person", person);
        return "person/editPerson";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person")@Valid Person person,
                               BindingResult bindingResult, @PathVariable("id")int id){
        if (bindingResult.hasErrors())
            return "person/editPerson";
        personDao.update(id, person);
        return "redirect:/person";
    }





}
