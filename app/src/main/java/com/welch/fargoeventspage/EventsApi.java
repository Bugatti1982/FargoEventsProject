package com.welch.fargoeventspage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface EventsApi {
    @GET("events")
    Call<List<Event>> getEvents(@Header("Authorization") String token);
}
