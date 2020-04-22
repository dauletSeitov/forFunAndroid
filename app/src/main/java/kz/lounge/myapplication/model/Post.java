package kz.lounge.myapplication.model;


import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Post {



    //@SerializedName("albumId")
    private Long id;
    private User user;
    private String title;
    private String tag;
    private String imageUrl;

//    private LocalDateTime updated;
    private String category;
    private Long rating;
    private Long commentCount;
    private Boolean isUpVoted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

//    public LocalDateTime getUpdated() {
//        return updated;
//    }
//
//    public void setUpdated(LocalDateTime updated) {
//        this.updated = updated;
//    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Boolean getUpVoted() {
        return isUpVoted;
    }

    public void setUpVoted(Boolean upVoted) {
        isUpVoted = upVoted;
    }
}
