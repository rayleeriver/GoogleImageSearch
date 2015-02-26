package com.rayleeriver.googleimagesearch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public static final String searchUrl = "https://ajax.googleapis.com/ajax/services/search/images";
    private static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    GridView gvImages;
    private List<GoogleImage> googleImages;
    private GoogleImagesArrayAdapter aGoogleImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {

        googleImages = new ArrayList<GoogleImage>();
        aGoogleImages = new GoogleImagesArrayAdapter(this, googleImages);
        gvImages = (GridView) findViewById(R.id.gvImages);
        gvImages.setAdapter(aGoogleImages);

        // setup toolbar
        Toolbar toobar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toobar);

        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etSearch = (EditText) findViewById(R.id.etSearch);

                if (etSearch.getText().length() > 0) {

                    RequestParams params = new RequestParams();
                    params.put("v","1.0");
                    params.put("q",etSearch.getText().toString());
                    params.put("rsz", "8");

                    asyncHttpClient.get(searchUrl, params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            if (statusCode == 200) {
                                JSONObject responseData = response.optJSONObject("responseData");
                                if (response != null) {
                                    aGoogleImages.clear();
                                    googleImages.addAll(GoogleImage.fromJsonArray(responseData.optJSONArray("results")));
                                    aGoogleImages.notifyDataSetChanged();
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
