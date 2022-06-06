package com.dxc.cba.librarydemo.service;

import java.sql.Date;
import java.util.List;
import com.dxc.cba.librarydemo.model.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    public List<Book> getAllBooks( );

    public Book addBook( Book bookRecord );

    public Book updateBook( Book bookRecord );

    public void deleteById( Long id );

    public List<Book> getFilteredBooks( String author, String title, Long isbn, Date published );
}