package kz.lounge.myapplication.repository;

import java.util.Map;

import kz.lounge.myapplication.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserRepository {

    @Headers({"no-Authentication:true"})
    @POST("/api/auth/login")
    Call<Map<String, String>> autoidentificate(@Body User user);
}