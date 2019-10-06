package com.example.katalogfilm.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.katalogfilm.Class.Movies;
import com.example.katalogfilm.Detail.MoviesDetailActivity;
import com.example.katalogfilm.R;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private final ArrayList<Movies> mData = new ArrayList<>();

    public void setData(ArrayList<Movies> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_movies, parent, false);
        return new MoviesViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleFilm, languageFilm;
        ImageView imgPhoto;

        private MoviesViewHolder(View mView) {
            super(mView);
            titleFilm = mView.findViewById(R.id.tv_item_title);
            languageFilm = mView.findViewById(R.id.tv_item_language);
            imgPhoto = mView.findViewById(R.id.img_item_photo);

            itemView.setOnClickListener(this);
        }

        void bind(Movies movies) {
            String url_image = "https://image.tmdb.org/t/p/w185" + movies.getPhoto();

            titleFilm.setText(movies.getTittle());
            languageFilm.setText(movies.getOriginal_language());

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPhoto);
    }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movies movie = mData.get(position);
//
            movie.setTittle(movie.getTittle());
            movie.setOverview(movie.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), MoviesDetailActivity.class);
            moveWithObjectIntent.putExtra(MoviesDetailActivity.EXTRA_MOVIE, movie);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }
}
