package Part6.Movie;

import java.util.*;

class Movie implements IMovie, Comparable<IMovie> {
    private String movieId;
    private String title;
    private String director;
    private int year;
    private double rating;
    private String genre;
    private int duration;

    public Movie(String movieId,String title, String director, int year, double rating, String genre, int duration) {
        this.movieId = movieId;
        this.title=title;
        this.director=director;
        this.year=year;
        this.rating=rating;
        this.genre=genre;
        this.duration=duration;

    }

    @Override
    public String getMovieId() {
        return movieId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDirector() {
        return director;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int compareTo(IMovie other) {
        int cmp = Double.compare(other.getRating(), this.getRating()); // giảm dần
        if (cmp != 0) return cmp;

        return Integer.compare(other.getYear(), this.getYear()); // giảm dần
    }

    @Override
    public String toString() {
        return String.format("Movie[id='%s', title='%s', director='%s', year=%d, rating=%.1f, genre='%s', duration=%dmin]",
                movieId,title , director, year, rating, genre, duration);
    }
}

