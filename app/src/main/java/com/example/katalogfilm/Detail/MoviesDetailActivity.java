package com.example.katalogfilm.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.katalogfilm.Class.Movies;
import com.example.katalogfilm.R;

public class MoviesDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    TextView tvTitle, tvOverview, tvLanguage;
    private ProgressBar progressBar;
    ImageView imagePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);

        tvTitle = findViewById(R.id.tv_detail_tittle);
        tvOverview = findViewById(R.id.tv_detail_overview);
        tvLanguage = findViewById(R.id.tv_detail_language);
        imagePhoto = findViewById(R.id.poster_movies);

        progressBar = findViewById(R.id.progressDetailMovie);

        progressBar.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(5000);
                }
                catch (Exception e) { }

                handler.post(new Runnable() {
                    public void run() {
                        Movies movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

                        String url_image = "https://image.tmdb.org/t/p/w185" + movie.getPhoto();

                        tvTitle.setText(movie.getTittle());
                        tvOverview.setText(movie.getOverview());
                        tvLanguage.setText(movie.getOriginal_language()) ;
                        Glide.with(MoviesDetailActivity.this)
                                .load(url_image)
                                .placeholder(R.color.colorAccent)
                                .dontAnimate()
                                .into(imagePhoto);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }
}
