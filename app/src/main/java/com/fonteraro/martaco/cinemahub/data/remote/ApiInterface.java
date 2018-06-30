package com.fonteraro.martaco.cinemahub.data.remote;

import com.fonteraro.martaco.cinemahub.BuildConfig;
import com.fonteraro.martaco.cinemahub.data.remote.response.MoviesData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET(BuildConfig.MOVIES)
    Call<MoviesData> getMovies();
}
