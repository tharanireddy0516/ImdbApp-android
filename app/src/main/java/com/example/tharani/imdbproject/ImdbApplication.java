package com.example.tharani.imdbproject;

import android.app.Application;

import com.example.tharani.imdbproject.connection.ConnectivityReceiver;

/**
 * Created by Tharani on 1/7/2018.
 */

public class ImdbApplication extends Application {

    private static ImdbApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized ImdbApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
