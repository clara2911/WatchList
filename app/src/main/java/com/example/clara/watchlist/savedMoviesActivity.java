package com.example.clara.watchlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class savedMoviesActivity extends AppCompatActivity {
    ListView lvItems;
    ArrayList<String> savedMoviesArray = new ArrayList<String>();
    SharedPreferences prefs;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_movies);

        loadFromSharedPrefs();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            addNewMovie(extras);
        }
        makeSavedMoviesAdapter();
        listenToLongClicks();

        saveToSharedPrefs();

    }

    public void makeSavedMoviesAdapter() {
        arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, savedMoviesArray);
        lvItems = (ListView) findViewById(R.id.savedMoviesList);
        assert lvItems != null;
        lvItems.setAdapter(arrayAdapter);
    }

    public void addNewMovie(Bundle extras) {
        String newMovie = (String) extras.getString("movie");
        savedMoviesArray.add(newMovie);
    }

    public void loadFromSharedPrefs() {
        prefs = this.getSharedPreferences("settings", this.MODE_PRIVATE);
        int size = prefs.getInt("sizeList",0);
        for(int i=0; i<size; i++) {
            savedMoviesArray.add(prefs.getString("movie_"+i,null));
        }
    }

    public void saveToSharedPrefs() {
        prefs = this.getSharedPreferences("settings",this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("sizeList", savedMoviesArray.size());
        for(int i=0; i<savedMoviesArray.size();i++) {
            editor.remove("movie_"+i);
            editor.putString("movie_"+i,savedMoviesArray.get(i));
        }
        editor.commit();
    }


    public void goToMainActivity(View view) {
        Intent movieIntent = new Intent(this, MainActivity.class);
        this.startActivity(movieIntent);
    }

    public void clearList(View view) {
        savedMoviesArray.clear();
        prefs = this.getSharedPreferences("settings",this.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        Intent movieIntent = new Intent(this, savedMoviesActivity.class);
        this.startActivity(movieIntent);
    }

    public void listenToLongClicks() {
        lvItems.setClickable(true);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lvItems.getItemAtPosition(position);
                deleteItem(position);
                return true;
            }
        });
    }

    public void deleteItem(int pos) {
        Object itemToDelete = savedMoviesArray.get(pos);
        savedMoviesArray.remove(itemToDelete);
        arrayAdapter.notifyDataSetChanged();
    }
}
