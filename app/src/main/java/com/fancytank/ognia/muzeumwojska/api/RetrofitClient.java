package com.fancytank.ognia.muzeumwojska.api;

import android.util.Log;

import com.fancytank.ognia.muzeumwojska.api.dto.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    public static final String BASE_URL = "\"https://api.github.com/";
    private static Retrofit retrofit = null;
    // TODO API KEY ?
    private static String API_KEY = "";
    private final String TAG = "LOG";

    public RetrofitClient() {
        ApiInterface apiService = getClient().create(ApiInterface.class);

        Call<Model> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                List<Model> movies = response.body().getResults();
                Log.d(TAG, "Response: " + movies.size());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public void get(String uri) {

    }

}
