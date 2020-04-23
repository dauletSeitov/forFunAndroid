package kz.lounge.myapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import kz.lounge.myapplication.R;
import kz.lounge.myapplication.model.Page;
import kz.lounge.myapplication.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<Post> dataList;
    private Context context;

    public PostAdapter(Context context, Page<Post> dataList) {
        this.context = context;
        this.dataList = dataList.getContent();
    }


    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_contact, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.getPostTitle().setText(dataList.get(position).getTitle());
        holder.getRating().setText(""+dataList.get(position).getRating());

        if(dataList.get(position).getCommentCount() != null) {
            holder.getCommentCount().setText("" + dataList.get(position).getCommentCount().toString());
        }
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getImageUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
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

    PostViewHolder(View view) {
        super(view);
        postTitle = super.itemView.findViewById(R.id.postTitle);
        postImage = super.itemView.findViewById(R.id.postImage);

        rating = super.itemView.findViewById(R.id.rating);
        commentCount = super.itemView.findViewById(R.id.commentCount);
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
}
