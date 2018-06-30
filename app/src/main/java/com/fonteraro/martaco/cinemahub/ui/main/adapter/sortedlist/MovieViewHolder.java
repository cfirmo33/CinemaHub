package com.fonteraro.martaco.cinemahub.ui.main.adapter.sortedlist;

import android.support.annotation.NonNull;

import com.fonteraro.martaco.cinemahub.data.model.db.Movie;
import com.fonteraro.martaco.cinemahub.databinding.MovieItemBinding;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

public class MovieViewHolder extends SortedListAdapter.ViewHolder<Movie> {

    private final MovieItemBinding mBinding;

    public MovieViewHolder(MovieItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    @Override
    protected void performBind(@NonNull Movie item) {
        mBinding.setMovie(item);
    }
}
