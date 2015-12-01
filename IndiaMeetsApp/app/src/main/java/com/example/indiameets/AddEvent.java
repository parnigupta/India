package com.example.indiameets;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by grashmi on 11/19/2015.
 */
public class AddEvent extends Activity {
    Button badd;
    EditText etName, etDescription, etdate, ettime, etlocation;
    String name, description, date, time, location;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        etName = (EditText) findViewById(R.id.event_name);
        etDescription = (EditText) findViewById(R.id.event_description);
        etdate = (EditText) findViewById(R.id.event_date);
        ettime = (EditText) findViewById(R.id.event_time);
        etlocation = (EditText) findViewById(R.id.event_location);

    badd=(Button)findViewById(R.id.button_add);

    badd.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
 //       if (validate(etName, etDescription, etdate, ettime, etlocation)) {
            event.name = etName.getText().toString();
            event.description = etDescription.getText().toString();
            //String date = etdate.getText().toString();
            event.time = ettime.getText().toString();
            event.location = etlocation.getText().toString();

            getEvent(event);


   //     }

    }

    }

    );
}
    private void getEvent(final Event event) {
        final String url = "http://192.168.137.237:8888/api/login";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", event.name);
        params.put("description", event.description);
        params.put("time", event.time);
        params.put("location", event.location);
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    VolleyLog.v("Response:%n%s", response.toString(4));
                    Toast.makeText(AddEvent.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    if (response.getBoolean("success")) {
                     //loginToken = response.getString("token");
                        //userId = response.getString("userid");
     //                   Intent intent = new Intent();
                        // intent.putExtra("logintoken",loginToken);
                        //intent.putExtra("userid",userId);
       //                 setResult(RESULT_OK, intent);
          //              finish();
                        //Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddEvent.this, "Error in server", Toast.LENGTH_LONG).show();
            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

    public boolean validate(TextView _name, TextView _description, TextView _date, TextView _time, TextView _locations) {
        boolean valid = true;

        String name = _name.getText().toString();
        String description = _description.getText().toString();
        String date = _date.getText().toString();
        String time = _time.getText().toString();
        String location = _locations.getText().toString();

        if (name.isEmpty()) {
            _name.setError("enter a name");
            valid = false;
        } else {
            _name.setError(null);
        }

        if (description.isEmpty()) {
            _description.setError("enter description");
            valid = false;
        } else {
            _description.setError(null);
        }

        if (time.isEmpty()) {
            _time.setError("enter time");
            valid = false;
        } else {
            _time.setError(null);
        }

        if (location.isEmpty()) {
            _locations.setError("enter location");
            valid = false;
        } else {
            _locations.setError(null);
        }

            return valid;
        }

    }

