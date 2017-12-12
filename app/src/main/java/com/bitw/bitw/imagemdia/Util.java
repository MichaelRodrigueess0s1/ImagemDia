package com.bitw.bitw.imagemdia;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Michael on 06/12/2017.
 */

public final class Util {

    private Util() { }

    public static String readFileFromAssets(Context context, String filename) {
        String result;

        try {
            String line;
            StringBuilder builder = new StringBuilder();
            InputStream json = context.getAssets().open(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(json, "UTF-8"));

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            reader.close();

            result = builder.toString();
        } catch (Exception e) {
            result = null;
        }

        return result;
    }

    public static String readStringFromUrl(String source) {
        String result = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(source);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setConnectTimeout(20 * 1000);
            conn.setReadTimeout(20 * 1000);

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                result = readStream(conn.getInputStream());
            }
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return result;
    }


    private static String readStream(InputStream inputStream) throws IOException {
        String line;
        StringBuilder builder = new StringBuilder();
        InputStream in = new BufferedInputStream(inputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        return  builder.toString();
    }



}