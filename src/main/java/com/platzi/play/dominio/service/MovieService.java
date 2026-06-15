package com.platzi.play.dominio.service;

import com.platzi.play.dominio.dto.MovieDto;
import com.platzi.play.dominio.dto.UpdateMovieDto;
import com.platzi.play.dominio.repository.MovieRepository;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService( MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }
    @Tool("Busca todas las peliculas que existan dentro de la plataforma")
    public List<MovieDto> getAll()
    {
        return  this.movieRepository.getAll();
    }

    public MovieDto getById(long id){
        return this.movieRepository.getById(id);
    }
    public MovieDto save(MovieDto dto){
        return this.movieRepository.save(dto);
    }
    public MovieDto update(long id, UpdateMovieDto updateMovieDto){
        return this.movieRepository.update(updateMovieDto,id);
    }
    public void delete(long id){
        this.movieRepository.delete(id);
    }
}
