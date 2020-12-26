package com.example.movielistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.movielistapp.Adapter.MoviesAdapter;
import com.example.movielistapp.Model.Movie;
import com.example.movielistapp.Network.ApiInterface;
import com.example.movielistapp.Network.NetworkHelper;
import com.example.movielistapp.Network.onGetMovieCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public String API_KEY="52a18783ed514602a5facb15a0177e61";
    int page=1;
    private ApiInterface apiInterface;
    private MoviesAdapter adapter;
    private RecyclerView moviesList;
    private NetworkHelper moviesRepository;


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesRepository = NetworkHelper.getInstance();
        moviesList = findViewById(R.id.movies_list);
        moviesList.setLayoutManager(new LinearLayoutManager(this));


        moviesRepository.getMovies(new onGetMovieCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {

                adapter=new MoviesAdapter(movies);
                moviesList.setAdapter(adapter);


            }

            @Override
            public Void onError() {

                Toast.makeText(MainActivity.this, "internert not connected", Toast.LENGTH_SHORT).show();
                return null;
            }
        });

    }



}
