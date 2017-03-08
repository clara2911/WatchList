package com.example.clara.watchlist;

import android.content.Context;
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

public class TrackAsyncTask extends AsyncTask<String, Integer, String> {
    Context context;
    MainActivity mainAct;

    public TrackAsyncTask(MainActivity main) {
        this.mainAct = main;
        this.context = this.mainAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "searching for tracks...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        ArrayList<String> results = new ArrayList<String>();
        String title = "";
        String year = "";
        String poster = "";
        JSONArray resultlist = null;
        JSONObject resultElem = null;
        try {
            JSONObject trackStreamobj = new JSONObject(result);
            resultlist = trackStreamobj.getJSONArray("Search");

            for(int i=0; i<resultlist.length(); i++) {
                resultElem = resultlist.getJSONObject(i);
                title = resultElem.getString("Title");
                year =  resultElem.getString("Year");
                poster = resultElem.getString("Poster");
                results.add(title + "(" + year + ")");
                results.add(poster);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.mainAct.trackStartIntent(results);
    }



}
