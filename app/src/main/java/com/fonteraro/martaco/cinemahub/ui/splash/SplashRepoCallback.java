package com.fonteraro.martaco.cinemahub.ui.splash;

import com.fonteraro.martaco.cinemahub.data.model.db.Movie;

import java.util.List;

public interface SplashRepoCallback {
    void onDataRequestSucceed(List<Movie> movies);
    void onDataRequestFail();
}
