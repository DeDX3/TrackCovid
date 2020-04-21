package com.example.trackcovid;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    TextView tvActive, tvConfirmed, tvRecovered, tvDeaths, tvDate;
    TextView tvNewActive, tvNewRecovered, tvNewConfirmed, tvNewDeaths;
    RequestQueue rQueue;
    Button btnFetch;

    String active, confirmed, deaths, recovered, date;
    String newConfirmed, newDeaths, newRecovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        btnFetch = findViewById(R.id.btnFetch);

        btnFetch.setOnClickListener(new View.OnClickListener() {
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
                    //Get array through API
                    JSONArray jsonArray = response.getJSONArray("statewise");
                    JSONObject statewise = jsonArray.getJSONObject(0);

                    //Get data from array
                    date = statewise.getString("lastupdatedtime");
                    active = statewise.getString("active");
                    confirmed = statewise.getString("confirmed");
                    recovered = statewise.getString("recovered");
                    deaths = statewise.getString("deaths");
                    newConfirmed = statewise.getString("deltaconfirmed");
                    newRecovered = statewise.getString("deltarecovered");
                    newDeaths = statewise.getString("deltadeaths");

                    //Get new active cases
                    int nActive = (Integer.parseInt(newConfirmed)) - (Integer.parseInt(newRecovered)) + (Integer.parseInt(newDeaths));

                    //Display on TextView
                    tvDate.setText("Last Updated: "+date+"\n");

                    tvNewActive.setText("New Active Cases: "+nActive);
                    tvActive.setText("Total Active Cases: "+active+"\n");

                    tvNewConfirmed.setText("New Confirmed Cases: "+newConfirmed);
                    tvConfirmed.setText("Total Confirmed Cases: "+confirmed+"\n");

                    tvNewRecovered.setText("New Recovered Cases: "+newRecovered);
                    tvRecovered.setText("Total Recovered Cases: "+recovered+"\n");

                    tvNewDeaths.setText("New Deaths: "+newDeaths);
                    tvDeaths.setText("Total Deaths: "+deaths+"\n");

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
