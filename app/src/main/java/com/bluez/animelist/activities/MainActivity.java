package com.bluez.animelist.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bluez.animelist.R;
import com.bluez.animelist.adapters.RecyclerViewAdapter;
import com.bluez.animelist.model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
    private RequestQueue requestQueue;
    private JsonArrayRequest request;
    private RecyclerView recyclerView;
    private List<Anime> animeList;

    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe = findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipe.setRefreshing(true);
                jsonRequest();
            }
        });

        recyclerView = findViewById(R.id.recycler_view_id);
        animeList = new ArrayList<>();

        jsonRequest();
        swipe.setRefreshing(false);
    }

    private void jsonRequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for(int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Anime anime = new Anime();

                        anime.setName(jsonObject.getString("name"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setEpisodes(jsonObject.getInt("episode"));
                        anime.setCategory(jsonObject.getString("categorie"));
                        anime.setStudio(jsonObject.getString("studio"));
                        anime.setImgUrl(jsonObject.getString("img"));

                        animeList.add(anime);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setupRecyclerView(animeList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this
                        , "Couldn't fetch data!"
                        , Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    private void setupRecyclerView(List<Anime> animeList) {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, animeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
