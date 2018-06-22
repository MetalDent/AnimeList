package com.bluez.animelist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluez.animelist.R;
import com.bluez.animelist.model.Anime;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by bluez on 22/6/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Anime> animeList;
    RequestOptions glideOptions;

    public RecyclerViewAdapter(Context mContext, List<Anime> animeList) {
        this.mContext = mContext;
        this.animeList = animeList;
        glideOptions = new RequestOptions().centerCrop()
                .placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.anime_model,
                parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(animeList.get(position).getName());
        holder.category.setText(animeList.get(position).getCategory());
        holder.episodes.setText(String.format("%d Episodes", animeList.get(position).getEpisodes()));
        holder.rating.setText(animeList.get(position).getRating());
        holder.studio.setText(animeList.get(position).getStudio());

        Glide.with(mContext)
                .load(animeList.get(position).getImgUrl())
                .apply(glideOptions)
                .into(holder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView category;
        TextView episodes;
        TextView rating;
        TextView studio;
        ImageView img_thumbnail;


        public MyViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.anime_title);
            category = itemView.findViewById(R.id.category);
            episodes = itemView.findViewById(R.id.episodes);
            rating = itemView.findViewById(R.id.rating);
            studio = itemView.findViewById(R.id.studio);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
