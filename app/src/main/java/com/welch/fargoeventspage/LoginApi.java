package com.welch.fargoeventspage;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface LoginApi {
    @POST("login")
    Call<Token> getToken(@Query("Username") String Username,
                         @Query("Password") String Password);
}
