package com.example.clara.watchlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by clara on 24-2-2017.
 */

public class HttpRequestHelper {

    protected static synchronized String downloadFromServer(String... params) {
        String result = "";
        String chosentag = params[0];
        String stringUrl = "http://www.omdbapi.com/?s=" + chosentag;
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection connect;

        if(url != null) {
            try {
                connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("GET");
                Integer responseCode = connect.getResponseCode();
                if (responseCode >= 200 && responseCode < 300) {
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                    String line;
                    while((line = bReader.readLine()) != null) {
                        result += line;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d("JSONresult:",result);
        return result;

    }

    protected static synchronized Bitmap downloadPosterFromServer(String... params) {
        Bitmap bmp = null;
        URL url2 = null;
        try {
            url2 = new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            bmp = BitmapFactory.decodeStream(url2.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;

    }
}
