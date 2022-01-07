package com.rohfl.logansquaresample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bluelinelabs.logansquare.LoganSquare;
import com.rohfl.logansquaresample.model.ResponseGetQuoteModel;
import com.rohfl.logansquaresample.singleton.VolleySingleton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String QUOTE_URL = "https://api.quotable.io/random";

    TextView quoteTv;
    Button getQuoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTv = findViewById(R.id.quote_tv);
        getQuoteButton = findViewById(R.id.get_quote_button);

        getQuoteButton.setOnClickListener(v -> {
            getRandomQuote();
        });

    }

    private void getRandomQuote() {
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, QUOTE_URL, null,
                        response -> {
                            try {
                                ResponseGetQuoteModel obj = LoganSquare.parse(response.toString(), ResponseGetQuoteModel.class);
                                quoteTv.setText(obj.getContent());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> {

                        });
        VolleySingleton.getInstance(this).getRequestQueue().add(jsonObjectRequest);
    }

}