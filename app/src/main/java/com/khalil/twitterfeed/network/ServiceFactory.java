package com.khalil.twitterfeed.network;


import com.khalil.twitterfeed.network.services.TimelineService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 5/16/17.
 */

public class ServiceFactory {

    protected final Map<Class<?>, Object> instances = new ConcurrentHashMap<>();
    private RetrofitAdapter retrofitAdapter;

    public ServiceFactory(ServiceProtocol serviceProtocol) {
        if(retrofitAdapter != null) {
            return;
        }
        retrofitAdapter = new RetrofitAdapter(serviceProtocol);
    }

    public TimelineService getTimeLineService() {
        return loadService(TimelineService.class);
    }


    private <S> S loadService(Class<S> serviceClass) {
        if (!instances.containsKey(serviceClass)) {
            instances.put(serviceClass, retrofitAdapter.loadService(serviceClass));
        }
        return (S) instances.get(serviceClass);
    }
}
