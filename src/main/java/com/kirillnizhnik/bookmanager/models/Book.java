package com.kirillnizhnik.bookmanager.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

        private int book_id;
        private Integer personId;



        @Size(min = 2, max = 100, message = "Min 2 and Max 100")
        @NotEmpty
        private String bookName;

        @Min(value = 300)
        @Max(2022)
        private int yearOfIssue;

        public Integer getPersonId() {
                return personId;
        }

        public void setPersonId(Integer personId) {
                this.personId = personId;
        }

        public int getBook_id() {
                return book_id;
        }

        public void setBook_id(int book_id) {
                this.book_id = book_id;
        }


        public String getBookName() {
                return bookName;
        }

        public void setBookName(String bookName) {
                this.bookName = bookName;
        }

        public int getYearOfIssue() {
                return yearOfIssue;
        }

        public void setYearOfIssue(int yearOfIssue) {
                this.yearOfIssue = yearOfIssue;
        }

        @Override
        public String toString() {
                return "Book{" +
                        "book_id=" + book_id +
                        ", person_id=" + personId +
                        ", bookName='" + bookName + '\'' +
                        ", yearOfIssue=" + yearOfIssue +
                        '}';
        }
}
