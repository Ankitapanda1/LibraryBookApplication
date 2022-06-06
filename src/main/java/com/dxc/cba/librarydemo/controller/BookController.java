package com.dxc.cba.librarydemo.controller;

import com.dxc.cba.librarydemo.exception.CustomLibraryException;
import com.dxc.cba.librarydemo.exception.NoSuchBookExistsException;
import com.dxc.cba.librarydemo.model.Book;
import com.dxc.cba.librarydemo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    Logger logger = LoggerFactory.getLogger( BookController.class );

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity <Book> addBook( @Valid @RequestBody Book bookRecord ) {


        return new ResponseEntity <>( bookService.addBook( bookRecord ) , HttpStatus.CREATED );

    }

    @GetMapping("/getAllbooks")
    public ResponseEntity <List <Book>> getAllBooks( ) {
        logger.debug( "Retriving all books..." );
        return new ResponseEntity <>( bookService.getAllBooks( ) , HttpStatus.OK );
    }


    @GetMapping("/getFilteredBooks")
    public ResponseEntity <List <Book>> getFilteredbooks(
            @RequestParam(required = false) final Long isbn ,
            @RequestParam(required = false) final String author ,
            @RequestParam(required = false) final String title ,
            @RequestParam(required = false) final Date published_date ) {

        return new ResponseEntity <>( bookService.getFilteredBooks( author , title , isbn , published_date ) , HttpStatus.OK );
    }


    @PutMapping("/updateBook/{id}")
    public ResponseEntity <Book> updateBook(
            @PathVariable("id") final Long id , @Valid @RequestBody final Book bookRecord ) {
               logger.debug( "####Updating book" + id );
        bookRecord.setId(id);
        return new ResponseEntity <>( bookService.updateBook( bookRecord) , HttpStatus.OK );

    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity <HttpStatus> deleteBook(
            @PathVariable("id") final Long id ) {
        bookService.deleteById( id );
        return new ResponseEntity <>( HttpStatus.OK );


    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity <Book> getBookById( @PathVariable("id") long id ) {
        return new ResponseEntity <Book>( bookService.getBookById( id ) , HttpStatus.OK );
    }


}


