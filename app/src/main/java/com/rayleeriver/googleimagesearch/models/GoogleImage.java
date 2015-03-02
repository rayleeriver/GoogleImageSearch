package com.rayleeriver.googleimagesearch.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ray on 2/25/15.
 */
public class GoogleImage implements Parcelable {
    public String title;
    public String tbUrl;
    public String url;


    public GoogleImage(String title, String tbUrl, String url) {
        this.title = title;
        this.tbUrl = tbUrl;
        this.url = url;
    }

    public static GoogleImage fromJsonObject(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {
            String title = jsonObject.getString("title");
            String tbUrl = jsonObject.getString("tbUrl");
            String url = jsonObject.getString("url");
            GoogleImage googleImage = new GoogleImage(title, tbUrl, url);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(tbUrl);
        dest.writeString(url);
    }

    private GoogleImage(Parcel in) {
        title = in.readString();
        tbUrl = in.readString();
        url = in.readString();
    }

    public static final Creator<GoogleImage> CREATOR = new Creator<GoogleImage>() {

        @Override
        public GoogleImage createFromParcel(Parcel source) {
            return new GoogleImage(source);
        }

        @Override
        public GoogleImage[] newArray(int size) {
            return new GoogleImage[size];
        }
    };
}
