package com.fonteraro.martaco.cinemahub.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.fonteraro.martaco.cinemahub.data.local.db.dao.MovieDao;
import com.fonteraro.martaco.cinemahub.data.local.prefs.AppPreferencesHelper;
import com.fonteraro.martaco.cinemahub.data.model.db.Movie;
import com.fonteraro.martaco.cinemahub.data.remote.ApiInterface;
import com.fonteraro.martaco.cinemahub.data.remote.response.MoviesData;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fonteraro.martaco.cinemahub.utils.AppConstants.REFRESH_TIMEOUT_IN_MILLISECONDS;

@Singleton
public class DataRepository {

    private ApiInterface apiInterface;

    private MovieDao movieDao;

    private AppPreferencesHelper appPreferencesHelper;

    private Executor executor;

    @Inject
    public DataRepository(ApiInterface apiInterface, MovieDao movieDao,
                          AppPreferencesHelper appPreferencesHelper, Executor executor) {
        this.apiInterface = apiInterface;
        this.movieDao = movieDao;
        this.appPreferencesHelper = appPreferencesHelper;
        this.executor = executor;
    }

    public LiveData<List<Movie>> getMovies() {
        refreshMovies();
        return movieDao.queryMovies();
    }

    private void refreshMovies() {
        new Thread(() -> {
            // Check if movies were fetched recently
            if (shouldRefreshList()) {
                apiInterface.getMovies().enqueue(new Callback<MoviesData>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesData> call, @NonNull Response<MoviesData> response) {
                        if (response.body() != null) {
                            appPreferencesHelper.setLastRefreshTimestamp(new Date().getTime());

                            executor.execute(() -> movieDao.insertAll(response.body().getData()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MoviesData> call, @NonNull Throwable t) {
                    }
                });
            }
        }).start();
    }

    private boolean shouldRefreshList() {
        long lastRefreshTimestamp = appPreferencesHelper.getLastRefreshTimestamp();
        return new Date().getTime() - lastRefreshTimestamp >= REFRESH_TIMEOUT_IN_MILLISECONDS;
    }
}
