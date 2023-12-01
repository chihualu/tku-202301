package org.tku.database.custom.impl;

import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.tku.database.custom.BookRepositoryCustom;
import org.tku.database.entity.Book;
import org.tku.database.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final BookRepository bookRepository;

    public BookRepositoryCustomImpl(@Lazy BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> findBooksBySelective(Book book) {
        return bookRepository.findAll((root, query, cb) -> {
            query.distinct(true);
            query.orderBy(cb.asc(root.get("bookSeq")));

            final List<Predicate> predicates = new ArrayList<>();
            if (book.getBookSeq() != null) {
                predicates.add(cb.equal(root.get("bookSeq"), book.getBookSeq()));
            }

            if (StringUtils.isNotBlank(book.getBookName())) {
                predicates.add(cb.like(root.get("bookName"), "%" + book.getBookName() + "%"));
            }

            if (StringUtils.isNotBlank(book.getAuthor())) {
                predicates.add(cb.like(root.get("author"), "%" + book.getAuthor() + "%"));
            }

            if (book.getPrice() != null) {
                predicates.add(cb.equal(root.get("price"), book.getPrice()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
