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

public class NowPlaying extends Fragment {

    public NowPlaying() {
    }

    RecyclerView recyclerView_nowPlaying;
    public View view_nowPlaying;
    int mode =0;
    String URL_nowPlaying = "http://api.themoviedb.org/3/movie/now_playing?api_key=8496be0b2149805afa458ab8ec27560c";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);

    }

    @Override
    public void onPause() {
        setRetainInstance(true);
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onViewCreated(container, savedInstanceState);
        view_nowPlaying = inflater.inflate(R.layout.nowplaying_fragment, container, false);
        recyclerView_nowPlaying = (RecyclerView) view_nowPlaying.findViewById(R.id.recyclerView_nowPlaying);
        loadRecyclerViewData();
        return view_nowPlaying;
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

            case R.id.most_popular:
                Toast.makeText(getActivity(),"Most Popular Movies", Toast.LENGTH_SHORT).show();
                break;

            case R.id.up_coming:
                Toast.makeText(getActivity(),"Upcoming Movies", Toast.LENGTH_SHORT).show();
                break;

            case R.id.latest_movies:
                Toast.makeText(getActivity(),"Latest Movies", Toast.LENGTH_SHORT).show();
                break;

            case R.id.now_playing:
                Toast.makeText(getActivity(),"Now Playing", Toast.LENGTH_SHORT).show();
                break;

            case R.id.top_rated:
                Toast.makeText(getActivity(),"Top Rated", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_favorites:
                mode = 1;
                JSONFavoriteWatchlist JSON_favorite_watchlist = new JSONFavoriteWatchlist(getActivity().getApplicationContext(), recyclerView_nowPlaying, mode);
                JSON_favorite_watchlist.execute();
                Toast.makeText(getActivity(),"My Favorites", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_watchlist:
                mode = 2;
                JSONFavoriteWatchlist JSON_favorite_watchlist1 = new JSONFavoriteWatchlist(getActivity().getApplicationContext(),recyclerView_nowPlaying, mode);
                JSON_favorite_watchlist1.execute();
                Toast.makeText(getActivity(),"My Watchlist", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_refresh:
                new NowPlaying();
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
        DownloadJSON downloadJSON = new DownloadJSON(getActivity().getApplication().getApplicationContext(), recyclerView_nowPlaying);
        downloadJSON.getJSON(URL_nowPlaying);
    }

}
