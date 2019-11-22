package cn.liuzehao.definedview.animinterpolator;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.liuzehao.definedview.R;

/**
 * Created by liuzehao on 2019-11-20.
 */
public class InterpolatorActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mStart;
    private TextView mAnimTv2;
    private ImageView mAnimImg1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_interpolator);
        mAnimImg1 = findViewById(R.id.anim_iv1);
        mAnimTv2 = findViewById(R.id.anim_tv2);
        mStart = findViewById(R.id.anim_start);
        mStart.setOnClickListener(this);

    }
    /**
     *AccelerateDecelerateInterpolator是加速减速插值器，表示在开始与结束的地方速率改变
     *比较慢，在中间的时候加速，效果是先加速后减速；
     *AnticpateOvershootInterpolator是AnticipateInterpolator和OvershootInterpolator的合体，即
     *表示动画开始时反方向偏移一段距离，动画结束时再向后偏移一段距离
     */
    private void startAnim1(){
        Animation accTran = new TranslateAnimation(0,0, 0, 500);
        accTran.setFillAfter(true);
        accTran.setDuration(2000);
        accTran.setInterpolator(new AnticipateOvershootInterpolator(2));//张力值默认1.5
        mAnimTv2.startAnimation(accTran);
    }

    /**
     *AccelerateInterpolator是加速插值器，表示在动画开始的地方速率改变比较慢，然后开始加速
     *OvershootInterpolator是结束偏移插值器，表示在动画结束时，沿动画方向继续运动一段距离后再结束动画,
     *偏移结束后会再回到动画原本结束的位置
     */
    private void startAnim2(){
        Animation accRotate = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        accRotate.setDuration(2000);
        accRotate.setFillAfter(true);
        accRotate.setInterpolator(new OvershootInterpolator(3));//张力值和下面的类似
        mAnimTv2.startAnimation(accRotate);
    }

    /**
     *DecelerateInterpolator是减速插值器，表示在动画开始的一瞬间加速到最大值，然后逐渐变慢
     *LinearInterpolator是线性插值器，也称匀速加速器，速率是恒定的
     *BounceInterpolator是弹跳插值器，模拟了控件自由落地后回弹的效果
     *AnticpateInterpolator是初始偏移插值器，表示动画在开始后，会往动画的反方向移动一段距离，再应用动画
     */
    private void startAnim3(){
        Animation accTran = new TranslateAnimation(0,500, 0, 0);
        accTran.setFillAfter(true);
        accTran.setDuration(2000);
        accTran.setInterpolator(new AnticipateInterpolator(3));//tension表示张力，默认是2，值越大，一开始的偏移量就越大
        mAnimTv2.startAnimation(accTran);
    }

    /**暂时不理解，之后再看
     *CycleInterpolator是循环插值器，表示动画循环播放特定的次数，速率沿正弦曲线改变
     */
    private void startAnim4(){
        Animation cycleScale = new ScaleAnimation(1.0f, 3.0f, 1.0f, 3.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        cycleScale.setDuration(8000);
        cycleScale.setInterpolator(new CycleInterpolator(1));//cycles表示循环次数
        mAnimTv2.startAnimation(cycleScale);
    }

    private void startAnim5(){
        Animation boundScale = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        boundScale.setFillAfter(true);
        boundScale.setInterpolator(new BounceInterpolator());
        boundScale.setDuration(6000);
        mAnimImg1.startAnimation(boundScale);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.anim_start:
                //startAnim1();
                //startAnim2();
                //startAnim3();
                //startAnim4();
                startAnim5();
                break;
        }

    }
}
