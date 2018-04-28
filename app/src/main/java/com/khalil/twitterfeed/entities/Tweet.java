package com.khalil.twitterfeed.entities;

import android.text.TextUtils;
import android.text.format.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class Tweet {

    private String text;
    private User user;
    private Date createdAt;

    public String getText() {
        return TextUtils.isEmpty(text) ? "" : text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user == null ? new User() : user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CharSequence getTime() {
        if(createdAt == null) {
            return "N/A";
        }
        try {
            String prettyDate = DateUtils.getRelativeTimeSpanString(createdAt.getTime(),
                    Calendar.getInstance().getTimeInMillis(),
                    DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.FORMAT_ABBREV_RELATIVE).toString();

            String[] dateComponents = prettyDate.split(" ");

            return String.format("%s %s", dateComponents[0], dateComponents[1]);
        } catch (Exception ex) {
            return "N/A";
        }
    }

    @Override
    public String toString() {
        return getText();
    }
}
