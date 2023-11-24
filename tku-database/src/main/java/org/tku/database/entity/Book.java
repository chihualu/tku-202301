package org.tku.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "book_seq")
    private Long bookSeq;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author")
    private String author;
    @Column(name = "price")
    private Long price;

    @Transient
    private String action;
}
