package com.example.movielistapp.Network;

import com.example.movielistapp.Model.Movie;

import java.util.List;

public interface onGetMovieCallback {

    void onSuccess(List<Movie> movies);

    Void onError();
}
