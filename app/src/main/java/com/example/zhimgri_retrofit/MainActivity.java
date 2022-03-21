package com.example.zhimgri_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.widget.TextView;

import com.example.zhimgri_retrofit.interfaz.MyApiService;
import com.example.zhimgri_retrofit.modelo.Comentarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    TextView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.textviewdatos);
        getComentarios();
    }
    private void  getComentarios(){
        Retrofit retrofit =new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
        MyApiService myApiService =retrofit.create(MyApiService.class);

        Call<List<Comentarios>> call = myApiService.getComentarios();
        call.enqueue(new Callback<List<Comentarios>>() {
            @Override
            public void onResponse(Call<List<Comentarios>>call,Response<List<Comentarios>>response) {
                if (!response.isSuccessful()){
                    listView.setText("Codigo:"+response.code());
                    return;
                }
                List<Comentarios> datos=response.body();
                for (Comentarios d1:datos){
                    String mostrar ="";
                    mostrar +="postId : "+ d1.getPostId()+"\n";
                    mostrar +="id: "+ d1.getId()+"\n";
                    mostrar +="name: "+ d1.getName()+"\n";
                    mostrar +="email: "+ d1.getEmail()+"\n";
                    mostrar +="body: "+ d1.getBody()+"\n\n";
                    listView.append(mostrar);

                }
            }

            @Override
            public void onFailure(Call<List<Comentarios>>call, Throwable t) {
              listView.setText(t.getMessage());
            }
        });
    }
}