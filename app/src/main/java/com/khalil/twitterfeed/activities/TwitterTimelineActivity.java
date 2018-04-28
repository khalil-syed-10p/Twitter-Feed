package com.khalil.twitterfeed.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.khalil.twitterfeed.R;
import com.khalil.twitterfeed.activities.base.BaseActivity;
import com.khalil.twitterfeed.adapters.TweetsRecyclerAdapter;
import com.khalil.twitterfeed.entities.Tweet;
import com.khalil.twitterfeed.listeners.TweetAdapterListener;
import com.khalil.twitterfeed.network.ServiceCallback;

import java.util.List;

public class TwitterTimelineActivity extends BaseActivity implements TweetAdapterListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerTweets;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TweetsRecyclerAdapter tweetsRecyclerAdapter;
    private TextView txtNoData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_timeline);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerTweets = findViewById(R.id.recyclerTweets);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        txtNoData = findViewById(R.id.txtNoData);

        swipeRefreshLayout.setOnRefreshListener(this);
        tweetsRecyclerAdapter = new TweetsRecyclerAdapter(this);
        recyclerTweets.setLayoutManager(new LinearLayoutManager(this));
        recyclerTweets.setAdapter(tweetsRecyclerAdapter);
        recyclerTweets.addItemDecoration(new DividerItemDecoration(this, 1));
    }

    private void noDataAvailable() {
        recyclerTweets.setVisibility(View.GONE);
        txtNoData.setVisibility(View.VISIBLE);
    }

    private void dataAvailable() {
        recyclerTweets.setVisibility(View.VISIBLE);
        txtNoData.setVisibility(View.GONE);
    }

    private void clearScreen() {
//        recyclerTweets.setVisibility(View.GONE);
        txtNoData.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearScreen();
        loadTweets();
    }

    private void loadTweets() {
        String screenName = getIntent().getStringExtra(MainActivity.KEY_SCREEN_NAME);
        if(TextUtils.isEmpty(screenName)) {
            return;
        }
        getServiceFactory().getTimeLineService().
                userTimeline(screenName, 10).
                networkUI(this).
                enqueue(new ServiceCallback<List<Tweet>>() {

                    public void onSuccess(List<Tweet> tweetList, int code) {
                        Log.d("", tweetList.toString());
                        onDataReceived(tweetList);
                    }


                    public void onFailure(String errorMessage, int code) {
                        Log.d("", errorMessage);
                        Toast.makeText(TwitterTimelineActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void onDataReceived(List<Tweet> tweetList) {
        if(tweetList == null || tweetList.isEmpty()) {
            noDataAvailable();
            return;
        }
        tweetsRecyclerAdapter.updateData(tweetList);
        dataAvailable();
    }

    @Override
    public void aboutToReachEndOfTheList(int position) {

    }

    @Override
    public void onItemClicked(Tweet tweet) {

    }

    @Override
    public void onRefresh() {
        loadTweets();
    }

    @Override
    public void didFinishCall(boolean success) {
        super.didFinishCall(success);
        swipeRefreshLayout.setRefreshing(false);
        if(!success) {
            noDataAvailable();
        }
    }

    @Override
    public void willStartCall() {
        if(swipeRefreshLayout.isRefreshing()) {
            return;
        }
        super.willStartCall();
    }

    @Override
    public View getContentView() {
        return swipeRefreshLayout;
    }
}
