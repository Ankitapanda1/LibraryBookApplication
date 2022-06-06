package com.dxc.cba.librarydemo.service;

import com.dxc.cba.librarydemo.model.Book;
import com.dxc.cba.librarydemo.presistence.BookRepository;
import com.dxc.cba.librarydemo.presistence.CustomBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service // for used with classes that provide some business functionalities
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository<Book> bookRepository;

    @Autowired
    private CustomBookRepository bookRepo;


    public List<Book> getAllBooks( ) {

        List<Book> book = new ArrayList<>( );
        bookRepository.findAll( ).forEach( book::add );
        return book;
    }


    public Book addBook( Book bookRecord ) {

        return bookRepository.save( bookRecord );
    }

    public Book updateBook( Book bookRecord ) {

        return bookRepository.save( bookRecord );
    }

    public void deleteById( Long id ) {

        bookRepository.deleteById( id );
    }

    public List<Book> getFilteredBooks( String author, String title, Long isbn, Date published ) {

        if (isbn == null && author == null && title == null) {
            return getAllBooks( );
        } else {
            return bookRepo.findBooksByCriteria( author, title, isbn, published );
        }
    }

    public void deleteAll( ) {
        bookRepository.deleteAll( );
    }
}


