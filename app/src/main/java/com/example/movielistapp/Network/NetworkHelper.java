package com.example.movielistapp.Network;

import com.example.movielistapp.BuildConfig;
import com.example.movielistapp.Model.GenresResponse;
import com.example.movielistapp.Model.MovieResponcee;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {

    private static final String BASE_URL="https://api.themoviedb.org/3/";;
    private String API_KEY="52a18783ed514602a5facb15a0177e61";
    private static final String LANGUAGE = "en-US";
    int page=1;

    private static NetworkHelper repository;
    private ApiInterface api;

    public NetworkHelper(ApiInterface api) {
        this.api = api;
    }

   public static NetworkHelper getInstance(){
        if (repository==null){

//            HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
//
//
//            OkHttpClient okHttpClient=new OkHttpClient.Builder()
//                    .addInterceptor(loggingInterceptor)
//                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repository=new NetworkHelper(retrofit.create(ApiInterface.class));
        }

        return repository;
   }

    public void getMovies(final onGetMovieCallback callback){
        api.getPopularMovies(API_KEY,LANGUAGE,page).enqueue(new Callback<MovieResponcee>() {
            @Override
            public void onResponse(Call<MovieResponcee> call, Response<MovieResponcee> response) {
               if (response.isSuccessful()){
                   MovieResponcee movieResponcee=response.body();

                   if (movieResponcee != null && movieResponcee.getMovies() !=null){
                       callback.onSuccess(movieResponcee.getMovies());
                   }
                   else
                   {
                       callback.onError();
                   }
               }
               else {
                   callback.onError();
               }
            }

            @Override
            public void onFailure(Call<MovieResponcee> call, Throwable t) {
                callback.onError();
            }
        });


    }


}
