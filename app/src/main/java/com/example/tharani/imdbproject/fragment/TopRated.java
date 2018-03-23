package com.example.tharani.imdbproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tharani.imdbproject.R;
import com.example.tharani.imdbproject.json.DownloadJSON;
import com.example.tharani.imdbproject.json.JSONFavoriteWatchlist;

/**
 * Created by Tharani on 1/7/2018.
 */

public class TopRated  extends Fragment {

    public TopRated() {
    }
    RecyclerView recyclerView_topRated;
    View view_topRated;
    int mode =0;
    String URL_topRated = "http://api.themoviedb.org/3/movie/top_rated?api_key=8496be0b2149805afa458ab8ec27560c";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onPause() {
        setRetainInstance(true);
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view_topRated = inflater.inflate(R.layout.toprated_fragment, container, false);
        recyclerView_topRated = (RecyclerView) view_topRated.findViewById(R.id.recyclerView_topRated);
        loadRecyclerViewData();
        return view_topRated;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_favorites:
                mode = 1;
                JSONFavoriteWatchlist JSON_favorite_watchlist = new JSONFavoriteWatchlist(getActivity().getApplicationContext(), recyclerView_topRated, mode);
                JSON_favorite_watchlist.execute();
                Toast.makeText(getActivity(),"My Favorites", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_watchlist:
                mode = 2;
                JSONFavoriteWatchlist JSON_favorite_watchlist1 = new JSONFavoriteWatchlist(getActivity().getApplicationContext(),recyclerView_topRated, mode);
                JSON_favorite_watchlist1.execute();
                Toast.makeText(getActivity(),"My WatchList", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_refresh:
                new TopRated();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void loadRecyclerViewData(){
        DownloadJSON downloadJSON = new DownloadJSON(getActivity().getApplication().getApplicationContext(), recyclerView_topRated);
        downloadJSON.getJSON(URL_topRated);
    }
}
