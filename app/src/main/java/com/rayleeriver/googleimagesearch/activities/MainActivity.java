package com.rayleeriver.googleimagesearch.activities;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.rayleeriver.googleimagesearch.EndlessScrollListener;
import com.rayleeriver.googleimagesearch.GoogleImage;
import com.rayleeriver.googleimagesearch.GoogleImagesArrayAdapter;
import com.rayleeriver.googleimagesearch.R;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public static final String searchUrl = "https://ajax.googleapis.com/ajax/services/search/images";
    public static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    GridView gvImages;
    List<GoogleImage> googleImages;
    GoogleImagesArrayAdapter aGoogleImages;

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
        gvImages.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemCount) {

            }
        });

        gvImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, FullScreenImageActivity.class);
                intent.putExtra("googleImage", aGoogleImages.getItem(position));
                startActivity(intent);
            }

        });

        // setup toolbar
        Toolbar toobar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toobar);

//        Button btnSearch = (Button) findViewById(R.id.btnSearch);
//        btnSearch.setOnClickListener(new GoogleImagesSearchOnClickListener(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView svSearch = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                RequestParams params = new RequestParams();
                params.put("v", "1.0");
                params.put("q", s);
                params.put("rsz", "8");

                MainActivity.asyncHttpClient.get(MainActivity.searchUrl, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        if (statusCode == 200) {
                            JSONObject responseData = response.optJSONObject("responseData");
                            if (response != null) {
                                aGoogleImages.clear();
                                aGoogleImages.addAll(GoogleImage.fromJsonArray(responseData.optJSONArray("results")));
                                aGoogleImages.notifyDataSetChanged();
                            }
                        }

                        Intent i = new Intent();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
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
