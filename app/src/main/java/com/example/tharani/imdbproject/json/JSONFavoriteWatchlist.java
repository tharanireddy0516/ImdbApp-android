package com.example.tharani.imdbproject.json;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.tharani.imdbproject.MovieAdapter;
import com.example.tharani.imdbproject.MovieInfo;
import com.example.tharani.imdbproject.db.MovieDbHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tharani on 1/7/2018.
 */




public class JSONFavoriteWatchlist extends AsyncTask<Object, Object, ArrayList<HashMap<String, String>>> {
    ArrayList<MovieInfo> arrayList_imdb;
    RecyclerView fav_watch;
    Context context;
    MovieInfo imdb;
    MovieDbHelper movieDbHelper;
    RecyclerView currentView;
    int mode = 0;
    private ArrayList<HashMap<String, String>> arrayList;
    private JSONObject jsonObject;
    private String url_fav;

    public JSONFavoriteWatchlist(Context applicationContext, RecyclerView view, int mode) {
        this.context = applicationContext;
        this.currentView = view;
        this.mode = mode;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<HashMap<String, String>> doInBackground(Object... params) {
        arrayList_imdb = new ArrayList<>();
        arrayList_imdb.clear();
        movieDbHelper = new MovieDbHelper(context);

        if (mode == 1) {
            arrayList_imdb = movieDbHelper.getFavorite();
            Log.e("Fav entry", String.valueOf(arrayList_imdb));
        } else if (mode == 2) {
            arrayList_imdb = movieDbHelper.getWatchlist();
        }

        arrayList = new ArrayList<>();

        JSONdata jsoNdata = new JSONdata();
        try {
            for (int i = 0; i < arrayList_imdb.size(); i++) {
                url_fav = "http://api.themoviedb.org/3/movie/" + arrayList_imdb.get(i).getMovie_id()
                        + "?api_key=8496be0b2149805afa458ab8ec27560c";
                jsonObject = jsoNdata.getJSONFromURL(url_fav);
                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put("id", jsonObject.getString("id"));
                hashMap.put("original_title", jsonObject.getString("original_title"));
                hashMap.put("release_date", jsonObject.getString("release_date"));
                hashMap.put("popularity", jsonObject.getString("popularity"));
                hashMap.put("vote_count", jsonObject.getString("vote_count"));
                hashMap.put("vote_average", jsonObject.getString("vote_average"));
                hashMap.put("poster_path", "http://image.tmdb.org/t/p/original" +
                        jsonObject.getString("poster_path"));
                arrayList.add(hashMap);
            }

        } catch (JSONException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        Log.e("Arraylist fav",arrayList.toString());
        return arrayList;
    }
    @Override
    protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
        super.onPostExecute(result);
        MovieAdapter mAdapter = new MovieAdapter(context, result);
        currentView.setAdapter(mAdapter);
    }
}
