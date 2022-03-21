package com.example.zhimgri_retrofit.interfaz;

import com.example.zhimgri_retrofit.modelo.Comentarios;

import java.util.List;
import  retrofit2.Call;
import retrofit2.http.GET;


public interface MyApiService {
    @GET("comments")
    Call<List <Comentarios>> getComentarios();
}
