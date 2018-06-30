package com.fonteraro.martaco.cinemahub.ui.main.adapter.sortedlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fonteraro.martaco.cinemahub.data.model.db.Movie;
import com.fonteraro.martaco.cinemahub.databinding.MovieItemBinding;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.Comparator;

public class SortedMoviesAdapter extends SortedListAdapter<Movie> {

    public SortedMoviesAdapter(@NonNull Context context, @NonNull Comparator<Movie> comparator) {
        super(context, Movie.class, comparator);
    }

    @NonNull
    @Override
    protected ViewHolder<? extends Movie> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final MovieItemBinding binding = MovieItemBinding.inflate(inflater, parent, false);
        return new MovieViewHolder(binding);
    }
}
