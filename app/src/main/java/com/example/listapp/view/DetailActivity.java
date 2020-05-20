package com.example.listapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.listapp.Injection;
import com.example.listapp.R;
import com.example.listapp.model.RickandMorty;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

private TextView txtDetail;
private TextView txtDetail2;
private TextView txtDetail3;
public ImageView imgDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.detail_txt);
        txtDetail2 = findViewById(R.id.detail2_txt);
        txtDetail3 = findViewById(R.id.detail3_txt);
        imgDetail = findViewById(R.id.image_Detail);

        Intent intent = getIntent();
        String rickandMortyJson = intent.getStringExtra("RMkey");
        RickandMorty rickandMorty = Injection.getGson().fromJson(rickandMortyJson, RickandMorty.class);
        ShowDetail(rickandMorty);
        ShowImage(rickandMorty);


    }

    private void ShowDetail(RickandMorty rickandMorty) {
        txtDetail.setText(rickandMorty.getName());
        txtDetail2.setText(rickandMorty.getStatus());
        txtDetail3.setText(rickandMorty.getSpecies());
    }

    private void ShowImage(RickandMorty rickandMorty){
       // String image = rickandMorty.getImage();
        Glide.with(this)
                .load("https://www.ecranlarge.com/media/cache/1600x1200/uploads/image/001/120/rick-et-morty-saison-4-photo-1120124.jpg")
                .into(imgDetail);
    }


}
