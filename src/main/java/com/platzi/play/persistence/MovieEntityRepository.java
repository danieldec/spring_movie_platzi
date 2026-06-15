package com.platzi.play.persistence;

import com.platzi.play.dominio.dto.MovieDto;
import com.platzi.play.dominio.dto.UpdateMovieDto;
import com.platzi.play.dominio.exception.MovieAlredyExistsException;
import com.platzi.play.dominio.repository.MovieRepository;
import com.platzi.play.persistence.crud.CrudMovieEntity;
import com.platzi.play.persistence.entity.MovieEntity;
import com.platzi.play.persistence.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class MovieEntityRepository implements MovieRepository {
    private final CrudMovieEntity crudMovieEntity;
    private final MovieMapper movieMapper;

    public MovieEntityRepository(CrudMovieEntity crudMovieEntity, MovieMapper movieMapper) {
        this.crudMovieEntity = crudMovieEntity;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieDto> getAll() {
        return this.movieMapper.toDto(crudMovieEntity.findAll());
    }

    @Override
    public MovieDto getById(long id) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        return this.movieMapper.toDo(movieEntity);
    }

    @Override
    public MovieDto save(MovieDto movieDto) {
        if (this.crudMovieEntity.findFirstByTitulo(movieDto.title())!=null){
            throw new MovieAlredyExistsException(movieDto.title());
        }
        var movieEntity = this.movieMapper.toEntity(movieDto);
        movieEntity.setEstado("D");
        this.crudMovieEntity.save(movieEntity);
        return this.movieMapper.toDo(movieEntity);
    }

    @Override
    public MovieDto update(UpdateMovieDto movieDto, long id) {
        var movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        if (movieEntity == null) {
            return null;
        }
        this.movieMapper.updateEntityFromDto(movieDto, movieEntity);
        var cme = this.crudMovieEntity.save(movieEntity);
        return movieMapper.toDo(cme);
    }

    @Override
    public void delete(long id) {
        var movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        if (movieEntity == null) {
            return;
        }
        this.crudMovieEntity.delete(movieEntity);
    }
}
