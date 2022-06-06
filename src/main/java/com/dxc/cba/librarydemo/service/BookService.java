package com.dxc.cba.librarydemo.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.dxc.cba.librarydemo.model.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    public Book getBookById( Long id);

    public Book addBook( Book bookRecord );

    public Book updateBook( Book bookRecord );

    public void deleteById( Long id );

    public List<Book> getFilteredBooks( String author, String title, Long isbn, Date published );
    public List<Book> getAllBooks( );

    Optional<Book> findById( long id );
}