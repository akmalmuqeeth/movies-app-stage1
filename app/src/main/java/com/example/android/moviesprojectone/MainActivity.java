package com.example.android.moviesprojectone;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moviesprojectone.adapter.MoviesAdapter;
import com.example.android.moviesprojectone.dto.MovieDTO;
import com.example.android.moviesprojectone.utilities.MovieDbJsonUtils;
import com.example.android.moviesprojectone.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;

    private static final Integer GRID_SPAN_COUNT = 2;


    TextView errorDisplay;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);
       // LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, GRID_SPAN_COUNT);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mMoviesAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mMoviesAdapter);

        errorDisplay = (TextView) findViewById(R.id.error_display);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);


        loadMovies();

    }

    public void loadMovies(){
        new GetMoviesTask().execute();
    }

    @Override
    public void onClick(MovieDTO movieDTO) {

        Intent startChildActivityIntent = new Intent(MainActivity.this, MovieDetailsActivity.class);
        startChildActivityIntent.putExtra("movieDTOExtra", movieDTO);

        startActivity(startChildActivityIntent);
    }

    public class GetMoviesTask extends AsyncTask<String, Void, MovieDTO[]> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected MovieDTO[] doInBackground(String... params) {

            URL movieDbUrl = NetworkUtils.buildUrl();

            try {
                String movieDbResponseJson = NetworkUtils
                        .getResponseFromHttpUrl(movieDbUrl);

                MovieDTO[] moviesData = MovieDbJsonUtils.getMovieResultsFromMovieDbJSON(movieDbResponseJson);

                return moviesData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(MovieDTO[] moviesData) {
            progressBar.setVisibility(View.INVISIBLE);
            if(moviesData != null) {
                showMoviesDisplay();
                mMoviesAdapter.setMoviesData(moviesData);
            } else {
                showErrorDisplay();
            }
        }
    }

    public void showErrorDisplay(){
        mRecyclerView.setVisibility(View.INVISIBLE);
        errorDisplay.setVisibility(View.VISIBLE);
    }

    public void showMoviesDisplay(){
        mRecyclerView.setVisibility(View.VISIBLE);
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
            loadMovies();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
