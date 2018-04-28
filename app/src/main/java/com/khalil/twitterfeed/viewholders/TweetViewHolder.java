package com.khalil.twitterfeed.viewholders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.khalil.twitterfeed.R;
import com.khalil.twitterfeed.components.CircleImageView;
import com.khalil.twitterfeed.entities.Tweet;
import com.khalil.twitterfeed.listeners.TweetAdapterListener;

public class TweetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TweetAdapterListener tweetAdapterListener;
    private Tweet tweet;

    private TextView txtTweet;
    private TextView txtName;
    private TextView txtScreenName;
    private TextView txtTime;

    private CircleImageView imgProfile;

    public TweetViewHolder(View itemView, TweetAdapterListener tweetAdapterListener) {
        super(itemView);
        this.tweetAdapterListener = tweetAdapterListener;
        itemView.setOnClickListener(this);

        txtTweet = itemView.findViewById(R.id.txtTweet);
        txtName = itemView.findViewById(R.id.txtName);
        txtScreenName = itemView.findViewById(R.id.txtScreenName);
        txtTime = itemView.findViewById(R.id.txtTime);

        imgProfile = itemView.findViewById(R.id.imgProfile);
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
        Log.i("VIEW HOLDEr","tweet : " + tweet.toString());
        if(tweet == null) {
            return;
        }

        txtTweet.setText(tweet.getText());
        txtName.setText(tweet.getUser().getName());
        txtScreenName.setText(tweet.getUser().getScreenName());
        txtTime.setText(tweet.getTime());

//        imgProfile.setImageResource(R.drawable.twitter_egg_blue);
        imgProfile.imageLoader(tweet.getUser().getProfileImageUrlHttps())
                .placeholder(R.drawable.twitter_egg_blue)
                .errorPlaceHolder(R.drawable.twitter_egg_blue)
                .load();
    }

    @Override
    public void onClick(View view) {
        if(tweetAdapterListener == null) {
            return;
        }

        tweetAdapterListener.onItemClicked(tweet);
    }
}
