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

public class Upcoming extends Fragment {

    public Upcoming() {
    }
    RecyclerView recyclerView_upComing;
    View view_upComing;
    int mode =0;
    String URL_upComing = "http://api.themoviedb.org/3/movie/upcoming?api_key=8496be0b2149805afa458ab8ec27560c";

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

        view_upComing = inflater.inflate(R.layout.upcoming_fragment, container, false);
        recyclerView_upComing= (RecyclerView)view_upComing.findViewById(R.id.recyclerView_upComing);
        loadRecyclerViewData();
        return view_upComing;
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
                JSONFavoriteWatchlist JSON_favorite_watchlist = new JSONFavoriteWatchlist(getActivity().getApplicationContext(), recyclerView_upComing, mode);
                JSON_favorite_watchlist.execute();
                Toast.makeText(getActivity(),"My Favorites", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_watchlist:
                mode = 2;
                JSONFavoriteWatchlist JSON_favorite_watchlist1 = new JSONFavoriteWatchlist(getActivity().getApplicationContext(),recyclerView_upComing, mode);
                JSON_favorite_watchlist1.execute();
                Toast.makeText(getActivity(),"My Watchlist", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_refresh:
                new Upcoming();
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
        DownloadJSON downloadJSON = new DownloadJSON(getActivity().getApplication().getApplicationContext(), recyclerView_upComing);
        downloadJSON.getJSON(URL_upComing);
    }
}
