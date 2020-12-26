package com.example.movielistapp.Network;

import com.example.movielistapp.Model.GenresResponse;
import com.example.movielistapp.Model.Movie;
import com.example.movielistapp.Model.MovieResponcee;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

//gets now playing
    @GET("movie/now_playing")
    Call<MovieResponcee> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );


}
