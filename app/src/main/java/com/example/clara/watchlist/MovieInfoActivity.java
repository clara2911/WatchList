package com.example.clara.watchlist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MovieInfoActivity extends AppCompatActivity {
    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("movieinfo", "Im in movie info!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        Bundle extras = getIntent().getExtras();
        String movieName = (String) extras.getString("movieName");
        String poster = (String) extras.getString("poster");
        textview = (TextView) findViewById(R.id.movieName);
        textview.setText(movieName);

        PosterAsyncTask asyncTask = new PosterAsyncTask(this);
        asyncTask.execute(poster);
    }

    public void setPoster(Bitmap bmp) {
        ImageView posterview = (ImageView) findViewById(R.id.posterview);
        posterview.setImageBitmap(bmp);
    }

    public void saveMovie(View view) {
        Intent movieIntent = new Intent(this, savedMoviesActivity.class);
        movieIntent.putExtra("movie", textview.getText());
        this.startActivity(movieIntent);
    }

    public void goToMyMovies(View view) {
        Intent movieIntent = new Intent(this, savedMoviesActivity.class);
        this.startActivity(movieIntent);
    }
}
