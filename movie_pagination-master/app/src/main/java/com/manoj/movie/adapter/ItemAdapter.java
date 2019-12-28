package com.manoj.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manoj.movie.R;
import com.manoj.movie.api.pojo.movielist1;

import static com.manoj.movie.api.api.imageurl;

public class ItemAdapter extends PagedListAdapter<movielist1, ItemAdapter.ItemViewHolder> {

    private static DiffUtil.ItemCallback<movielist1> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<movielist1>() {
                @Override
                public boolean areItemsTheSame(movielist1 oldItem, movielist1 newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(movielist1 oldItem, movielist1 newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private Context mCtx;

    public ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.movie_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final movielist1 ddt = getItem(position);
        holder.names.setText(ddt.getTitle());
        holder.ratingrate.setText(String.valueOf(ddt.getVoteAverage()));
        Glide.with(holder.context).load(imageurl+ddt.getPosterPath()).into(holder.image);

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView names,ratingrate;
        ImageView image;
        Context context;

        public ItemViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();
            names = itemView.findViewById(R.id.moviename);
            image = itemView.findViewById(R.id.image);
            ratingrate = itemView.findViewById(R.id.ratingrate);
        }
    }
}
