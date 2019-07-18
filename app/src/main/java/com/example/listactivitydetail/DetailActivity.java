package com.example.listactivitydetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;


public class DetailActivity extends AppCompatActivity {



    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_CONTENT = "extra_content";
    public static final String EXTRA_PHOTO = "extra_photo";
    public static final String EXTRA_PRICE = "extra_price";
    public static final String EXTRA_ADDRESS ="extra_address";

    ImageView photoDetail;
    TextView tvName, tvContent, tvPrice, tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.tv_detail_name);
        tvContent = findViewById(R.id.tv_detail_content);
        tvPrice = findViewById(R.id.tv_detail_price);
        tvAddress = findViewById(R.id.tv_detail_address);
        photoDetail = findViewById(R.id.item_photo_detail);

        String name = getIntent().getStringExtra(EXTRA_NAME);
        String content = getIntent().getStringExtra(EXTRA_CONTENT);
        String price = "Rp." + getIntent().getStringExtra(EXTRA_PRICE);
        String address = getIntent().getStringExtra(EXTRA_ADDRESS);

        RequestManager requestManager = Glide.with(this);
        RequestBuilder requestBuilder = requestManager.load(getIntent().getStringExtra(EXTRA_PHOTO));
        requestBuilder.into(photoDetail);


        tvName.setText(name);
        tvContent.setText(content);
        tvPrice.setText(price);
        tvAddress.setText(address);
    }
}
