package com.example.puzzlegram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class PostDetailActivity extends AppCompatActivity {

    Post post;

    private TextView tvUsername;
    private ImageView ivMainImage;
    private TextView tvDetailDescription;
    private ImageView ivUserImage;
    private TextView tvTimestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        post = Parcels.unwrap(getIntent().getParcelableExtra("post"));
        tvUsername = findViewById(R.id.tvUsername);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        ivMainImage = findViewById(R.id.ivMainImage);
        ivUserImage = findViewById(R.id.ivUserImage);
        tvTimestamp = findViewById(R.id.tvTimestamp);
        tvUsername.setText(post.getUser().getUsername());
        tvDetailDescription.setText(post.getDescription());
        tvTimestamp.setText(post.getCreatedAt().toString());
        Glide.with(this).load(post.getImage().getUrl())
                .into(ivMainImage);
        if(post.getUser().getParseFile("userImage") != null){
            Glide.with(this).load(post.getUser().getParseFile("userImage").getUrl().toString())
                    .override(200,200)
                    .fitCenter()
                    .transform(new CircleCrop())
                    .into(ivUserImage);
        }
        else{
            Glide.with(this).load(R.drawable.placeholder_user)
                    .transform(new CircleCrop())
                    .into(ivUserImage);
        }

    }
}