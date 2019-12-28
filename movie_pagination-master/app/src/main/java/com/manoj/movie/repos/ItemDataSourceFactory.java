package com.manoj.movie.repos;



import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.manoj.movie.api.pojo.movielist1;

import java.util.List;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, movielist1>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource<Integer, movielist1> create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, movielist1>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
