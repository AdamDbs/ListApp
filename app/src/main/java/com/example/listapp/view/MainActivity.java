package com.example.listapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.listapp.Injection;
import com.example.listapp.R;
import com.example.listapp.controller.MainController;
import com.example.listapp.model.RickandMorty;

import java.util.List;

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
                Injection.getGson(),
            Injection.getSharedPreference(getApplicationContext())
        );
        controller.onStart();
    }


    public void showList(List<RickandMorty> rickandMortyList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new ListAdapter(rickandMortyList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RickandMorty item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }



    public void showError() {
        Toast.makeText(this, "API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(RickandMorty rickandMorty) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("RMkey", Injection.getGson().toJson(rickandMorty));

        MainActivity.this.startActivity(myIntent);
    }
}
