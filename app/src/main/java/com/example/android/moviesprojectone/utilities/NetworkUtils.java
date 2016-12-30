package com.example.android.moviesprojectone.utilities;

import android.net.Uri;
import android.util.Log;

import com.example.android.moviesprojectone.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by akmal.muqeeth on 12/27/16.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BASE_URL_POPULARITY_API = "https://api.themoviedb.org/3/movie/popular";
    private static final String BASE_URL_TOP_RATED_API = "https://api.themoviedb.org/3/movie/top_rated";

    private static final String API_KEY_PARAM = "api_key";
    private static final String API_KEY_VALUE = Constants.API_KEY_VALUE;

    /**
     * Builds the URL used to talk to the MovieDB API.
     *
     * @return The URL to use to get popular movies.
     */
    public static URL buildUrl(String sortBy) {

        String baseUrl = (sortBy == MainActivity.SORT_BY_TOP_RATED) ? BASE_URL_TOP_RATED_API : BASE_URL_POPULARITY_API;

        Uri builtUri = Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY_VALUE)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
