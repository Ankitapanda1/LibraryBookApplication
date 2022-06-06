package com.dxc.cba.librarydemo.presistence;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dxc.cba.librarydemo.model.Book;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
public class CustomBookRepositoryImpl implements CustomBookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findBooksByCriteria( String author , String title , Long isbn , Date published ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder( );
        CriteriaQuery<Book> cq = cb.createQuery( Book.class );

        Root<Book> book = cq.from( Book.class );
        List<Predicate> predicates = new ArrayList<>( );

        if (!ObjectUtils.isEmpty( author )) {
            predicates.add( cb.like( book.get( "author" ) , "%" + author + "%" ) );
        }
        if (!ObjectUtils.isEmpty( title )) {
            predicates.add( cb.like( book.get( "title" ) , "%" + title + "%" ) );
        }
        if (!ObjectUtils.isEmpty( isbn )) {
            predicates.add( cb.equal( book.get( "isbn" ) , isbn ) );
        }

        Predicate predicateForGrade = cb.and( predicates.toArray( new Predicate[ 0 ] ) );
        cq.where( predicateForGrade );

        return entityManager.createQuery( cq ).getResultList( );
    }
}