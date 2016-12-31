package com.example.android.moviesprojectone;

import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moviesprojectone.dto.MovieDTO;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;

public class MovieDetailsActivity extends AppCompatActivity  {

    TextView movieTitle;
    ImageView moviePoster;

    TextView movieOverview;
    TextView movieVoteAvg;
    TextView movieReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // retrieve movieDTO object from MainActivity
        MovieDTO movieDTO = (MovieDTO) getIntent().getSerializableExtra("movieDTOExtra");

        movieTitle = (TextView) findViewById(R.id.movie_detail_title);
        movieTitle.setText(movieDTO.getTitle());

        moviePoster = (ImageView) findViewById(R.id.movie_detail_poster);
        Picasso.with(this).load(movieDTO.getPosterPath()).into(moviePoster);

        movieOverview = (TextView) findViewById(R.id.movie_detail_overview);
        movieOverview.setText(movieDTO.getOverview());

        movieVoteAvg = (TextView) findViewById(R.id.movie_vote_avg);
        movieVoteAvg.setText("Rating: "+movieDTO.getVoteAverage().toString());

        movieReleaseDate = (TextView) findViewById(R.id.movie_release_date);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(movieDTO.getReleaseDate());
            SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, MMM d, ''yy");
            movieReleaseDate.setText("Released On: "+dateFormatter.format(date));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
