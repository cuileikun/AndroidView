package com.gitstudy.photoview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.gitstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoViewActivity extends AppCompatActivity {
    @BindView(R.id.photoview)
    PhotoView photoView;
    private String url = "http://img2.3lian.com/2014/f2/37/d/40.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);
//        Glide.with(PhotoViewActivity.this).load(url).into(photoView);
        Glide.with(PhotoViewActivity.this).load(url).placeholder(R.drawable.ic_default_adimage).into(photoView);
    }
}
