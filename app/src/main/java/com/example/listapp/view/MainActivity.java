package com.example.listapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.listapp.R;
import com.example.listapp.RMApi;
import com.example.listapp.controller.MainController;
import com.example.listapp.model.RestRMResponse;
import com.example.listapp.model.RickandMorty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://rickandmortyapi.com/";

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                new GsonBuilder()
                        .setLenient()
                        .create(),
            getSharedPreferences("application_esiea", Context.MODE_PRIVATE)
        );
        controller.onStart();
    }


    public void showList(List<RickandMorty> rickandMortyList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(rickandMortyList);
        recyclerView.setAdapter(mAdapter);
    }



    public void showError() {
        Toast.makeText(this, "API Error", Toast.LENGTH_SHORT).show();
    }
}
