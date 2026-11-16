package com.tamziy.Library.Management.Api.repository;

import com.tamziy.Library.Management.Api.modal.GenreEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    List<GenreEntity> findByActiveTrueOrderByDisplayOrderAsc();

    List<GenreEntity> findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

    List<GenreEntity> findByParentGenreIdAndActiveTrueOrderByDisplayOrderAsc(Long parentGenreId);

    long countByActiveTrue();

    //@Query("SELECT count(b) from book b where b.genre.id=:genreId")
    //long countBooksByGenre(@Param("genreId") Long genreId);
}
