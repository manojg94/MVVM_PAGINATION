package com.manoj.movie.repos;


import android.media.Image;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import com.manoj.movie.api.RetrofitClient;
import com.manoj.movie.api.pojo.movielist;
import com.manoj.movie.api.pojo.movielist1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemDataSource extends PageKeyedDataSource<Integer, movielist1> {

    public static final int PAGE_SIZE = 5;
    private static final int FIRST_PAGE = 1;
    private final String API_KEY = "5a9e972c916d99006f4d6ec3c46829ce";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, movielist1> callback) {

        RetrofitClient.getInstance()
                .getApi().getmovies(API_KEY, FIRST_PAGE)
                .enqueue(new Callback<movielist>() {
                    @Override
                    public void onResponse(Call<movielist> call, Response<movielist> response) {
                        if (response.isSuccessful()) {
                            Log.d("results", response.body().getResults().get(0).getTitle());
                            Log.d("results", String.valueOf(response.body().getResults().size()));

                            callback.onResult(response.body().getResults(), null, FIRST_PAGE + 1);

                        }
                    }

                    @Override
                    public void onFailure(Call<movielist> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, movielist1> callback) {
        RetrofitClient.getInstance()
                .getApi().getmovies(API_KEY, params.key)
                .enqueue(new Callback<movielist>() {
                    @Override
                    public void onResponse(Call<movielist> call, Response<movielist> response) {
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {

                                callback.onResult(response.body().getResults(), adjacentKey);
                            }

                    }

                    @Override
                    public void onFailure(Call<movielist> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, movielist1> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getmovies(API_KEY, params.key)
                .enqueue(new Callback<movielist>() {
                    @Override
                    public void onResponse(Call<movielist> call, Response<movielist> response) {
                        if (response.body() != null && true) {

                                callback.onResult(response.body().getResults(), params.key + 1);

                        }
                    }

                    @Override
                    public void onFailure(Call<movielist> call, Throwable t) {

                    }
                });
    }
}
