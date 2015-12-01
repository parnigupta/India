package com.example.indiameets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Template extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template);

        Intent i=getIntent();

        String name = i.getStringExtra("name");
        String age = i.getStringExtra("age");
        String time = i.getStringExtra("time");
        String location = i.getStringExtra("location");
        Button sponsor;
        sponsor=(Button) findViewById(R.id.button_sponsor);

        TextView v1 = (TextView) findViewById(R.id.name);
        v1.setText(name);
        TextView v2 = (TextView) findViewById(R.id.age);
        v2.setText(age);
        TextView v3 = (TextView) findViewById(R.id.time);
        v3.setText(time);
        sponsor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Template.this, Sponsor.class);
                startActivity(intent);
            }
        });

                RequestQueue que = Volley.newRequestQueue(this);
        String url = "http://192.168.42.82:3000/api/joined";
        HashMap<String,String> param = new HashMap<String, String>();
        param.put("name", "Hackathon");

        JsonArrayRequest requ = new JsonArrayRequest(Request.Method.POST, url, new JSONObject(param), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d("rt", "qw");
                    JSONObject jsonObject = response.getJSONObject(0);
                            person person = new person();
                            if (!jsonObject.isNull("name")) {
                                person.name = jsonObject.getString("name");
                                Log.d("mag",person.name);
                            }
                            if (!jsonObject.isNull("description")) {
                                person.age = jsonObject.getString("description");
                                Log.d("ok",person.age);
                            }
                            if (!jsonObject.isNull("imagepath")) {
                                person.url = jsonObject.getString("imagepath");
                                Log.d("ok",person.url);
                            }
                            if (!jsonObject.isNull("time")) {
                                person.time = jsonObject.getString("time");
                                Log.d("ok",person.url);
                            }
                            if (!jsonObject.isNull("location")) {
                                person.location = jsonObject.getString("location");
                                Log.d("ok",person.url);
                            }

                            if (!jsonObject.isNull("join")) {
                                person.join = jsonObject.getJSONArray("join");
                                Log.d("ok",person.url);
                            }

                        Log.d("tag", "yo");

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Template.this,error.getMessage(),Toast.LENGTH_LONG).show();
                TextView v4 = (TextView) findViewById(R.id.location);
                v4.setText(error.getMessage());

            }
        });
        que.add(requ);

        Toast.makeText(Template.this, name, Toast.LENGTH_LONG).show();

    }
}
