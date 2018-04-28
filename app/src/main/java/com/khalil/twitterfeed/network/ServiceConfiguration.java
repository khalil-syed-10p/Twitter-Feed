package com.khalil.twitterfeed.network;

import retrofit2.Response;

public interface ServiceConfiguration {

    String getErrorMessage (Response response, int code);

    int getStatusCode (Response response);

    boolean isSuccessful (Response response);

    boolean isAuthorized (int code);

    int getConnectionTimeoutInSeconds ();

    int getReadTimeoutInSeconds ();

    int getWriteTimeoutInSeconds ();

    boolean shouldFetchMockData();

    String getMockData(String path);
}
