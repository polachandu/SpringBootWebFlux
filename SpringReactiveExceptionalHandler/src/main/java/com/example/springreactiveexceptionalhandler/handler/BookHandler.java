package com.example.springreactiveexceptionalhandler.handler;

import com.example.springreactiveexceptionalhandler.dao.BookRepository;
import com.example.springreactiveexceptionalhandler.dto.Book;
import com.example.springreactiveexceptionalhandler.exception.BookAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookHandler {

    @Autowired
    private BookRepository bookRepository;

    public Mono<ServerResponse> getAllBooks(ServerRequest request){
        Flux<Book> bookFlux = bookRepository.getBooks();
        return ServerResponse.ok().body(bookFlux,Book.class);
    }

    public Mono<ServerResponse> getBookById(ServerRequest request){
        int id = Integer.parseInt(request.pathVariable("bookId"));
        Mono<Book> bookMono = bookRepository.getBooks()
                .filter(book -> book.getId() == id)
                .next().switchIfEmpty(Mono.error(new BookAPIException("Book not found with id: "+ id)));
        return ServerResponse.ok().body(bookMono,Book.class);

    }
}
