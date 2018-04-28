package com.khalil.twitterfeed.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khalil.twitterfeed.R;
import com.khalil.twitterfeed.entities.Tweet;
import com.khalil.twitterfeed.listeners.TweetAdapterListener;
import com.khalil.twitterfeed.viewholders.TweetViewHolder;

import java.util.List;

/**
 * Created on 5/17/17.
 */

public class TweetsRecyclerAdapter extends RecyclerView.Adapter<TweetViewHolder> {

    private List<Tweet> tweetList;
    private final TweetAdapterListener tweetAdapterListener;

    private static final int END_THRESHOLD = 3;

    public TweetsRecyclerAdapter(List<Tweet> tweetList, TweetAdapterListener tweetAdapterListener) {
        this.tweetList = tweetList;
        this.tweetAdapterListener = tweetAdapterListener;
    }

    public TweetsRecyclerAdapter(TweetAdapterListener tweetAdapterListener) {
        this.tweetAdapterListener = tweetAdapterListener;
    }

    public void setMovieEntities(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    public void updateData(List<Tweet> tweetList) {
        this.tweetList = tweetList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TweetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_twitter_feed, parent, false);
        return new TweetViewHolder(view, tweetAdapterListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetViewHolder holder, int position) {
        if((tweetList == null)
                || tweetList.isEmpty()) {
            return;
        }
        Log.i("RECYCLER","position : " + position);
        if((tweetAdapterListener != null) && ((tweetList.size() - position) == END_THRESHOLD)) {
            tweetAdapterListener.aboutToReachEndOfTheList(position);
        }
        holder.setTweet(tweetList.get(position));
    }

    @Override
    public int getItemCount() {
        return (tweetList != null) ? tweetList.size() : 0;
    }
}
