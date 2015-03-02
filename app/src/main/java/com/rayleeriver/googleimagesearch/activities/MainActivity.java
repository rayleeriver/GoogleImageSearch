package com.rayleeriver.googleimagesearch.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.rayleeriver.googleimagesearch.NutraBaseImageDecoder;
import com.rayleeriver.googleimagesearch.fragments.FiltersDialog;
import com.rayleeriver.googleimagesearch.listeners.EndlessScrollListener;
import com.rayleeriver.googleimagesearch.models.GoogleImage;
import com.rayleeriver.googleimagesearch.adapters.GoogleImagesArrayAdapter;
import com.rayleeriver.googleimagesearch.R;
import com.rayleeriver.googleimagesearch.delegates.GoogleImageApiDelegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    public static final String searchUrl = "https://ajax.googleapis.com/ajax/services/search/images";
    public static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private GoogleImageApiDelegate googleImageApiDelegate = null;

    private Map<String, String> filtersMap = new HashMap<String, String> ();

    GridView gvImages;
    List<GoogleImage> googleImages;

    public GoogleImagesArrayAdapter getaGoogleImages() {
        return aGoogleImages;
    }

    GoogleImagesArrayAdapter aGoogleImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).imageDecoder(new NutraBaseImageDecoder(false)).build();
        ImageLoader.getInstance().init(config);
        googleImageApiDelegate = new GoogleImageApiDelegate(this, aGoogleImages);
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
                googleImageApiDelegate.fetchImagesForQueryFromIndex(null, page, filtersMap);
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

        Toolbar toobar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toobar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView svSearch = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        svSearch.setOnQueryTextListener(new MyOnQueryTextListener());
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
            FragmentManager fragmentManager = getSupportFragmentManager();
            FiltersDialog filtersDialog = FiltersDialog.newInstance("Filters");
            filtersDialog.show(fragmentManager, "fragment_filter_dialog");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyOnQueryTextListener implements SearchView.OnQueryTextListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            googleImageApiDelegate.fetchImagesForQueryFromIndex(query, 0, filtersMap);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    }

    public Map<String, String> getFiltersMap() {
        return filtersMap;
    }

    public void setFiltersMap(Map<String, String> filtersMap) {
        this.filtersMap = filtersMap;
    }


}
