package com.example.clara.watchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText inputTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTrack = (EditText) findViewById(R.id.inputTrack);
        assert inputTrack != null;
    }

    public void trackSearch(View view) {
        String trackSearch = inputTrack.getText().toString();
        TrackAsyncTask asyncTask = new TrackAsyncTask(this);
        asyncTask.execute(trackSearch);
        inputTrack.getText().clear();
    }

    public void trackStartIntent(ArrayList<String> trackData) {
        Intent dataIntent = new Intent(this, DataActivity.class);
        dataIntent.putExtra("data", trackData);
        this.startActivity(dataIntent);
    }

    public void goToMyMovies(View view) {
        Intent movieIntent = new Intent(this, savedMoviesActivity.class);
        this.startActivity(movieIntent);
    }
}
