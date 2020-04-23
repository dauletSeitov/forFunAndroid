package kz.lounge.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import kz.lounge.myapplication.adapter.PostAdapter;
import kz.lounge.myapplication.config.RetrofitClientInstance;
import kz.lounge.myapplication.model.Page;
import kz.lounge.myapplication.model.Post;
import kz.lounge.myapplication.repository.PostRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private PostAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        PostRepository service = RetrofitClientInstance.getRetrofitInstance().create(PostRepository.class);
        Call<Page<Post>> call = service.getAllPhotos("FRESH");
        call.enqueue(new Callback<Page<Post>>() {
            @Override
            public void onResponse(Call<Page<Post>> call, Response<Page<Post>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<Page<Post>> call, Throwable t) {
                Log.e("ok", t.getMessage(), t);
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void generateDataList(Page<Post> photoList) {
        recyclerView = findViewById(R.id.rvContacts);
        adapter = new PostAdapter(this, photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}

