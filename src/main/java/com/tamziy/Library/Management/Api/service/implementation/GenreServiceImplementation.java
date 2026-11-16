package com.tamziy.Library.Management.Api.service.implementation;

import com.tamziy.Library.Management.Api.Exception.GenreException;
import com.tamziy.Library.Management.Api.mapper.GenreMapper;
import com.tamziy.Library.Management.Api.modal.GenreEntity;
import com.tamziy.Library.Management.Api.payload.dto.GenreDTO;
import com.tamziy.Library.Management.Api.repository.GenreRepository;
import com.tamziy.Library.Management.Api.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImplementation implements GenreService {

    @Autowired
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO){

        GenreEntity genre = genreMapper.toEntity(genreDTO);
        GenreEntity savedGenre = genreRepository.save(genre);


        GenreDTO dto = genreMapper.toDTO(savedGenre);


        return dto;
    }

    @Override
    public List<GenreDTO> getAllGenres(){
        return genreRepository.findAll().stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDTO getGenreById(Long genreId) throws GenreException {
        GenreEntity genre = genreRepository.findById(genreId).orElseThrow(() -> new GenreException("genre not found"));
        return genreMapper.toDTO(genre);
    }

    @Override
    public GenreDTO updateGenre(Long genreId, GenreDTO genreDTO) throws GenreException {
        GenreEntity existingGenre =genreRepository.findById(genreId).orElseThrow(
                () -> new GenreException("genre not found"));

        genreMapper.updateEntityFromDTO(genreDTO, existingGenre);

        GenreEntity updatedGenre = genreRepository.save(existingGenre);

        return genreMapper.toDTO(updatedGenre);
    }

    @Override
    public void deleteGenre(Long genreId) throws GenreException {

        GenreEntity existingGenre =genreRepository.findById(genreId).orElseThrow(
                () -> new GenreException("genre not found"));
        existingGenre.setActive(false);
        genreRepository.save(existingGenre);

    }

    @Override
    public void hardDeleteGenre(Long genreId) throws GenreException {

        GenreEntity existingGenre =genreRepository.findById(genreId).orElseThrow(
                () -> new GenreException("genre not found"));
        genreRepository.delete(existingGenre);


    }

    @Override
    public List<GenreDTO> getAllActiveGenresWithSubgenres() {
        List<GenreEntity> topLevelGenre = genreRepository.findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

        return genreMapper.toDtoList(topLevelGenre);

    }

    @Override
    public List<GenreDTO> getTopLevelGenres() {
        List<GenreEntity> topLevelGenre = genreRepository.findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();

        return genreMapper.toDtoList(topLevelGenre);
    }

    @Override
    public long getTotalActiveGenres() {


        return genreRepository.countByActiveTrue();
    }

    @Override
    public long getBookCountByGenre(Long genreId) {
        return 0;
    }
}
