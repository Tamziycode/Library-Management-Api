package com.tamziy.Library.Management.Api.controller;

import com.tamziy.Library.Management.Api.payload.dto.GenreDTO;
import com.tamziy.Library.Management.Api.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    @PostMapping("/create")
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genre) {
        GenreDTO createdGenre=genreService.createGenre(genre);
        return ResponseEntity.ok(createdGenre);

    }

    @GetMapping()
    public ResponseEntity<List<GenreDTO>> getAllGenres(){
        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }
}
