package com.example.android.moviesprojectone.utilities;

import com.example.android.moviesprojectone.dto.MovieDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by akmal.muqeeth on 12/27/16.
 */

public class MovieDbJsonUtils {

    public static MovieDTO[] getMovieResultsFromMovieDbJSON(String moviesJSONStr) throws JSONException {

        final String MOVIES_RESULTS = "results";

        final String MOV_ORIGINAL_TITLE = "original_title";
        final String MOV_TITLE = "title";
        final String MOV_POSTER_PATH = "poster_path";
        final String MOV_OVERVIEW = "overview";

        JSONObject forecastJson = new JSONObject(moviesJSONStr);
        JSONArray moviesArray = forecastJson.getJSONArray(MOVIES_RESULTS);

        /* String array to hold each day's weather String */
        MovieDTO[] parsedMovieData = new MovieDTO[moviesArray.length()];

        for (int i = 0; i < moviesArray.length(); i++) {
            JSONObject movieJSON = moviesArray.getJSONObject(i);

            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setTitle(movieJSON.getString(MOV_TITLE));
            movieDTO.setOriginalTitle(movieJSON.getString(MOV_ORIGINAL_TITLE));
            movieDTO.setPosterPath(movieJSON.getString(MOV_POSTER_PATH));
            movieDTO.setOverview(movieJSON.getString(MOV_OVERVIEW));

            parsedMovieData[i] = movieDTO;
        }

        return parsedMovieData;
    }
}
