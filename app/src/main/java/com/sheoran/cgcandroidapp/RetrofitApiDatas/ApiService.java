package com.sheoran.cgcandroidapp.RetrofitApiDatas;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {
    @GET("posts")
    Call<ArrayList<ApiModel>>getpostlist();

    @GET("comments")
    Call<ArrayList<ApiModel>>getcommentlist();
    @GET("users")
    Call<ArrayList<ApiModel>>getuserlist();
}
