package com.example.movielistapp.Network;

import com.example.movielistapp.Model.Genre;

import java.util.List;

public interface OnGetGenresCallback {

    void onSuccess(List<Genre> genres);

    void onError();
}
