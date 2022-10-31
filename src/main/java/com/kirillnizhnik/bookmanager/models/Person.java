package com.kirillnizhnik.bookmanager.models;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private int person_id;
    @NotEmpty
    @Size(min = 2, max = 30, message = "name cannot be more than 2 and less than 30 characters")
    private String name;
    @NotEmpty
    @Size(min = 2, max = 30, message = "surname cannot be more than 2 and less than 30 characters")
    private String surname;
    @NotEmpty
    @Size(min = 2, max = 30, message = "patronymic cannot be more than 2 and less than 30 characters")
    private String patronymic;


    @Min(value = 1940, message = "min year of birth 1940")
    @Max(value = 2022, message = "max year of birth 2022")
    private int dateOfBirth;


    public Person() {
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
