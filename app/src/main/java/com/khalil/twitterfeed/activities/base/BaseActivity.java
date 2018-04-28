package com.khalil.twitterfeed.activities.base;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.khalil.twitterfeed.R;
import com.khalil.twitterfeed.TwitterFeedApp;
import com.khalil.twitterfeed.components.Loader;
import com.khalil.twitterfeed.network.NetworkUI;
import com.khalil.twitterfeed.network.ServiceFactory;

public abstract class BaseActivity extends AppCompatActivity implements NetworkUI {

    @Override
    public void willStartCall() {
        Loader.showLoader(this,getString(R.string.text_please_wait),"");
    }

    @Override
    public void didFinishCall(boolean success) {
        Loader.hideLoader();
    }

    protected ServiceFactory getServiceFactory() {
        return TwitterFeedApp.getInstance().getServiceFactory();
    }
}
