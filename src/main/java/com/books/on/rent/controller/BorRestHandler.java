package com.books.on.rent.controller;

import com.books.on.rent.model.Book;
import com.books.on.rent.service.BORService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BorRestHandler {

    @Autowired
    BORService service;

    public List<Book> getAllBooks(){
        return service.getAllBooks();
    }

    public Book getBook(String id){
        return service.getBook(id);
    }

}
