package com.rayleeriver.googleimagesearch.delegates;

import android.content.Intent;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.rayleeriver.googleimagesearch.models.GoogleImage;
import com.rayleeriver.googleimagesearch.adapters.GoogleImagesArrayAdapter;
import com.rayleeriver.googleimagesearch.activities.MainActivity;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.Map;

public class GoogleImageApiDelegate {
    private final MainActivity mainActivity;
    private GoogleImagesArrayAdapter googleImagesArrayAdapter;
    private String query;
    private boolean processingImages = false;

    public GoogleImageApiDelegate(MainActivity mainActivity, GoogleImagesArrayAdapter adapter) {
        this.mainActivity = mainActivity;
        this.googleImagesArrayAdapter = adapter;
    }

    public void fetchImagesForQueryFromIndex(String query, int start, Map<String, String> filtersMap) {
        synchronized (GoogleImageApiDelegate.class) {
            if (!processingImages) {
                processingImages = true;
                if (query != null) this.query = query;

                if (start == 0) {
                    mainActivity.getaGoogleImages().clear();
                }

                RequestParams params = new RequestParams();
                params.put("v", "1.0");
                params.put("q", this.query);
                params.put("rsz", "8");
                params.put("start", String.valueOf(start * 8));

                if (filtersMap != null && filtersMap.size() > 0) {
                    for (String key: filtersMap.keySet()) {
                        String value = filtersMap.get(key);
                        if (value != null) {
                            params.put(key, value);
                        }
                    }
                }

                MainActivity.asyncHttpClient.get(MainActivity.searchUrl, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        if (statusCode == 200) {
                            JSONObject responseData = response.optJSONObject("responseData");
                            if (responseData != null) {
                                mainActivity.getaGoogleImages().addAll(GoogleImage.fromJsonArray(responseData.optJSONArray("results")));
                                mainActivity.getaGoogleImages().notifyDataSetChanged();
                            }
                        }

                        processingImages = false;
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        processingImages = false;
                    }
                });
            }
        }
    }
}