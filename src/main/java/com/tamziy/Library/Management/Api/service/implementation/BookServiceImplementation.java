package com.tamziy.Library.Management.Api.service.implementation;

import com.tamziy.Library.Management.Api.payload.dto.BookDTO;
import com.tamziy.Library.Management.Api.payload.request.BookSearchRequest;
import com.tamziy.Library.Management.Api.payload.response.PageResponse;
import com.tamziy.Library.Management.Api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImplementation implements BookService {
    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public BookDTO getBookByID(Long bookId) {
        return null;
    }

    @Override
    public List<BookDTO> createBooksBulk() {
        return List.of();
    }

    @Override
    public BookDTO getBookByISBN(String isbn) {
        return null;
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDTO) {
        return null;
    }

    @Override
    public void deleteBook(Long bookId) {

    }

    @Override
    public void hardDeleteBook(Long bookId) {

    }

    @Override
    public PageResponse<BookDTO> searchBookWithFilters(BookSearchRequest searchRequest) {
        return null;
    }

    @Override
    public long getTotalActiveBooks() {
        return 0;
    }

    @Override
    public long getTotalAvailableBooks() {
        return 0;
    }
}
