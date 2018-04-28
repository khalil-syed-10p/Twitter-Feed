package com.khalil.twitterfeed.network;

import android.app.Activity;
import android.view.View;

public interface NetworkUI {

    void willStartCall();
    void didFinishCall(boolean success);
    View getContentView();
}
