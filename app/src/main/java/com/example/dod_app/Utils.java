package com.example.dod_app;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    public interface HttpRequestListener {
        void onTaskComplete(String result);
    }

    public static void sendHttpRequest(String urlString, String urlParameters, HttpRequestListener listener) {
        new HttpRequestTask(listener).execute(urlString, urlParameters);
    }

    private static class HttpRequestTask extends AsyncTask<String, Void, String> {
        private HttpRequestListener listener;

        public HttpRequestTask(HttpRequestListener listener) {
            this.listener = listener;
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];
            String urlParameters = params[1];
            HttpURLConnection conn = null;
            try {
                URL url = new URL(urlString);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                os.write(urlParameters.getBytes());
                os.flush();
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                    in.close();
                    return response.toString();
                } else {
                    return "HTTP error code: " + responseCode;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (listener != null) {
                listener.onTaskComplete(result);
            }
        }
    }
}