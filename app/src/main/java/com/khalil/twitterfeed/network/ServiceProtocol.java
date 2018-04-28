package com.khalil.twitterfeed.network;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.khalil.twitterfeed.BuildConfig;
import com.khalil.twitterfeed.R;
import com.khalil.twitterfeed.TwitterFeedApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import okhttp3.Request;
import retrofit2.Response;

public class ServiceProtocol implements ServiceConfiguration, CommonNetworkActionsHandler {

    public String getAPIURL() {
        return getContext().getString(R.string.api_url);
    }

    public Context getContext() {
        return TwitterFeedApp.getInstance();
    }

    public Request.Builder addHeaders(Request.Builder requestBuilder) {
        String bearerToken = String.format("Bearer %s", getContext().getString(R.string.auth_token));
        requestBuilder.addHeader("Authorization", bearerToken);
        return requestBuilder;
    }

    @Override
    public void onUnAuthorized(ServiceCall serviceCall, String errorMessage, int code, View contentView) {

        showSnackbar(contentView, R.string.error_unauthorized);
    }

    @Override
    public void onInternetConnectionError(ServiceCall serviceCall, View contentView) {

        showSnackbar(contentView, R.string.error_internet_connection);
    }

    private void showSnackbar(final View contentView, @StringRes int message) {
        if (contentView == null) {
            return;
        }

        try {
            final Snackbar snackbar = Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT);
            snackbar.show ();
            contentView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick (View v) {

                    snackbar.dismiss ();
                    contentView.setOnClickListener (null);
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace ();
        }
    }

    @Override
    public String getErrorMessage(Response response, int code) {
        try {

            return response.errorBody ().string ();
        }
        catch (IOException ex) {
            return "";
        }
    }

    @Override
    public int getStatusCode(Response response) {

        if(response == null) {
            return -1;
        }
        return response.code ();
    }

    @Override
    public boolean isSuccessful(Response response) {
        return response != null && response.errorBody () == null;
    }

    @Override
    public boolean isAuthorized(int code) {
        return code != 401;
    }

    @Override
    public int getConnectionTimeoutInSeconds() {
        return 10;
    }

    @Override
    public int getReadTimeoutInSeconds() {
        return 10;
    }

    @Override
    public int getWriteTimeoutInSeconds() {
        return 10;
    }

    @Override
    public boolean shouldFetchMockData() {
        return BuildConfig.IS_OFFLINE;
    }

    @Override
    public String getMockData(String path) {
        return readFromAssets("user_timeline.json");
    }

    private String readFromAssets(String fileName){
        AssetManager mgr = getContext().getAssets();

        try {
            System.out.println("filename : " + fileName);
            InputStream in = mgr.open(fileName, AssetManager.ACCESS_BUFFER);
            String json = streamToString(in);
            in.close();
            return json;

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String streamToString(InputStream in) throws IOException {
        if(in == null) {
            return "";
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception ex) {

        }
        return writer.toString();
    }
}
