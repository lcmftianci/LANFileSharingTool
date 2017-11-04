package com.example.shinelon.flowerviewpager.Splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.example.shinelon.flowerviewpager.MsgSendGet.MsgActivity;
import com.example.shinelon.flowerviewpager.R;
import java.util.Random;

/**
 * Created by Shinelon on 2017/11/4.
 */

public class SplashActivity extends Activity {

    /**
     * 三个切换的动画
     */
    private Animation mFadeIn;// 渐显动画
    private Animation mFadeInScale;// 放大动画
    private Animation mFadeOut;// 渐隐动画

    //@InjectView(R.id.image)
    ImageView mImageView;

    public static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        mImageView = (ImageView)findViewById(R.id.image);
        int index = new Random().nextInt(2);
        if (index == 1) {
            mImageView.setImageResource(R.drawable.entrance3);
        } else {
            mImageView.setImageResource(R.drawable.entrance2);
        }
        initAnim();
        setListener();
    }

    /**
     * 初始化 动画效果
     */
    private void initAnim() {
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in);
        mFadeIn.setDuration(500);
        mFadeInScale = AnimationUtils.loadAnimation(this,
                R.anim.welcome_fade_in_scale);
        mFadeInScale.setDuration(2000);
        mFadeOut = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_out);
        mFadeOut.setDuration(500);
        mImageView.startAnimation(mFadeIn);
    }

    /**
     * 设置动画效果 监听事件
     */
    public void setListener() {
        /**
         * 动画切换原理:开始时是用第一个渐现动画,当第一个动画结束时开始第二个放大动画,当第二个动画结束时调用第三个渐隐动画,
         * 第三个动画结束时修改显示的内容并且重新调用第一个动画,从而达到循环效果
         */
        mFadeIn.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                mImageView.startAnimation(mFadeInScale);
            }
        });
        mFadeInScale.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                //跳转Activity
                startActivity(new Intent(SplashActivity.this, MsgActivity.class));
                finish();
                // mImageView.startAnimation(mFadeOut);
            }
        });
        mFadeOut.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                // startActivity(MainActivity.class);
            }
        });
    }

}
