package org.tku.web.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tku.database.entity.Book;
import org.tku.database.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Controller
@Log4j2
public class BookStockController {


    private final BookRepository bookRepository;

    public BookStockController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping(value = "/web/book")
    public String index(@RequestParam(value = "bookSeq", required = false) Long bookSeq,
                        @RequestParam(value = "bookName", required = false) String bookName,
                        @RequestParam(value = "author", required = false) String author,
                        @RequestParam(value = "price", required = false) Long price,
                        Model model) {

        Book book = new Book();
        book.setBookSeq(bookSeq);
        book.setBookName(bookName);
        book.setAuthor(author);
        book.setPrice(price);
        List<Book> books = bookRepository.findBooksBySelective(book);
        model.addAttribute("books", books);
        model.addAttribute("book", book);
        return "book/index";
    }

    @GetMapping(value = "/web/book/{bookSeq}")
    public String bookInfo(@PathVariable(value = "bookSeq", required = false) Long bookSeq, Model model) {
        if(bookSeq == null) {
            Book book = new Book();
            book.setAction("create");
            model.addAttribute("book", book);
            return "book/detail";
        }
        Book book = bookRepository.findById(bookSeq).orElse(new Book());
        if(book.getBookSeq() == null) {
            book.setAction("create");
        }else {
            book.setAction("update");
        }
        model.addAttribute("book", book);
        return "book/detail";
    }

    @PostMapping(value = "/web/book")
    public String modifyBook(Long bookSeq,
                             String bookName,
                             String author,
                             Long price,
                             String action,
                             Model model) {
        log.info(bookSeq);
        if(!StringUtils.isBlank(action)){
            switch (action) {
                case "create":
                    Book book = new Book();
                    book.setBookSeq(bookSeq);
                    book.setBookName(bookName);
                    book.setAuthor(author);
                    book.setPrice(price);
                    bookRepository.save(book);
                    break;
                case "update":
                    Optional<Book> optionalBook = bookRepository.findById(bookSeq);
                    if(optionalBook.isEmpty()) {
                        break;
                    }
                    book = optionalBook.get();
                    book.setBookName(bookName);
                    book.setAuthor(author);
                    book.setPrice(price);
                    bookRepository.save(book);
                    break;
                case "delete":
                    bookRepository.deleteById(bookSeq);
            }
        }
        return "redirect:/web/book";
    }

}
