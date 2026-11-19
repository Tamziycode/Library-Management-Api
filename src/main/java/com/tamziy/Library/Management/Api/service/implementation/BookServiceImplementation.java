package com.tamziy.Library.Management.Api.service.implementation;

import com.tamziy.Library.Management.Api.Exception.BookException;
import com.tamziy.Library.Management.Api.mapper.BookMapper;
import com.tamziy.Library.Management.Api.modal.BookEntity;
import com.tamziy.Library.Management.Api.payload.dto.BookDTO;
import com.tamziy.Library.Management.Api.payload.request.BookSearchRequest;
import com.tamziy.Library.Management.Api.payload.response.PageResponse;
import com.tamziy.Library.Management.Api.repository.BookRepository;
import com.tamziy.Library.Management.Api.service.BookService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDTO createBook(BookDTO bookDTO) throws BookException {

        if (bookRepository.existsByIsbn(bookDTO.getIsbn())) {
            throw new BookException("book with isbn " + bookDTO.getIsbn() + " already exists");
        }
        BookEntity book = bookMapper.toEntity(bookDTO);

        if (!book.isAvailableCopiesValid()) {
            throw new BookException("Available copies cannot exceed total copies");
        }
        BookEntity savedBook = bookRepository.save(book);

        return bookMapper.toDTO(savedBook);
    }

    @Override
    public BookDTO getBookByID(Long bookId) throws BookException {

        BookEntity book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("book not found"));
        return bookMapper.toDTO(book);
    }

    @Override
    public List<BookDTO> createBooksBulk(List<BookDTO> bookDTOS) throws BookException {

        List<BookDTO> createdBooks = new ArrayList<>();
        for (BookDTO bookDTO : bookDTOS) {
            BookDTO book = createBook(bookDTO);
            createdBooks.add(book);
        }
        return createdBooks;
    }

    @Override
    public BookDTO getBookByISBN(String isbn) throws BookException {
        BookEntity book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookException("Book not found with ISBN: " + isbn));
        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDTO) throws BookException {
        BookEntity existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("Book not found with id: " + bookId));

        if (!existingBook.getIsbn().equals(bookDTO.getIsbn()) && bookRepository.existsByIsbn(bookDTO.getIsbn())) {
            throw new BookException("Book with ISBN " + bookDTO.getIsbn() + " already exists");
        }

        bookMapper.updateEntityFromDTO(bookDTO, existingBook);

        if (!existingBook.isAvailableCopiesValid()) {
            throw new BookException("Available copies cannot exceed total copies");
        }

        BookEntity updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDTO(updatedBook);
    }

    @Override
    public void deleteBook(Long bookId) throws BookException {
        BookEntity book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("Book not found with id: " + bookId));
        book.setActive(false);
        bookRepository.save(book);
    }

    @Override
    public void hardDeleteBook(Long bookId) throws BookException {
        BookEntity book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("Book not found with id: " + bookId));
        bookRepository.delete(book);
    }

    @Override
    public PageResponse<BookDTO> searchBookWithFilters(BookSearchRequest searchRequest) {
        Sort sort = searchRequest.getSortDirection().equalsIgnoreCase("DESC")
                ? Sort.by(searchRequest.getSortBy()).descending()
                : Sort.by(searchRequest.getSortBy()).ascending();

        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getSize(), sort);

        Page<BookEntity> bookPage = bookRepository.searchBooksWithFilters(
                searchRequest.getSearchTerm(),
                searchRequest.getGenreId(),
                searchRequest.getAvailableOnly(),
                pageable
        );

        List<BookDTO> bookDTOs = bookPage.getContent().stream()
                .map(bookMapper::toDTO)
                .toList();

        return PageResponse.<BookDTO>builder()
                .content(bookDTOs)
                .pageNumber(bookPage.getNumber())
                .pageSize(bookPage.getSize())
                .totalElements(bookPage.getTotalElements())
                .totalPages(bookPage.getTotalPages())
                .last(bookPage.isLast())
                .first(bookPage.isFirst())
                .empty(bookPage.isEmpty())
                .build();
    }

    @Override
    public long getTotalActiveBooks() {
        return bookRepository.countByActiveTrue();
    }

    @Override
    public long getTotalAvailableBooks() {
        return bookRepository.countAvailableBooks();
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .toList();
    }
}
