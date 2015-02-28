package com.rayleeriver.googleimagesearch;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ray on 2/25/15.
 */
public class GoogleImagesArrayAdapter extends ArrayAdapter<GoogleImage> {
    public GoogleImagesArrayAdapter(Context context, List<GoogleImage> images) {
        super(context, R.layout.item_image_result, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
        }

        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

        ivImage.setImageResource(0);

        final GoogleImage googleImage = getItem(position);
        tvTitle.setText(Html.fromHtml(googleImage.title));

        Picasso.with(getContext()).load(googleImage.tbUrl).fit().centerCrop().into(ivImage);
        return convertView;
    }
}
