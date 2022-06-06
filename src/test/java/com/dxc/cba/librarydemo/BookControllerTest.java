package com.dxc.cba.librarydemo;

import com.dxc.cba.librarydemo.controller.BookController;
import com.dxc.cba.librarydemo.model.Book;
import com.dxc.cba.librarydemo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    static Book testBook=new Book();
    @Test
    public void testGetBooks() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = getStringLinkedMultiValueMap( );
        testBookData();
        List<Book> bookArrayList=new ArrayList<>();
        bookArrayList.add( testBook );
        bookArrayList.add( testBook );
        when( bookService.getAllBooks()).thenReturn(bookArrayList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/books/getAllbooks").params(requestParams);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());

    }
    @Test
    public void testAddBook() throws Exception {
        when(bookService.addBook( any(Book.class))).thenReturn(testBookData());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                        "/api/books/addBook").accept( MediaType.APPLICATION_JSON).content(asJsonString(testBookData() ))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isCreated());

    }
    private Book testBookData( )
             {

        testBook.setId(18l);
        testBook.setTitle("ankitatest");
        testBook.setUpdatetime(new Date(System.currentTimeMillis()));
        testBook.setAuthor("ankita");
        testBook.setIsbn(1023456789122l);
                 testBook.setPublished( new Date(System.currentTimeMillis() ));
        return testBook;
    }

    private LinkedMultiValueMap<String, String> getStringLinkedMultiValueMap( ) {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("title", "test");
        requestParams.add("author", "test1");
        requestParams.add("isbn", "1234567891012");
        return requestParams;
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testDeleteBook() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/books/deleteBook/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void testUpdateBook() throws Exception {

        when(bookService.updateBook(any())).thenReturn( testBookData());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                        "/api/books/updateBook/{id}",1L).accept(MediaType.APPLICATION_JSON).content(asJsonString(testBookData()))
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

}
