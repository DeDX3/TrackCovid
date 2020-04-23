package com.example.trackcovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvActive, tvConfirmed, tvRecovered, tvDeaths, tvDate;
    private TextView tvNewActive, tvNewRecovered, tvNewConfirmed, tvNewDeaths;

    private RecyclerView recyclerView;
    private StateDataAdapter adapter;
    private ArrayList<StateListItem> list;
    private RequestQueue rQueue;

    //These strings will represent total country data
    private String active, confirmed, deaths, recovered, date;
    private String newConfirmed, newDeaths, newRecovered;
    private int nActive;

    //These strings will represent statewise data
    private String stState, stConfirmed, stActive, stRecovered, stDeaths;
    private String stNewConfirmed, stNewRecovered, stNewDeaths;
    private int stNewActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create RecyclerView to show statewise data
        recyclerView = findViewById(R.id.rvStateWiseData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        rQueue = Volley.newRequestQueue(this);

        tvDate = findViewById(R.id.tvDate);
        tvActive = findViewById(R.id.tvActive);
        tvNewActive = findViewById(R.id.tvNewActive);
        tvConfirmed = findViewById(R.id.tvConfirmed);
        tvNewConfirmed = findViewById(R.id.tvNewConfirmed);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvNewRecovered = findViewById(R.id.tvNewRecovered);
        tvDeaths = findViewById(R.id.tvDeaths);
        tvNewDeaths = findViewById(R.id.tvNewDeaths);
        Button btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }

    private void jsonParse(){
        String url = "https://api.covid19india.org/data.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Get array
                    JSONArray jsonArray = response.getJSONArray("statewise");
                    JSONObject totaldata = jsonArray.getJSONObject(0);

                    //Get Data from array.
                    //This is total country data.
                    date = totaldata.getString("lastupdatedtime");
                    active = totaldata.getString("active");
                    confirmed = totaldata.getString("confirmed");
                    recovered = totaldata.getString("recovered");
                    deaths = totaldata.getString("deaths");
                    newConfirmed = totaldata.getString("deltaconfirmed");
                    newRecovered = totaldata.getString("deltarecovered");
                    newDeaths = totaldata.getString("deltadeaths");

                    for(int i = 1; i < jsonArray.length(); i++){
                        JSONObject state = jsonArray.getJSONObject(i);

                        //This is statewise data.
                        stState = state.getString("state");
                        stActive = state.getString("active");
                        stConfirmed = state.getString("confirmed");
                        stRecovered = state.getString("recovered");
                        stDeaths = state.getString("deaths");
                        stNewRecovered = state.getString("deltarecovered");
                        stNewDeaths = state.getString("deltadeaths");
                        stNewConfirmed = state.getString("deltaconfirmed");
                        stNewActive = (Integer.parseInt(stNewConfirmed)) - (Integer.parseInt(stNewRecovered)) + (Integer.parseInt(stNewDeaths));

                        list.add(new StateListItem(stState, stActive, stConfirmed, stRecovered, stDeaths, stNewConfirmed, stNewRecovered, stNewDeaths, stNewActive));
                        adapter = new StateDataAdapter(MainActivity.this, list);
                        recyclerView.setAdapter(adapter);

                    }

                    //Get new active cases
                    int newActive = (Integer.parseInt(newConfirmed)) - (Integer.parseInt(newRecovered)) + (Integer.parseInt(newDeaths));

                    //Display on TextView
                    tvDate.setText("Last Updated: "+date);

                    tvNewActive.setText("[+"+newActive+"]");
                    tvActive.setText(active);

                    tvNewConfirmed.setText("[+"+newConfirmed+"]");
                    tvConfirmed.setText(confirmed);

                    tvNewRecovered.setText("[+"+newRecovered+"]");
                    tvRecovered.setText(recovered);

                    tvNewDeaths.setText("[+"+newDeaths+"]");
                    tvDeaths.setText(deaths);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        rQueue.add(request);
    }
}
