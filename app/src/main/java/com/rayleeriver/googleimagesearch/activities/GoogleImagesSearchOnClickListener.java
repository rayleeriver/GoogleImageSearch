package com.rayleeriver.googleimagesearch.activities;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.rayleeriver.googleimagesearch.GoogleImage;
import com.rayleeriver.googleimagesearch.R;

import org.apache.http.Header;
import org.json.JSONObject;

/**
* Created by ray on 2/26/15.
*/
class GoogleImagesSearchOnClickListener implements View.OnClickListener {
    private MainActivity mainActivity;

    public GoogleImagesSearchOnClickListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
//        EditText etSearch = (EditText) mainActivity.findViewById(R.id.etSearch);
//
//        if (etSearch.getText().length() > 0) {
//
//            RequestParams params = new RequestParams();
//            params.put("v", "1.0");
//            params.put("q", etSearch.getText().toString());
//            params.put("rsz", "8");
//
//            MainActivity.asyncHttpClient.get(MainActivity.searchUrl, params, new JsonHttpResponseHandler() {
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                    if (statusCode == 200) {
//                        JSONObject responseData = response.optJSONObject("responseData");
//                        if (response != null) {
//                            mainActivity.aGoogleImages.clear();
//                            mainActivity.googleImages.addAll(GoogleImage.fromJsonArray(responseData.optJSONArray("results")));
//                            mainActivity.aGoogleImages.notifyDataSetChanged();
//                        }
//                    }
//
//                    Intent i = new Intent();
//
//                }
//
//                @Override
//                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                    super.onFailure(statusCode, headers, throwable, errorResponse);
//                }
//            });
//        }
    }
}
