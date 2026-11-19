package com.tamziy.Library.Management.Api.service;

import com.tamziy.Library.Management.Api.Exception.BookException;
import com.tamziy.Library.Management.Api.payload.dto.BookDTO;
import com.tamziy.Library.Management.Api.payload.request.BookSearchRequest;
import com.tamziy.Library.Management.Api.payload.response.PageResponse;

import java.awt.*;
import java.util.List;

public interface BookService {


    List<BookDTO> getAllBooks();

    BookDTO createBook(BookDTO bookDTO) throws BookException;
    BookDTO getBookByID(Long bookId) throws BookException;
    List<BookDTO> createBooksBulk(List<BookDTO> bookDTOS) throws BookException;
    BookDTO getBookByISBN(String isbn) throws BookException;
    BookDTO updateBook(Long bookId, BookDTO bookDTO) throws BookException;
    void deleteBook(Long bookId) throws BookException;
    void hardDeleteBook(Long bookId) throws BookException;

    PageResponse<BookDTO> searchBookWithFilters(BookSearchRequest searchRequest);

    long getTotalActiveBooks();

    long getTotalAvailableBooks();
}
