package com.jiao.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RoundView yellowRoundView1;
    private RoundView redRoundView2;
    private RoundView greenRoundView3;

    private int distance;
    private int dp;
    private int circleWidth;
    private int minTranslationX;
    private int maxTranslationX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yellowRoundView1 = findViewById(R.id.round_1);
        redRoundView2 = findViewById(R.id.round_2);
        greenRoundView3 = findViewById(R.id.round_3);

        yellowRoundView1.setPaintColor(R.color.color_FFB03F);
        redRoundView2.setPaintColor(R.color.color_ED4A42);
        greenRoundView3.setPaintColor(R.color.color_ACD120);

        dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,getResources().getDisplayMetrics());
        circleWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15,getResources().getDisplayMetrics());
        distance = dp * 2 + circleWidth * 3;

        minTranslationX = dp + circleWidth;
        maxTranslationX = distance - circleWidth;

        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Matrix matrix = new Matrix();
//                matrix.setScale(40,40);
//                translation();
//                translation2();
                translation3();
            }
        });

//        roundView1.setPaintColor(R.color.color_ACD120);
//        roundView1.setPaintColor(R.color.color_FFB03F);
//        roundView1.setPaintColor(R.color.color_ED4A42);
    }

    private void translation2(){
        /**对象的不同属性组合**/
        PropertyValuesHolder objectAnimatorTranslation = PropertyValuesHolder.ofFloat("translationX", 0, distance/2, distance, 0);
        /**同时操作对象的两个属性动画**/
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(yellowRoundView1, objectAnimatorTranslation);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(2000);
        objectAnimator.start();

    }

    private int index = 1;
    private float lastRedTranslationX;
    private float lastGreenTranslationX;
    private float lastYellowTranslationX;



    private void translation3(){

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f,1f);
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {}

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {
                lastRedTranslationX = redRoundView2.getTranslationX();
                lastGreenTranslationX = greenRoundView3.getTranslationX();
                lastYellowTranslationX = yellowRoundView1.getTranslationX();
                Log.d("jiao","onAnimationRepeat index "+index+" red X "+lastRedTranslationX+" greenX "+lastGreenTranslationX+" yellowX "+lastYellowTranslationX);

                if (index >= 3){
                    index = 0;
                }
                index ++;


            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = animation.getAnimatedFraction();

                BigDecimal bd = new BigDecimal(value);
                float fraction = bd.setScale(2, BigDecimal.ROUND_DOWN).floatValue();

                if (fraction == 0){
                    return;
                }

                float translationX = fraction * distance;
                Log.i("jiao","fraction "+fraction+" translationX "+translationX+" distance "+distance+" dp "+dp+" value "+value);

                if (index == 4){
                    float tx = translationX;
                    if (tx >= minTranslationX){
                        Log.i("jiao"," index "+index+" fraction "+fraction);
                        tx = minTranslationX;
                    }

                    yellowCircleTranslation(tx +lastYellowTranslationX);
                    redCircleTranslation(tx + lastRedTranslationX);

                    if (translationX >= maxTranslationX){
                        translationX = maxTranslationX;
                    }

                    greenCircleTranslation(-translationX + lastGreenTranslationX);
                } else if (index == 1){
                    float tx = translationX;
                    if (tx >= minTranslationX){
                        Log.i("jiao"," index "+index+" fraction "+fraction);
                        tx = minTranslationX;
                    }

                    yellowCircleTranslation(tx +lastYellowTranslationX);
                    redCircleTranslation(tx + lastRedTranslationX);

                    if (translationX >= maxTranslationX){
                        translationX = maxTranslationX;
                    }

                    greenCircleTranslation(-translationX + lastGreenTranslationX);
                } else if (index == 2){
                    float tx = translationX;
                    if (tx >= minTranslationX){
                        Log.i("jiao"," index "+index+" fraction "+fraction);
                        tx = minTranslationX;
                    }
                    greenCircleTranslation(tx + lastGreenTranslationX);
                    yellowCircleTranslation(tx + lastYellowTranslationX);

                    if (translationX >= maxTranslationX){
                        translationX = maxTranslationX;
                    }

                    redCircleTranslation(-translationX + lastRedTranslationX);

                } else if (index == 3){
                    float tx = translationX;
                    if (tx >= minTranslationX){
                        Log.i("jiao"," index "+index+" fraction "+fraction);
                        tx = minTranslationX;
                    }
                    redCircleTranslation(tx + lastRedTranslationX);
                    greenCircleTranslation(tx + lastGreenTranslationX);

                    if (translationX >= maxTranslationX){
                        translationX = maxTranslationX;
                    }
                    yellowCircleTranslation(-translationX + lastYellowTranslationX);
                }
            }
        });

        valueAnimator.setRepeatCount(-1);
        valueAnimator.setDuration(700);
        valueAnimator.start();
    }


    private void redCircleTranslation(float translation){
        redRoundView2.setTranslationX(translation);
    }

    private void greenCircleTranslation(float translation){
        greenRoundView3.setTranslationX(translation);
    }

    private void yellowCircleTranslation(float translation){
        yellowRoundView1.setTranslationX(translation);
    }

    private void translation(){
        List<Animator> animators = new ArrayList<>();

//        ObjectAnimator translationXRound1Anim = ObjectAnimator.ofFloat(roundView1, "translationX", 0f, distance/2, distance,0);
//        translationXRound1Anim.setDuration(2000);
//        translationXRound1Anim.setInterpolator(new AccelerateDecelerateInterpolator());
//        translationXRound1Anim.setRepeatCount(ValueAnimator.INFINITE);//无限循环
//        translationXRound1Anim.start();
//
//        ObjectAnimator translationXRound2Anim = ObjectAnimator.ofFloat(roundView2, "translationX", 0f, distance / 2, -distance / 2,0f);
//        translationXRound2Anim.setDuration(2000);
//        translationXRound2Anim.setInterpolator(new LinearInterpolator());
//        translationXRound2Anim.setRepeatCount(ValueAnimator.INFINITE);//无限循环
//        translationXRound2Anim.start();

        ObjectAnimator translationXRound3Anim = ObjectAnimator.ofFloat(greenRoundView3, "translationX", 0f, -distance, -distance/2,0f);
        translationXRound3Anim.setDuration(2000);
        translationXRound3Anim.setInterpolator(new LinearInterpolator());
        translationXRound3Anim.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        translationXRound3Anim.start();
////
////        animators.add(translationXRound3Anim);
//
////        AnimatorSet btnSexAnimatorSet = new AnimatorSet();
////        btnSexAnimatorSet.playTogether(animators);
////        btnSexAnimatorSet.setStartDelay(500);
////        btnSexAnimatorSet.start();
//
    }

    private void translationTwo(){
        ObjectAnimator translationXYellowAnim = ObjectAnimator.ofFloat(yellowRoundView1, "translationX", 0f, distance, 0f);
        translationXYellowAnim.setDuration(2000);
        translationXYellowAnim.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        translationXYellowAnim.setInterpolator(new LinearInterpolator());
        translationXYellowAnim.start();

        ObjectAnimator translationXGreenAnim = ObjectAnimator.ofFloat(greenRoundView3, "translationX", 0f, -distance, 0f);
        translationXGreenAnim.setDuration(2000);
        translationXGreenAnim.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        translationXGreenAnim.start();
    }


}
