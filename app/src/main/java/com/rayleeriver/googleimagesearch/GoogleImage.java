package com.rayleeriver.googleimagesearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ray on 2/25/15.
 */
public class GoogleImage {
    public String title;
    public String tbUrl;

    public GoogleImage(String title, String url) {
        this.title = title;
        this.tbUrl = url;
    }

    public static GoogleImage fromJsonObject(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {
            String title = jsonObject.getString("title");
            String tbUrl = jsonObject.getString("tbUrl");
            GoogleImage googleImage = new GoogleImage(title, tbUrl);
            return googleImage;
        }
        return null;
    }

    public static List<GoogleImage> fromJsonArray(JSONArray jsonArray) {
        if (jsonArray != null) {
            List<GoogleImage> googleImages = new ArrayList<GoogleImage>();

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    googleImages.add(fromJsonObject(jsonArray.optJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            return googleImages;
        }
        return null;
    }
}
