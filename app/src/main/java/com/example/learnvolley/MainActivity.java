package com.example.learnvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    RequestQueue queue;
    String url;
    TextView tmp;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init
        // Instantiate the RequestQueue.
        tmp = findViewById(R.id.editTextURL);
        textView = (TextView) findViewById(R.id.textView);
        queue = Volley.newRequestQueue(this);
        button = findViewById(R.id.button);

        button.setOnClickListener( (View view) -> {

                url = tmp.getText().toString(); // URL OF DATA
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
                        response -> textView.setText(response),
                        error -> textView.setText("This URL doesn't exist!")
                );

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
        });
    }
}