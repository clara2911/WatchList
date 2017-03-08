package com.example.clara.watchlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by clara on 24-2-2017.
 */

public class PosterAsyncTask extends AsyncTask<String, Integer, Bitmap> {
    Context context;
    MovieInfoActivity movieInfoAct;

    public PosterAsyncTask(MovieInfoActivity movieInfo) {
        this.movieInfoAct = movieInfo;
        this.context = this.movieInfoAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Getting movie info...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return HttpRequestHelper.downloadPosterFromServer(params);
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        this.movieInfoAct.setPoster(result);
    }



}
