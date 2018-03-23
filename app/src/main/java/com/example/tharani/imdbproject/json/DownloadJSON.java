package com.example.tharani.imdbproject.json;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tharani.imdbproject.MovieAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tharani on 1/7/2018.
 */

public class DownloadJSON {
    private Context context;
    private RecyclerView recyclerView_currentTab;
    private JSONArray jsonArray;
    private ArrayList<HashMap<String, String>> arrayMovieList = new ArrayList<>();

    public DownloadJSON(Context context, RecyclerView view) {
        this.context = context;
        this.recyclerView_currentTab = view;
    }

    public void getJSON(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String result) {
                        if (result == null || result.length() == 0) {
                            Log.e("Json parsed is :", "null");
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                jsonArray = jsonObject.getJSONArray("results");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    HashMap<String, String> hashMap = new HashMap<>();
                                    jsonObject = jsonArray.getJSONObject(i);
                                    if (jsonObject.has("id")) {
                                        hashMap.put("id", jsonObject.getString("id"));
                                    } else {
                                        hashMap.put("id", "id NA");
                                    }
                                    if (jsonObject.has("original_title")) {
                                        hashMap.put("original_title", jsonObject.getString("original_title"));
                                    } else {
                                        hashMap.put("original_title", "Title NA");
                                    }
                                    if (jsonObject.has("release_date")) {
                                        hashMap.put("release_date", jsonObject.getString("release_date"));
                                    } else {
                                        hashMap.put("release_date", "Date NA");
                                    }

                                    if (jsonObject.has("popularity")) {
                                        hashMap.put("popularity", jsonObject.getString("popularity"));
                                    } else {
                                        hashMap.put("popularity", "Popularity NA");
                                    }

                                    if (jsonObject.has("vote_count")) {
                                        hashMap.put("vote_count", jsonObject.getString("vote_count"));
                                    } else {
                                        hashMap.put("vote_count", "Vote NA");
                                    }

                                    if (jsonObject.has("vote_average")) {
                                        hashMap.put("vote_average", jsonObject.getString("vote_average"));
                                    } else {
                                        hashMap.put("vote_average", "Average Vote NA");
                                    }

                                    if (jsonObject.has("poster_path")) {
                                        hashMap.put("poster_path", "http://image.tmdb.org/t/p/original" +
                                                jsonObject.getString("poster_path"));
                                    } else {
                                        hashMap.put("poster_path", "Poster NA");
                                    }
                                    arrayMovieList.add(hashMap);
                                }
                                MovieAdapter mAdapter = new MovieAdapter(context, arrayMovieList);
                                recyclerView_currentTab.setAdapter(mAdapter);
                                recyclerView_currentTab.setLayoutManager(new LinearLayoutManager(context));
                                recyclerView_currentTab.smoothScrollToPosition(0);
                            } catch (JSONException e) {
                                Log.e("Error", e.getMessage());
                            }
                        }
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
