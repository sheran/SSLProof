package com.whisperingbell.apps.sslproof;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by sheran on 12/31/15.
 */
public class OKAsyncTask extends AsyncTask<String,Void, String> {

    public OkHttpClient getClient(){
        OkHttpClient client = new OkHttpClient();

        TrustManager[] v3 = new TrustManager[]{new X509TrustManager(){

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }};

        try{
            SSLContext v1 = SSLContext.getInstance("SSL");
            v1.init(null,v3,new SecureRandom());
            client.setSslSocketFactory(v1.getSocketFactory());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        client.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        return client;
    }

    OkHttpClient client = getClient();








    @Override
    protected String doInBackground(String... url) {
        Request request = new Request.Builder()
                .url(url[0])
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            Log.d("Proof",response.body().string());
            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
