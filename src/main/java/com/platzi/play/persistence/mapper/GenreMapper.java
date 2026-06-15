package com.platzi.play.persistence.mapper;

import com.platzi.play.dominio.Genre;
import org.mapstruct.Named;

@Named("stringToGenre")
public class GenreMapper {
    public static Genre stringToGenre(String genero) {
        if (genero == null) {
            return null;
        }
        return switch (genero.toUpperCase()) {
            case "ACCION" -> Genre.ACTION;
            case "COMEDIA" -> Genre.COMEDY;
            case "DRAMA" -> Genre.DRAMA;
            case "ANIMADA" -> Genre.ANIMATED;
            case "TERROR" -> Genre.HORROR;
            case "CIENCIA_FICCION" -> Genre.SCI_FI;
            default -> null;
        };
    }
    @Named("genreToString")
    public static String genreToString(Genre genre) {
        if (genre == null) {
            return null;
        }
        return switch (genre) {
            case Genre.ACTION -> "ACCION";
            case Genre.COMEDY -> "COMEDIA";
            case Genre.DRAMA -> "DRAMA";
            case Genre.ANIMATED -> "ANIMADA";
            case Genre.HORROR -> "TERROR";
            case Genre.SCI_FI -> "CIENCIA_FICCION";
            default -> null;
        };
    }
}
