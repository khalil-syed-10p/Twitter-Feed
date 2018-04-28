package com.khalil.twitterfeed.listeners;

import com.khalil.twitterfeed.entities.Tweet;

public interface TweetAdapterListener {

    void aboutToReachEndOfTheList(int position);
    void onItemClicked(Tweet tweet);
}
