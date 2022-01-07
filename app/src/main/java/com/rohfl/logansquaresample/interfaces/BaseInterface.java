package com.rohfl.logansquaresample.interfaces;

public interface BaseInterface {

    interface View {
        void showProgress();
        void hideProgress();
        void showQuote(String quote);
    }

    interface Presenter {
        void getQuote();
        void onDestroy();
    }

}


