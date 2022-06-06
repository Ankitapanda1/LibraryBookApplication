package com.dxc.cba.librarydemo.service;

import com.dxc.cba.librarydemo.exception.NoSuchBookExistsException;
import com.dxc.cba.librarydemo.model.Book;
import com.dxc.cba.librarydemo.presistence.BookRepository;
import com.dxc.cba.librarydemo.presistence.CustomBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // for used with classes that provide some business functionalities
public class BookServiceImpl implements BookService {

    Logger logger = LoggerFactory.getLogger( BookServiceImpl.class );
    @Autowired
    private BookRepository<Book> bookRepository;

    @Autowired
    private CustomBookRepository bookRepo;

    public List<Book> getAllBooks( ) {
        List<Book> book = new ArrayList<>( );
        bookRepository.findAll( ).forEach( book::add );
        if ( ObjectUtils.isEmpty( book ) ) {
            throw new NoSuchBookExistsException( );
        }
        return book;
    }

    @Override
    public Optional <Book> findById( long id ) {
        return  bookRepository.findById(id  );
    }


    @Override
    public Book getBookById( Long id ) {
        logger.debug( "getting Book by Id..." );
        Optional<Book> book = bookRepository.findById( id );
        if ( ( (Optional<?>) book ).isPresent( ) ) {
            return book.get();
        } else {
            throw new NoSuchBookExistsException( "No Such Book exists with this id!!" );
        }

    }

    public Book addBook( Book bookRecord ) {
        logger.debug( "Add Book ..." );
        if ( !ObjectUtils.isEmpty( bookRecord ) ) {
            logger.debug( "Adding a new book..." );
            bookRecord.setCreatetime( new Date( System.currentTimeMillis() ) );
            bookRecord.setUpdatetime( new Date( System.currentTimeMillis() ) );
            Book book = bookRepository.save( bookRecord );
            return book;

        } else {
            throw new NoSuchBookExistsException( "No Such Book exists with this id!!" );
        }

    }

    public Book updateBook( Book bookRecord ) {
        logger.debug( "update Book..." );
        Book book = getBookId(bookRecord.getId() );
        if (book!=null){
            bookRecord.setUpdatetime( new Date( System.currentTimeMillis( ) ) );
            return bookRepository.save( bookRecord );

        } else {
            throw new NoSuchBookExistsException( "No Such Book exists with this id!!" );
        }
    }

    public void deleteById( Long id ) {
        logger.debug( "delete Book by id..." );
        try {
            bookRepository.deleteById( id );
        } catch (Exception e) {
            throw new NoSuchBookExistsException( "No Such Book exists with this id!!" );
        }
        ;
    }

    public List<Book> getFilteredBooks( String author , String title , Long isbn , Date published ) {

        if ( isbn == null && author == null && title == null ) {
            return getAllBooks( );
        } else {
            return bookRepo.findBooksByCriteria( author , title , isbn , published );
        }
    }

    public void deleteAll( ) {

        bookRepository.deleteAll( );
    }
    private Book getBookId(long id) {
        Optional <Book> bookObj = bookRepository.findById(id);

        if (bookObj.isPresent()) {
            return bookObj.get();
        }
        return null;
    }
}


