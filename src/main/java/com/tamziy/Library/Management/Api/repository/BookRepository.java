package com.tamziy.Library.Management.Api.repository;

import com.tamziy.Library.Management.Api.modal.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    @Query("""
    SELECT b FROM BookEntity b
    WHERE (
        :searchTerm IS NULL
        OR LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
        OR LOWER(b.author) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
        OR LOWER(b.isbn) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
    )
    AND (:genreId IS NULL OR b.genre.id = :genreId)
    AND (:availableOnly = false OR b.availableCopies > 0)
    AND b.active = true
    """)
    Page<BookEntity> searchBooksWithFilters(@Param("searchTerm") String searchTerm, @Param("genreId") Long genreId, @Param("availableOnly") Boolean avialableOnly, Pageable pageable);

    long countByActiveTrue();

    @Query("SELECT count(b) from Book b where b.availableCopies > 0 and b.active = true")
    long countAvailableBooks();


}
