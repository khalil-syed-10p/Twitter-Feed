package com.khalil.twitterfeed;

import android.app.Application;

import com.khalil.twitterfeed.network.ServiceFactory;
import com.khalil.twitterfeed.network.ServiceProtocol;

public class TwitterFeedApp extends Application {

    private ServiceFactory serviceFactory;
    private static TwitterFeedApp instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static TwitterFeedApp getInstance() {
        return instance;
    }

    private void initServiceFactory() {
        serviceFactory = new ServiceFactory(new ServiceProtocol());
    }

    public ServiceFactory getServiceFactory() {
        if(serviceFactory == null) {
            initServiceFactory();
        }
        return serviceFactory;
    }
}
