package com.fonteraro.martaco.cinemahub.ui.main;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.fonteraro.martaco.cinemahub.data.DataRepository;
import com.fonteraro.martaco.cinemahub.ui.base.BaseViewModel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    @Inject
    public MainViewModel(DataRepository dataRepository) {
        super(dataRepository);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .noPlaceholder()
                .noFade()
                .into(view);
    }
}
