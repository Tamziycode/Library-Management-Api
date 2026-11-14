package com.tamziy.Library.Management.Api.service.implementation;

import com.tamziy.Library.Management.Api.modal.GenreEntity;
import com.tamziy.Library.Management.Api.repository.GenreRepository;
import com.tamziy.Library.Management.Api.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImplementation implements GenreService {

    @Autowired
    private final GenreRepository genreRepository;

    @Override
    public GenreEntity createGenre(GenreEntity genre){
        return genreRepository.save(genre);
    }
}
