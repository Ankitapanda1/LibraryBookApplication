package com.dxc.cba.librarydemo.presistence;

import java.sql.Date;
import java.util.List;

import com.dxc.cba.librarydemo.model.Book;

import org.springframework.stereotype.Repository;
@Repository
public interface CustomBookRepository {
    public List<Book> findBooksByCriteria(String author,String title,Long isbn,Date published);
}
