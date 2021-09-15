package com.example.learnvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    RequestQueue queue;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init
        // Instantiate the RequestQueue.
        textView = (TextView) findViewById(R.id.textView);
        queue = Volley.newRequestQueue(this);
        url ="URL OF DATA="; // URL OF DATA

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                            textView.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                  /*      textView.setText("That didn't work!");*/
                    }
                }
        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}