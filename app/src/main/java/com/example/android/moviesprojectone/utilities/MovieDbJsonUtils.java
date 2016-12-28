package com.example.android.moviesprojectone.utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by akmal.muqeeth on 12/27/16.
 */

public class MovieDbJsonUtils {

    public static String[] getMovieResultsFromMovieDbJSON(String moviesJSONStr) throws JSONException {

        final String MOVIES_RESULTS = "results";

        final String MOV_ORIGINAL_TITLE = "original_title";

        JSONObject forecastJson = new JSONObject(moviesJSONStr);
        JSONArray moviesArray = forecastJson.getJSONArray(MOVIES_RESULTS);

        /* String array to hold each day's weather String */
        String[] parsedMovieData = new String[moviesArray.length()];

        for (int i = 0; i < moviesArray.length(); i++) {
            JSONObject movieJSON = moviesArray.getJSONObject(i);
            String movieTitle = movieJSON.getString(MOV_ORIGINAL_TITLE);
            parsedMovieData[i] = movieTitle;
        }

        return parsedMovieData;
    }
}
