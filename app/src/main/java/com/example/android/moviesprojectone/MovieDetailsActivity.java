package com.example.android.moviesprojectone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moviesprojectone.dto.MovieDTO;

public class MovieDetailsActivity extends AppCompatActivity  {

    TextView movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieTitle = (TextView) findViewById(R.id.movie_detail_title);

        // retrieve movieDTO object from MainActivity
        MovieDTO movieDTO = (MovieDTO) getIntent().getSerializableExtra("movieDTOExtra");

        movieTitle.setText(movieDTO.getTitle());

    }

}
