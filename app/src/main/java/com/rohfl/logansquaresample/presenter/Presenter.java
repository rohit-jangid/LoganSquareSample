package com.rohfl.logansquaresample.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bluelinelabs.logansquare.LoganSquare;
import com.rohfl.logansquaresample.interfaces.BaseInterface;
import com.rohfl.logansquaresample.model.ResponseGetQuoteModel;
import com.rohfl.logansquaresample.singleton.VolleySingleton;

import java.io.IOException;

public class Presenter implements BaseInterface.Presenter {

    private final String QUOTE_URL = "https://api.quotable.io/random";

    private BaseInterface.View view;
    private Context ctx;

    public Presenter(BaseInterface.View view, Context ctx) {
        this.view = view;
        this.ctx = ctx;
    }

    @Override
    public void getQuote() {
        getRandomQuote();
    }

    private void getRandomQuote() {
        view.showProgress();
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, QUOTE_URL, null,
                        response -> {
                            try {
                                ResponseGetQuoteModel obj = LoganSquare.parse(response.toString(), ResponseGetQuoteModel.class);
                                view.hideProgress();
                                view.showQuote(obj.getContent());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> {

                        });
        VolleySingleton.getInstance(this.ctx).getRequestQueue().add(jsonObjectRequest);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.ctx = null;
    }

}
