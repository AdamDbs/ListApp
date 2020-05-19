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

    private static final String BASE_URL = "https://rickandmortyapi.com/";

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("application_esiea", Context.MODE_PRIVATE);

        gson = new GsonBuilder()
                .setLenient()
                .create();



        List<RickandMorty> rickandMortyList = getDataFromCache();

        if(rickandMortyList != null){
            showList(rickandMortyList);
        }else {
            makeApiCall();
        }

    }
    private List<RickandMorty> getDataFromCache(){
        String jsonRickandMorty = sharedPreferences.getString("jsonrickandMortyList", null);

        if(jsonRickandMorty == null){
            return null;
        }else{
            Type listType = new TypeToken<List<RickandMorty>>(){}.getType();
            return gson.fromJson(jsonRickandMorty, listType);
        }

    }

    private void showList(List<RickandMorty> rickandMortyList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(rickandMortyList);
        recyclerView.setAdapter(mAdapter);
    }


    private void makeApiCall(){



            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            RMApi rmapi = retrofit.create(RMApi.class);

            Call<RestRMResponse> call = rmapi.getRMResponse();
            call.enqueue(new Callback<RestRMResponse>() {
                @Override
                public void onResponse(Call<RestRMResponse> call, Response<RestRMResponse> response) {
                    if(response.isSuccessful() && response.body() != null){
                            List<RickandMorty> rickandMortyList = response.body().getResults();
                            saveList(rickandMortyList);
                            showList(rickandMortyList);
                    }else{
                        showError();
                    }
                }

                @Override
                public void onFailure(Call<RestRMResponse> call, Throwable t) {
                    showError();
                }
            });


    }

    private void saveList(List<RickandMorty> rickandMortyList) {
        String jsonString = gson.toJson(rickandMortyList);
        sharedPreferences
                .edit()
                .putString("jsonrickandMortyList", jsonString)
                .apply();

        Toast.makeText(this, "List Saved", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(this, "API Error", Toast.LENGTH_SHORT).show();
    }
}
