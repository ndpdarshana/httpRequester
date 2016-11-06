package com.ndp.httpRequester.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by darshana on 9/2/16.
 */
public class HttpRequester {
    private HttpClient httpClient;

    public HttpRequester(){
        this.httpClient = HttpClients.createDefault();
    }

    private HttpResponse execute(HttpUriRequest request) throws Exception{
        return httpClient.execute(request);
    }

    public HttpResponse get(String uri)throws Exception{
        HttpUriRequest request = RequestBuilder.get()
                .setUri(uri).build();
        return execute(request);
    }
}
