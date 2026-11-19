package com.tamziy.Library.Management.Api.controller;

import com.tamziy.Library.Management.Api.Exception.BookException;
import com.tamziy.Library.Management.Api.payload.dto.BookDTO;
import com.tamziy.Library.Management.Api.payload.request.BookSearchRequest;
import com.tamziy.Library.Management.Api.payload.response.ApiResponse;
import com.tamziy.Library.Management.Api.payload.response.PageResponse;
import com.tamziy.Library.Management.Api.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }


    @PostMapping("/create")
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) throws BookException {
        BookDTO createdBook = bookService.createBook(bookDTO);
        return ResponseEntity.ok(createdBook);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<BookDTO>> createBooksBulk(@Valid @RequestBody List<BookDTO> bookDTOS) throws BookException {
        List<BookDTO> createdBooks = bookService.createBooksBulk(bookDTOS);
        return ResponseEntity.ok(createdBooks);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("bookId") Long bookId) throws BookException {
        BookDTO book = bookService.getBookByID(bookId);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> getBookByIsbn(@PathVariable("isbn") String isbn) throws BookException {
        BookDTO book = bookService.getBookByISBN(isbn);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable("bookId") Long bookId,
            @Valid @RequestBody BookDTO bookDTO) throws BookException {
        BookDTO updatedBook = bookService.updateBook(bookId, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable("bookId") Long bookId) throws BookException {
        bookService.deleteBook(bookId);
        ApiResponse response = new ApiResponse("Book deleted - soft delete", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{bookId}/hard")
    public ResponseEntity<ApiResponse> hardDeleteBook(@PathVariable("bookId") Long bookId) throws BookException {
        bookService.hardDeleteBook(bookId);
        ApiResponse response = new ApiResponse("Book deleted - hard delete", true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/search")
    public ResponseEntity<PageResponse<BookDTO>> searchBooks(@RequestBody BookSearchRequest searchRequest) {
        PageResponse<BookDTO> response = bookService.searchBookWithFilters(searchRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats/active-count")
    public ResponseEntity<Long> getTotalActiveBooks() {
        Long count = bookService.getTotalActiveBooks();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/stats/available-count")
    public ResponseEntity<Long> getTotalAvailableBooks() {
        Long count = bookService.getTotalAvailableBooks();
        return ResponseEntity.ok(count);
    }
}
