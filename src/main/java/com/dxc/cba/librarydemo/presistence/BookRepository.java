package com.dxc.cba.librarydemo.presistence;


import com.dxc.cba.librarydemo.model.Book;

import org.springframework.data.repository.CrudRepository;


public interface BookRepository<Books> extends CrudRepository<Book,Long>{

}


