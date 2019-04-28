package com.books.on.rent.dao;

import com.books.on.rent.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksJpa extends JpaRepository<Book,String> {
}
