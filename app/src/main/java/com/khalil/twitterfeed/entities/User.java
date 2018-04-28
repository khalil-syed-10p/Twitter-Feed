package com.khalil.twitterfeed.entities;

import android.text.TextUtils;

public class User {

    private String name;
    private String screenName;
    private String profileImageUrlHttps;

    public String getName() {
        return TextUtils.isEmpty(name) ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return TextUtils.isEmpty(screenName) ? "" : String.format("@%s",screenName);
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getProfileImageUrlHttps() {
        return profileImageUrlHttps;
    }

    public void setProfileImageUrlHttps(String profileImageUrlHttps) {
        this.profileImageUrlHttps = profileImageUrlHttps;
    }
}
