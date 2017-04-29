package com.fancytank.ognia.muzeumwojska.api;

import com.fancytank.ognia.muzeumwojska.api.dto.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/top_rated")
    Call<Model> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Model> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
