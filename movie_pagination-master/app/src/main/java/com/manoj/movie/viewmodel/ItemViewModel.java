package com.manoj.movie.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import com.manoj.movie.api.pojo.movielist1;
import com.manoj.movie.repos.ItemDataSource;
import com.manoj.movie.repos.ItemDataSourceFactory;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<movielist1>> itemPagedList;
    public LiveData<PageKeyedDataSource<Integer, movielist1>> liveDataSource;

    public ItemViewModel() {
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE).build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }


}
