package com.example.clara.watchlist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    TextView tvResult;
    ListView lvItems;
    ArrayList<String> trackArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        tvResult = (TextView) findViewById(R.id.tvFound);
        lvItems = (ListView) findViewById(R.id.listViewID);

        Bundle extras = getIntent().getExtras();
        trackArray = (ArrayList<String>) extras.getSerializable("data");

        makeTrackAdapter();
        listenToClicks();
    }


    public void makeTrackAdapter() {
        ArrayList<String> titleYearArray = new ArrayList<String>();
        for(int i=0; i<trackArray.size(); i+=2) {
            titleYearArray.add(trackArray.get(i));
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, titleYearArray);
        lvItems = (ListView) findViewById(R.id.listViewID);
        assert lvItems != null;
        lvItems.setAdapter(arrayAdapter);
    }

    public void listenToClicks() {
        lvItems.setClickable(true);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lvItems.getItemAtPosition(position);
                goToMovieInfo(o,position);
                Log.d("in listen", "going to movie info");
            }

        });
    }




    public void goToMyMovies(View view) {
        Intent movieIntent = new Intent(this, savedMoviesActivity.class);
        this.startActivity(movieIntent);
    }

    public void goToMovieInfo(Object o, int position) {
        String movieName = o.toString();
        Intent movieIntent = new Intent(this, MovieInfoActivity.class);
        movieIntent.putExtra("movieName", movieName);
        movieIntent.putExtra("poster",trackArray.get(position*2+1));
        Log.d("in goto","Going to movie info activity now");
        this.startActivity(movieIntent);
    }


}
