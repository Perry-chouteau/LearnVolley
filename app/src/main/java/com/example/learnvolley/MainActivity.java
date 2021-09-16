package com.example.learnvolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    TextView tmp;
    TextView textView;
    Button button;
    String url;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Init
        tmp = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);

        button.setOnClickListener( (View view) -> {
            url = tmp.getText().toString();
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display characters of the response string.
                        textView.setHint(getText(R.string.textViewHint));
                        textView.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle Url Error.
                        if (url != null && url.length() != 0) {
                            textView.setText("");
                            textView.setHint(getText(R.string.textViewBadUrl_p1) + url + getText(R.string.textViewBadUrl_p2));
                        } else {
                            textView.setText("");
                            textView.setHint(getText(R.string.textViewNoUrl));
                        }
                    }
                }
            );
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        });
    }
}