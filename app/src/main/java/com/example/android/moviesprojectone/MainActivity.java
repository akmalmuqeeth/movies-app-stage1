package com.example.android.moviesprojectone;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.moviesprojectone.utilities.MovieDbJsonUtils;
import com.example.android.moviesprojectone.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView moviesDisplay;
    TextView errorDisplay;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesDisplay = (TextView) findViewById(R.id.movies_display);
        errorDisplay = (TextView) findViewById(R.id.error_display);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        loadMovies();

    }

    public void loadMovies(){
        new GetMoviesTask().execute();
    }

    public class GetMoviesTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {

            URL movieDbUrl = NetworkUtils.buildUrl();

            try {
                String movieDbResponseJson = NetworkUtils
                        .getResponseFromHttpUrl(movieDbUrl);

                String[] moviesData = MovieDbJsonUtils.getMovieResultsFromMovieDbJSON(movieDbResponseJson);

                return moviesData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] moviesData) {
            progressBar.setVisibility(View.INVISIBLE);
            if(moviesData != null) {
                showMoviesDisplay();
                for (String movie : moviesData) {
                    moviesDisplay.append((movie) + "\n\n\n");
                }
            } else {
                showErrorDisplay();
            }
        }
    }

    public void showErrorDisplay(){
        moviesDisplay.setVisibility(View.INVISIBLE);
        errorDisplay.setVisibility(View.VISIBLE);
    }

    public void showMoviesDisplay(){
        moviesDisplay.setVisibility(View.VISIBLE);
        errorDisplay.setVisibility(View.INVISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.movies, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            moviesDisplay.setText("");
            loadMovies();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
