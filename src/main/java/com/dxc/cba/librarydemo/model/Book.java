package com.dxc.cba.librarydemo.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long  id;
    @NotNull(message = "ISBN number cannot be empty")
    @NumberFormat(style = Style.NUMBER)
    @ISBNValidation(message = "ISBN Must be a 13 digit numeric value")
    private Long isbn;
    @NotBlank(message = "Title is mandatory")
    private String title;
        @NotBlank(message = "Author is mandatory")
    private String author;
   
    @NotNull(message = "Published date cannot be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date published;

     private Date createtime=new Date(System.currentTimeMillis());

    private Date updatetime=new Date(System.currentTimeMillis());




    public Book(Long isbn, String title, String author, Date published, Date createtime, Date updatetime) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.published = published;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }


    @Override
    public String toString() {
        return "Book [author=" + author + ", createtime=" + createtime + ", id=" + id + ", isbn=" + isbn
                + ", published=" + published + ", title=" + title + ", updatetime=" + updatetime + "]";
    }


    public void setId(Long id2) {
        this.id=id2;
    }
    
}
