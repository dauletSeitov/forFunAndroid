package kz.lounge.myapplication.repository;

import kz.lounge.myapplication.model.Page;
import kz.lounge.myapplication.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostRepository {

    @GET("/api/post")
    Call<Page<Post>> getAllPhotos(@Query("pageType") String pageType);
}