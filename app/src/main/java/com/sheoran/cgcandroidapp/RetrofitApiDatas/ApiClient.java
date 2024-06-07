package com.sheoran.cgcandroidapp.RetrofitApiDatas;

import retrofit2.Retrofit;

public class ApiClient {
    static  String BASE_URL = "https://jsonplaceholder.typicode.com/";
    static Retrofit getRetrofit(){
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build();
        return retrofit;

    }

}
//    OkHttpClient client = new OkHttpClient();
//
//    Request request = new Request.Builder()
//            .url("https://sephora.p.rapidapi.com/stores/list?latitude=33.9733&longitude=-118.2487&radius=25")
//            .get()
//            .addHeader("X-RapidAPI-Key", "c949d1212amsh0facb9790867f89p12f241jsn7f113df0086a")
//            .addHeader("X-RapidAPI-Host", "sephora.p.rapidapi.com")
//            .build();
//
//    Response response;
//
//    {
//        try {
//            response = client.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
