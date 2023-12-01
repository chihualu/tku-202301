package org.tku.database.custom;

import org.tku.database.entity.Book;

import java.util.List;

public interface BookRepositoryCustom {

    List<Book> findBooksBySelective(Book book);
}
