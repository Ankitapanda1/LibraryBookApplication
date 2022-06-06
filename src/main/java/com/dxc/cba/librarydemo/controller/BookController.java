package com.dxc.cba.librarydemo.controller;

import java.sql.Date;
import java.util.List;
import com.dxc.cba.librarydemo.exception.CustomLibraryException;
import com.dxc.cba.librarydemo.exception.NoSuchBookExistsException;
import com.dxc.cba.librarydemo.model.Book;
import com.dxc.cba.librarydemo.service.BookService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.jboss.logging.Logger;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class BookController {
    Logger logger = LoggerFactory.logger( BookController.class );

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<Book> addBook( @Valid @RequestBody Book bookRecord ) {
        logger.debug( "Adding a new book..." );
        bookRecord.setCreatetime( new Date( System.currentTimeMillis() ) );
        bookRecord.setUpdatetime( new Date( System.currentTimeMillis() ) );
        if (bookRecord != null)
            return new ResponseEntity<>( bookService.addBook( bookRecord ), HttpStatus.CREATED );
        else {
            throw new CustomLibraryException( "Book Record is invalid" );
        }
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks( ) {
        logger.debug( "Retriving all books..." );
        return new ResponseEntity<>( bookService.getAllBooks(), HttpStatus.OK );
    }


    @GetMapping("/book")
    public ResponseEntity<List<Book>> getFilteredbooks(
            @RequestParam(required = false) final Long isbn,
            @RequestParam(required = false) final String author,
            @RequestParam(required = false) final String title,
            @RequestParam(required = false) final Date published_date ) {

        return new ResponseEntity<>( bookService.getFilteredBooks( author, title, isbn, published_date ), HttpStatus.OK );
    }


    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable("id") final Long id, @Valid @RequestBody final Book bookRecord ) {
        logger.debug( "####Updating book" + id );
        if (bookRecord != null) {
            bookRecord.setId( id );
            bookRecord.setUpdatetime( new Date( System.currentTimeMillis() ) );
            return new ResponseEntity<>( bookService.updateBook( bookRecord ), HttpStatus.OK );
        } else {
            throw new NoSuchBookExistsException( "No Such Book exists!!" );
        }

    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<HttpStatus> deleteBook(
            @PathVariable("id") final Long id ) {
        try {
            bookService.deleteById( id );
            return new ResponseEntity<>( HttpStatus.OK );
        } catch (Exception e) {
            throw new NoSuchBookExistsException( "No Such Book exists with this id!!" );
        }

    }


}


