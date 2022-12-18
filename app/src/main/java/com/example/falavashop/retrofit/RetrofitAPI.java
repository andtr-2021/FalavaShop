package com.example.falavashop.retrofit;

import com.example.falavashop.Model.ResultsSearch;

import java.util.HashMap;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface RetrofitAPI {


    @POST("api/v1/search_product")
    Observable<ResultsSearch> getListSearch(
            @Body HashMap<String, String> Data
    );


}

