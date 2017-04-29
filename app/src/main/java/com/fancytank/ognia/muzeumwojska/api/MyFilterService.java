package com.fancytank.ognia.muzeumwojska.api;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public final class MyFilterService {
    public static final String API_URL = "http://hackaton.wawcode.ognia.gpw.webd.pl";

    public static class Record {
        public final String[] post;
        public final String[] get;

        public Record(String[] post, String[] get) {
            this.post = post;
            this.get = get;
        }
    }

    public interface MyInterface {
        @GET("/service/index.php?filters[0][field]=equals")
        Call<Record> GetSth(@Query("filters[0][field]") String nameFilter,
                            @Query("filters[0][value]") String value);
    }

    public static void main() throws IOException {
        //Logging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);


        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        MyInterface myService = retrofit.create(MyInterface.class);

        // Create a call instance for looking up Retrofit Records.
        Call<Record> call = myService.GetSth("a", "brykiet");

        // Fetch and print a list of the Records to the library.
        call.enqueue(new Callback<Record>() {
            @Override
            public void onResponse(Call<Record> call, Response<Record> response) {
                Record record = response.body();
                System.out.println(record.toString());
//                System.out.println(record.description);
            }

            @Override
            public void onFailure(Call<Record> call, Throwable t) {
                System.out.println("fail");
            }
        });

    }
}