package com.fancytank.ognia.muzeumwojska.api;

import android.content.Context;
import android.content.Intent;

import com.fancytank.ognia.muzeumwojska.DetailsView;
import com.fancytank.ognia.muzeumwojska.api.dto.DisplayItemDto;
import com.fancytank.ognia.muzeumwojska.api.model.Category;
import com.fancytank.ognia.muzeumwojska.api.model.DisplayParagraph;
import com.fancytank.ognia.muzeumwojska.api.model.DisplayUnit;
import com.fancytank.ognia.muzeumwojska.list.DisplayListAdapter;

import java.io.UnsupportedEncodingException;

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
    public static final String API_URL = "http://hackaton.wawcode.ognia.gpw.webd.pl";
    MyInterface myService;

    public MyFilterService() {
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
        myService = retrofit.create(MyInterface.class);
    }

    public interface MyInterface {
        @GET("/service/index.php?filters[0][field]=equals")
        Call<DisplayItemDto> getFilter(@Query("filters[0][field]") String nameFilter,
                                       @Query("filters[0][value]") String value);

        @GET("/service/index.php?mylife=false")
        Call<DisplayItemDto> getSth();

        @GET("/service?")
        Call<DisplayItemDto> getDetailsOf(@Query("id") String sth);
    }

    public void sendRequestForId(String id, final Context context) {
        // Create a call instance for looking up Retrofit Records.
        Call<DisplayItemDto> call = myService.getDetailsOf("1");

        // Fetch and print a list of the Records to the library.
        call.enqueue(new Callback<DisplayItemDto>() {
            @Override
            public void onResponse(Call<DisplayItemDto> call, Response<DisplayItemDto> response) {
                DisplayItemDto record = response.body();
                try {
                    openItem(serialize(record), context);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DisplayItemDto> call, Throwable t) {
                System.out.println("fail");
            }
        });
    }

    DisplayUnit serialize(DisplayItemDto dto) throws UnsupportedEncodingException {
        DisplayUnit output = new DisplayUnit(dto.id, dto.name, Category.TANK);
        output.coordinates = dto.gps_position;
        String desc =  new String(dto.description.getBytes(), "UTF-8");
        output.addDesc(new DisplayParagraph(dto.image_url, desc));
        return output;
    }


    void openItem(DisplayUnit unit, Context context) {
        Intent intent = new Intent(context, DetailsView.class);
        intent.putExtra(DisplayListAdapter.TAG, unit);
        context.startActivity(intent);
    }

}