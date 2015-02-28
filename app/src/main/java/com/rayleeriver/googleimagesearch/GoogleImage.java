package com.rayleeriver.googleimagesearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ray on 2/25/15.
 */
public class GoogleImage implements Serializable {
    public String title;

    public String tbUrl;
    public String url;

//    "width": "4288",
//            "height": "2848",
//            "imageId": "ANd9GcT1gNv9kRclytVPib80clNEqUxeoWL4XAQq3id8xdac71z8Gr2uCcuIaytR",
//            "tbWidth": "150",
//            "tbHeight": "100",
//            "unescapedUrl": "http://animalia-life.com/data_images/cat/cat6.jpg",
//            "url": "http://animalia-life.com/data_images/cat/cat6.jpg",


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
}
