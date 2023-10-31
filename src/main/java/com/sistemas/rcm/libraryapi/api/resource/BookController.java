package com.sistemas.rcm.libraryapi.api.resource;

import com.sistemas.rcm.libraryapi.api.dto.BookDTO;
import com.sistemas.rcm.libraryapi.api.model.entity.Book;
import com.sistemas.rcm.libraryapi.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody BookDTO bookDTO) {
        Book savedBook = Book.builder().author(bookDTO.getAuthor()).title(bookDTO.getAuthor()).isbn(bookDTO.getIsbn()).build();
        savedBook = bookService.save(savedBook);
        return BookDTO.builder()
                .id(savedBook.getId())
                .author(savedBook.getAuthor())
                .title(savedBook.getTitle())
                .isbn(savedBook.getIsbn()).build();
    }
}
