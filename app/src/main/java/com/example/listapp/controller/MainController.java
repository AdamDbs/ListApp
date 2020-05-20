package com.example.listapp.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.listapp.Injection;
import com.example.listapp.RMApi;
import com.example.listapp.model.RestRMResponse;
import com.example.listapp.model.RickandMorty;
import com.example.listapp.view.MainActivity;
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

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;


    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){


        List<RickandMorty> rickandMortyList = getDataFromCache();

        if(rickandMortyList != null){
            view.showList(rickandMortyList);
        }else {
            makeApiCall();
        }

    }

    private void makeApiCall(){


        Call<RestRMResponse> call = Injection.getRMApi().getRMResponse();
        call.enqueue(new Callback<RestRMResponse>() {
            @Override
            public void onResponse(Call<RestRMResponse> call, Response<RestRMResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<RickandMorty> rickandMortyList = response.body().getResults();
                    saveList(rickandMortyList);
                    view.showList(rickandMortyList);
                }else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestRMResponse> call, Throwable t) {
                view.showError();
            }
        });


    }

    private void saveList(List<RickandMorty> rickandMortyList) {
        String jsonString = gson.toJson(rickandMortyList);
        sharedPreferences
                .edit()
                .putString("jsonrickandMortyList", jsonString)
                .apply();

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

    public void onItemClick(RickandMorty rickandMorty){
        view.navigateToDetails(rickandMorty);
    }
}
