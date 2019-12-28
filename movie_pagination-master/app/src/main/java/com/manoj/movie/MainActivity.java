package com.manoj.movie;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.manoj.movie.adapter.ItemAdapter;
import com.manoj.movie.api.pojo.movielist1;
import com.manoj.movie.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ItemAdapter adapter;
    RecyclerView recyclerView;
    int pagenumber;
    String movieCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        movieCategory = "Now Playing";
        pagenumber = 1;


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        adapter = new ItemAdapter(this);
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<movielist1>>() {
            @Override
            public void onChanged(@Nullable final PagedList<movielist1> items) {
                adapter.submitList(items);
            }
        });

        recyclerView.setAdapter(adapter);
    }


}
