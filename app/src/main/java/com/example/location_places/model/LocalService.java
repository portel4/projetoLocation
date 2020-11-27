package com.example.location_places.model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LocalService {
    @GET("/api/3eafeb9051014c4caba4afd6bcaece2b/local")
    Call<List<Local>> getAllLocais();
    @POST("/api/3eafeb9051014c4caba4afd6bcaece2b/local")
    Call<ResponseBody> salvarLocal(
            @Body
                    Local local);
    @PUT("/api/3eafeb9051014c4caba4afd6bcaece2b/local/{id}")
    Call<ResponseBody> alterarLocal(
            @Path("id") String id,
            @Body LocalPut localPut);

    @DELETE("/api/3eafeb9051014c4caba4afd6bcaece2b/local/{id}")
    Call<ResponseBody> deletarLocal(
            @Path("id") String id);
}