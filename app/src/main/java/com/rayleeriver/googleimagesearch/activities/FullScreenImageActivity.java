package com.rayleeriver.googleimagesearch.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.rayleeriver.googleimagesearch.GoogleImage;
import com.rayleeriver.googleimagesearch.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ray on 2/26/15.
 */
public class FullScreenImageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_image);
        GoogleImage googleImage = (GoogleImage) getIntent().getSerializableExtra("googleImage");

        ImageView fullImage = (ImageView) findViewById(R.id.ivFullImage);
        Picasso.with(this).load(googleImage.url).into(fullImage);
    }
}
