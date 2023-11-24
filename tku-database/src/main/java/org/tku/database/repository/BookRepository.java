package org.tku.database.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.tku.database.custom.BookRepositoryCustom;
import org.tku.database.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>, BookRepositoryCustom {
}
