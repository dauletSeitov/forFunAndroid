package kz.lounge.myapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import kz.lounge.myapplication.R;
import kz.lounge.myapplication.config.RetrofitClientInstance;
import kz.lounge.myapplication.model.Page;
import kz.lounge.myapplication.model.Post;
import kz.lounge.myapplication.repository.PostRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<Post> dataList;
    private Context context;
    private PostRepository postRepository;

    public PostAdapter(Context context, Page<Post> dataList) {
        this.context = context;
        this.dataList = dataList.getContent();
        this.postRepository = RetrofitClientInstance.getRetrofitInstance(context).create(PostRepository.class);
    }


    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_contact, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, final int position) {

        final Post post = dataList.get(position);

        holder.getPostTitle().setText(post.getTitle());
        holder.getRating().setText("" + post.getRating());
        holder.getCommentCount().setText("" + post.getCommentCount());


        holder.getImageUp().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Call<String> stringCall = postRepository.changePostRating(post.getId(), false);

                stringCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        long reting = Long.parseLong(holder.getRating().getText().toString());

                        holder.getRating().setText("" + (reting + 1));
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        holder.getImageDown().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Call<String> stringCall = postRepository.changePostRating(post.getId(), true);
                stringCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        long rating = Long.parseLong(holder.getRating().getText().toString());

                        holder.getRating().setText("" + (rating - 1));
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        Long commentCount = dataList.get(position).getCommentCount();
        holder.getCommentCount().setText(commentCount == null ? "0" : commentCount.toString());


        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getImageUrl())
                .placeholder((R.drawable.image_placeholder))
                .error(R.drawable.broken_image_placeholder)
                .into(holder.getPostImage());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}

class PostViewHolder extends RecyclerView.ViewHolder {


    private TextView postTitle;
    private ImageView postImage;
    private TextView rating;
    private TextView commentCount;
    private ImageView imageUp;
    private ImageView imageDown;

    PostViewHolder(View view) {
        super(view);
        postTitle = super.itemView.findViewById(R.id.postTitle);
        postImage = super.itemView.findViewById(R.id.postImage);

        rating = super.itemView.findViewById(R.id.rating);
        commentCount = super.itemView.findViewById(R.id.commentCount);
        imageUp = super.itemView.findViewById(R.id.imageUp);
        imageDown = super.itemView.findViewById(R.id.imageDown);
    }

    public TextView getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(TextView postTitle) {
        this.postTitle = postTitle;
    }

    public ImageView getPostImage() {
        return postImage;
    }

    public void setPostImage(ImageView postImage) {
        this.postImage = postImage;
    }

    public TextView getRating() {
        return rating;
    }

    public void setRating(TextView rating) {
        this.rating = rating;
    }

    public TextView getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(TextView commentCount) {
        this.commentCount = commentCount;
    }


    public ImageView getImageUp() {
        return imageUp;
    }

    public void setImageUp(ImageView imageUp) {
        this.imageUp = imageUp;
    }

    public ImageView getImageDown() {
        return imageDown;
    }

    public void setImageDown(ImageView imageDown) {
        this.imageDown = imageDown;
    }
}
