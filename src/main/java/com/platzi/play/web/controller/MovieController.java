package com.platzi.play.web.controller;

import com.platzi.play.dominio.dto.MovieDto;
import com.platzi.play.dominio.dto.SuggestRequestDto;
import com.platzi.play.dominio.dto.UpdateMovieDto;
import com.platzi.play.dominio.service.MovieService;
import com.platzi.play.dominio.service.PlatziPlayAiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies",description = "Operations about movies of PlatziPlay")
public class MovieController {

    private final MovieService movieService;
    private final PlatziPlayAiService aiService;

    public MovieController(MovieService movieService, PlatziPlayAiService aiService) {
        this.movieService = movieService;
        this.aiService = aiService;
    }

    @GetMapping()
    public ResponseEntity<List<MovieDto>> getAll() {
        var peliculas = (List<MovieDto>) this.movieService.getAll();
        if (peliculas == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a movie by Id"
            ,description = "Return the movie by id"
            ,responses = {
                    @ApiResponse(responseCode = "200",description = "Find movie")
                    ,@ApiResponse(responseCode = "400",description = "not found movie",content = @Content)
            }
    )
    public ResponseEntity<MovieDto> getByID(@PathVariable @Parameter(description = "Id of Movie",example = "9") long id) {
        var movieDto = this.movieService.getById(id);
        if (movieDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping()
    public ResponseEntity<MovieDto> add(@RequestBody MovieDto movieDto) {
        var movieDtoCreado = this.movieService.save(movieDto);
        if (movieDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDtoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(
            @PathVariable long id
            , @RequestBody @Valid UpdateMovieDto updateMovieDto) {
        var movieDtoActualizado = this.movieService.update(id, updateMovieDto);
        if (movieDtoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDtoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id) {
        this.movieService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("suggest")
    public ResponseEntity<String> generateMovieSuggestion(@RequestBody SuggestRequestDto suggestRequestDto) {
        return ResponseEntity.ok(this.aiService.generateMovieSuggestion(suggestRequestDto.userPreference()));
    }
}
