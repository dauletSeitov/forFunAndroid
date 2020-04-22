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

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private List<Post> dataList;
    private Context context;

    public CustomAdapter(Context context, Page<Post> dataList){
        this.context = context;
        this.dataList = dataList.getContent();
    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_contact, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.getTxtTitle().setText(dataList.get(position).getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getImageUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.getCoverImage());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}

class CustomViewHolder extends RecyclerView.ViewHolder {


    private TextView txtTitle;
    private ImageView coverImage;

    CustomViewHolder(View itemView) {
        super(itemView);
        txtTitle = super.itemView.findViewById(R.id.textView2);
        coverImage = super.itemView.findViewById(R.id.imageView2);
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(TextView txtTitle) {
        this.txtTitle = txtTitle;
    }

    public ImageView getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(ImageView coverImage) {
        this.coverImage = coverImage;
    }
}
