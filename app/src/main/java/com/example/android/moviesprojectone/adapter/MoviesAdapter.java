package com.example.android.moviesprojectone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.moviesprojectone.R;
import com.example.android.moviesprojectone.dto.MovieDTO;

/**
 * Created by akmal.muqeeth on 12/28/16.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private MovieDTO[] moviesData;

    public MoviesAdapter(){
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movies_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MovieDTO movie = moviesData[position];
        holder.movieTitleTextView.setText(movie.getTitle());
        holder.movieOverviewTextView.setText(movie.getOverview());
    }

    @Override
    public int getItemCount() {
        if (null == moviesData) return 0;
        return moviesData.length;
    }

    public void setMoviesData(MovieDTO[] data) {
        moviesData = data;
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        public final TextView movieTitleTextView;
        public final TextView movieOverviewTextView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            movieTitleTextView = (TextView) itemView.findViewById(R.id.movie_title);
            movieOverviewTextView = (TextView) itemView.findViewById(R.id.movie_overview);

        }
    }
}
