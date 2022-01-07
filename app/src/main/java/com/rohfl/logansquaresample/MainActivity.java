package com.rohfl.logansquaresample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bluelinelabs.logansquare.LoganSquare;
import com.rohfl.logansquaresample.interfaces.BaseInterface;
import com.rohfl.logansquaresample.model.ResponseGetQuoteModel;
import com.rohfl.logansquaresample.presenter.Presenter;
import com.rohfl.logansquaresample.singleton.VolleySingleton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements BaseInterface.View {

    private final String QUOTE_URL = "https://api.quotable.io/random";

    TextView quoteTv;
    Button getQuoteButton;
    ProgressBar progressBar;

    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        updateStatusBarColorMain("#FFFFFF");

        setContentView(R.layout.activity_main);

        presenter = new Presenter(this, this);

        quoteTv = findViewById(R.id.quote_tv);
        getQuoteButton = findViewById(R.id.get_quote_button);
        progressBar = findViewById(R.id.progress_bar);

        getQuoteButton.setOnClickListener(v -> {
            quoteTv.setText(null);
            presenter.getQuote();
        });

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showQuote(String quote) {
        quoteTv.setText(quote);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @SuppressLint("ObsoleteSdkInt")
    public void updateStatusBarColorMain(String color) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor(color));
                window.setNavigationBarColor(Color.parseColor(color));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int systemUiVisibility = this.getWindow().getDecorView().getSystemUiVisibility();
                int flagLightStatusBar = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                systemUiVisibility |= flagLightStatusBar;
                this.getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}