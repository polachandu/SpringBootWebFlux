package com.example.springreactiveexceptionalhandler.controller;

import com.example.springreactiveexceptionalhandler.dao.BookRepository;
import com.example.springreactiveexceptionalhandler.dto.Book;
import com.example.springreactiveexceptionalhandler.exception.BookAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    public Flux<Book> getAllBooks(){
        return bookRepository.getBooks();
    }

    @GetMapping("/{id}")
    public Mono<Book> getBookById(@PathVariable int id){
        return bookRepository.getBooks()
                .filter(book -> book.getId() == id)
                .next()
                .switchIfEmpty(Mono.error(new BookAPIException("Book not found with id: "+ id)));
    }
}
