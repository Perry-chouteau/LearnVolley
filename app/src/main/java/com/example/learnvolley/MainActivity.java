package com.example.learnvolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            textView.setTextSize(10);
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display characters of the response string.
                        textView.setHint(getText(R.string.textViewHint));
                        if (response.charAt(0) == '[') { // It's a jsonArray ?
                            Log.d("TYPE", "It's a jsonArray !");
                            try {
                                // translate string to JsonArray & get each jsonObject & put it in a textView
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); ++i) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    if (i == 0)
                                        textView.setText(jsonObject.toString());
                                    else
                                        textView.setText(textView.getText() + "\n\n" + jsonObject.toString());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else if (response.charAt(0) == '{') { // It's a jsonObject ?
                            Log.d("TYPE", "It's a jsonObject !");
                            // translate string to JsonObject & put it in a textView
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                textView.setText(jsonObject.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else { // It's a string !
                            Log.d("TYPE", "It's a String !");
                            // no translation & put it in a textView.
                            textView.setText(response);
                        }
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