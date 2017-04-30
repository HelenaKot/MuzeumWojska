package com.fancytank.ognia.muzeumwojska.api;

import com.fancytank.ognia.muzeumwojska.api.dto.DisplayItemDto;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public final class MyFilterService {
    public static MyFilterService self;
    public static final String API_URL = "http://hackaton.wawcode.ognia.gpw.webd.pl";
    MyInterface myService;

    public interface MyInterface {
        @GET("/service/index.php?filters[0][field]=equals")
        Call<DisplayItemDto> getFilter(@Query("filters[0][field]") String nameFilter,
                                       @Query("filters[0][value]") String value);

        @GET("/service/index.php?mylife=false")
        Call<DisplayItemDto> getSth();

        @GET("/service?")
        Call<DisplayItemDto> getDetailsOf(@Query("id") String sth);
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
    }

    public void sendRequestForId(String id) {
        // Create a call instance for looking up Retrofit Records.
        Call<DisplayItemDto> call = myService.getDetailsOf("1");

        // Fetch and print a list of the Records to the library.
        call.enqueue(new Callback<DisplayItemDto>() {
            @Override
            public void onResponse(Call<DisplayItemDto> call, Response<DisplayItemDto> response) {
                DisplayItemDto record = response.body();
                System.out.println(record.toString());
            }

            @Override
            public void onFailure(Call<DisplayItemDto> call, Throwable t) {
                System.out.println("fail");
            }
        });
    }
}