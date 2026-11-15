package com.tamziy.Library.Management.Api.service;

import com.tamziy.Library.Management.Api.payload.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    GenreDTO createGenre(GenreDTO genre);
    List<GenreDTO> getAllGenres();
}
