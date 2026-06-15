package com.platzi.play.dominio.exception;

public class MovieAlredyExistsException extends RuntimeException{
    public MovieAlredyExistsException(String movieTitle) {
        super("La pelicula " + movieTitle + " ya existe." );
    }
}
