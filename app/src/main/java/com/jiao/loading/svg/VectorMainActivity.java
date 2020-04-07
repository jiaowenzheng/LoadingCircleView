package com.jiao.loading.svg;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jiao.loading.R;

public class VectorMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1;
    private Button mButton2;
    private ImageView playIv1;
    private ImageView playIv2;

    private boolean isPlay1;
    private boolean isPlay2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vector_main);

        ImageView imageView = findViewById(R.id.vector_image_view);

        mButton1 = findViewById(R.id.button_change_1);
        mButton2 = findViewById(R.id.button_change_2);

        playIv1 = findViewById(R.id.play_1);
        playIv2 = findViewById(R.id.play_2);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_change_1:
                changeButton1();
                break;
            case R.id.button_change_2:
                changeButton2();
                break;
        }
    }

    private void changeButton1(){
        playIv1.setImageResource(isPlay1 ? R.drawable.animated_vector_pause_to_play_1 :
                R.drawable.animated_vector_play_to_pause_1);

        Drawable drawable = playIv1.getDrawable();

        if (drawable instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
            animatedVectorDrawable.start();
        }

        isPlay1= !isPlay1;
    }

    private void changeButton2(){
        playIv2.setImageResource(isPlay2 ? R.drawable.animated_vector_pause_to_play_2 :
                R.drawable.animated_vector_play_to_pause_2);

        Drawable drawable = playIv2.getDrawable();

        if (drawable instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
            animatedVectorDrawable.start();
        }

        isPlay2= !isPlay2;
    }
}
