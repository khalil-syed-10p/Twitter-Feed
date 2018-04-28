package com.khalil.twitterfeed.network;

import android.app.Activity;
import android.view.View;

public interface CommonNetworkActionsHandler {

    void onUnAuthorized(ServiceCall serviceCall, String errorMessage, int code, View contentView);

    void onInternetConnectionError(ServiceCall serviceCall, View contentView);

}
