package kz.lounge.myapplication.config;

import android.content.Context;
import android.content.Intent;

import java.io.IOException;

import kz.lounge.myapplication.FunSecurity;
import kz.lounge.myapplication.ui.login.LoginActivity;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://51.15.222.91:8080";

    public static Retrofit getRetrofitInstance(final Context context) {
        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    if (chain.request().header("no-Authentication") != null) {
                        return chain.proceed(chain.request());
                    }

                    if (!FunSecurity.isAuthenticated()) {
                        Intent i = new Intent(context, LoginActivity.class);
                        context.startActivity(i);
                    }
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + FunSecurity.token)
                            .build();
                    return chain.proceed(newRequest);


                }
            }).build();


            retrofit = new retrofit2.Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}