package com.platzi.play.dominio.repository;

import com.platzi.play.dominio.dto.MovieDto;
import com.platzi.play.dominio.dto.UpdateMovieDto;

import java.util.List;

public interface MovieRepository {
    List<MovieDto> getAll();
    MovieDto getById(long id);
    MovieDto save(MovieDto movieDto);
    MovieDto update(UpdateMovieDto movieDto,long id);
    void delete(long id);
}
