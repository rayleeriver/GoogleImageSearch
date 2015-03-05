package com.rayleeriver.googleimagesearch.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.rayleeriver.googleimagesearch.TouchImageView;
import com.rayleeriver.googleimagesearch.models.GoogleImage;
import com.rayleeriver.googleimagesearch.R;

/**
 * Created by ray on 2/26/15.
 */
public class FullScreenImageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_image);
        GoogleImage googleImage = (GoogleImage) getIntent().getParcelableExtra("googleImage");

//        final ProgressBar pbFullImage = (ProgressBar) findViewById(R.id.pbFullImage);
//        pbFullImage.setVisibility(View.VISIBLE);

        TouchImageView fullImage = (TouchImageView) findViewById(R.id.ivFullImage);

        ImageLoader.getInstance().displayImage(googleImage.url, fullImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Log.d("debug", failReason.toString());
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                pbFullImage.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
//                                pbFullImage.setVisibility(View.GONE);
            }
        });

    }
}
