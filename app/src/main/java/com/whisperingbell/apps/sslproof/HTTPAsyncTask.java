package com.whisperingbell.apps.sslproof;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sheran on 12/31/15.
 */
public class HTTPAsyncTask extends AsyncTask<String ,Void, String> {
    private Exception exception;


    @Override
    protected String doInBackground(String... params) {
        String line = null;
        try {

            URL url = null;
            try {
                url = new URL("https://www.pcwebshop.co.uk/");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            URLConnection urlConnection = null;
            try {

                urlConnection = url.openConnection();
                InputStream in = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                while ((line = br.readLine()) != null) {
                    Log.d("Cretin", line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            this.exception = e;
            return null;
        }
        return line;
    }

}
