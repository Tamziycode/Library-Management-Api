package com.tamziy.Library.Management.Api.service;

import com.tamziy.Library.Management.Api.payload.dto.BookDTO;
import com.tamziy.Library.Management.Api.payload.request.BookSearchRequest;
import com.tamziy.Library.Management.Api.payload.response.PageResponse;

import java.awt.*;
import java.util.List;

public interface BookService {

    BookDTO createBook(BookDTO bookDTO);
    BookDTO getBookByID(Long bookId);
    List<BookDTO> createBooksBulk();
    BookDTO getBookByISBN(String isbn);
    BookDTO updateBook(Long bookId, BookDTO bookDTO);
    void deleteBook(Long bookId);
    void hardDeleteBook(Long bookId);

    PageResponse<BookDTO> searchBookWithFilters(BookSearchRequest searchRequest);

    long getTotalActiveBooks();

    long getTotalAvailableBooks();
}
