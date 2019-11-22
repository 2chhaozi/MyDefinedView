package cn.liuzehao.definedview.animinterpolator;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.liuzehao.definedview.R;

/**
 * Created by liuzehao on 2019-11-13.
 */
public class AnimActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mStart;
    private TextView mAnimTv1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_base);
        init();
    }

    private void init(){
        mStart = findViewById(R.id.start);
        mAnimTv1 = findViewById(R.id.anim_tv1);

        mStart.setOnClickListener(this);
    }

    private void startAnim1(){
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.scaleanim);
        mAnimTv1.startAnimation(anim1);

        //代码动态实现动画
        ScaleAnimation scaleAnim = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(700);
        scaleAnim.setInterpolator(new LinearInterpolator());//设置插值器
        //mAnimTv1.startAnimation(scaleAnim);
    }

    private void startAnim2(){
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.rotate);
        mAnimTv1.startAnimation(anim2);
    }

    private void startAnim3(){
        Animation anim3 = AnimationUtils.loadAnimation(this, R.anim.animset);
        mAnimTv1.startAnimation(anim3);
    }

    //代码动态实现动画
    private void startAnim4(){
        Animation alpha = new AlphaAnimation(1.0f, 0.1f);
        Animation scale = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        Animation rotate = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        AnimationSet animSet = new AnimationSet(true);//true表示集合动画共用插值器，false表示各用各的
        animSet.addAnimation(alpha);
        animSet.addAnimation(scale);
        animSet.addAnimation(rotate);
        animSet.setDuration(5000);
        animSet.setFillAfter(true);
        mAnimTv1.startAnimation(animSet);
    }

    private void startAnim5(){
        final Animation scale = new ScaleAnimation(0.0f, 2.0f, 0.0f, 2.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        final Animation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(3000);
        rotate.setFillAfter(true);
        scale.setDuration(700);
        scale.setFillAfter(true);
        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mAnimTv1.startAnimation(rotate);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mAnimTv1.startAnimation(scale);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                //startAnim1();
                //startAnim2();
                //startAnim3();
                startAnim5();
                break;
        }
    }
}
