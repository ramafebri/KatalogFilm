package com.example.katalogfilm.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.katalogfilm.Class.TvShow;
import com.example.katalogfilm.R;

public class TvDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "extra_movie";

    TextView tvName, tvLanguage, tvOverview;
    ImageView imagePhoto;

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        tvName = findViewById(R.id.tv_detail_tittle);
        tvLanguage = findViewById(R.id.tv_detail_language);
        tvOverview = findViewById(R.id.tv_detail_overview);
        imagePhoto = findViewById(R.id.poster_TV);
        progressBar = findViewById(R.id.progressDetailTV);

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
                        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);

                        String url_image = "https://image.tmdb.org/t/p/w185" + tvShow.getPoster_path();

                        tvName.setText(tvShow.getName());
                        tvOverview.setText(tvShow.getOverview());
                        tvLanguage.setText(tvShow.getOriginal_language()) ;

                        Glide.with(TvDetailActivity.this)
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
