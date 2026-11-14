package com.tamziy.Library.Management.Api.controller;

import com.tamziy.Library.Management.Api.modal.GenreEntity;
import com.tamziy.Library.Management.Api.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    @PostMapping("/create")
    public ResponseEntity<GenreEntity> addGenre(@RequestBody GenreEntity genre) {
        GenreEntity createdGenre=genreService.createGenre(genre);
        return ResponseEntity.ok(createdGenre);

    }
}
