package com.books.on.rent.service;

import com.books.on.rent.dao.BooksJpa;
import com.books.on.rent.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BORService {

    @Autowired
    BooksJpa repository;

    public Book getBook(String id){
        return repository.getOne(id);
    }

    public List<Book> getAllBooks(){
        return repository.findAll();
    }
}
