package com.khalil.twitterfeed.network.services;

import com.khalil.twitterfeed.entities.Tweet;
import com.khalil.twitterfeed.network.ServiceCall;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TimelineService {

    @GET("1.1/statuses/user_timeline.json")
    ServiceCall<List<Tweet>> userTimeline (@Query("screen_name") String screenName, @Query("count") int count);
}
