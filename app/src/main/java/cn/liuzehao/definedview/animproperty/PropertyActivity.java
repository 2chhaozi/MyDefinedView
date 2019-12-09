package cn.liuzehao.definedview.animproperty;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.liuzehao.definedview.R;

/**
 * Created by liuzehao on 2019-11-26.
 */
public class PropertyActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "liuzehao";
    private TextView mAnimTv1;
    private LetterTextView mAnimTv2;
    private FallingBallImageView mBallIv1;
    private Button mStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_property);
        mAnimTv1 = findViewById(R.id.pro_anim_tv1);
        mAnimTv2 = findViewById(R.id.pro_letter_tv);
        mBallIv1 = findViewById(R.id.obj_ball_iv);
        mStart = findViewById(R.id.pro_start_anim);
        mStart.setOnClickListener(this);
    }

    /**
     *ValueAnimator只负责对指定值区间进行动画运算；
     *同时需要对运算过程监听，然后对控件执行动画操作
     */
    private void startAnim1(){
        /*final ValueAnimator anim1 = ValueAnimator.ofInt(0, 400);
        anim1.setDuration(1000);
        anim1.setStartDelay(500);//延时多久开始动画
        anim1.setRepeatCount(ValueAnimator.INFINITE);
        anim1.setRepeatMode(ValueAnimator.RESTART);//RESTART表示正序重新开始；REVERSE表示倒序重新开始*/

        ValueAnimator anim1 = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.value_animator);

        anim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (Integer)animation.getAnimatedValue();//获取动画在运动时当前运动点的值
                mAnimTv1.layout(curValue, curValue, curValue + mAnimTv1.getWidth(), curValue + mAnimTv1.getHeight());
            }
        });
        anim1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e(TAG, "状态监听：Start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e(TAG, "状态监听：End");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e(TAG, "状态监听：Cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e(TAG, "状态监听：Repeat");
            }
        });
        anim1.start();
    }

    /**
     *ObjectAnimator能使用ValueAnimator的任何函数
     *参数2是属性名
     */
    private void startAnim2(){
        //ObjectAnimator animator = ObjectAnimator.ofFloat(mAnimTv1, "alpha", 1, 0, 1);

        /**
         *rotation表示沿着Z轴旋转
         */
        //ObjectAnimator animator = ObjectAnimator.ofFloat(mAnimTv1, "rotationY", 0, 360, 180);

        /**
         *每次移动距离的计算都是以原点为中心
         */
        //ObjectAnimator animator = ObjectAnimator.ofFloat(mAnimTv1, "translationY", 0, 200, -100, 0);

        /**
         *将TextView在Y轴上进行缩放，从原来的高度的0倍放大到3倍，在缩小到1倍
         */
        ObjectAnimator animator = ObjectAnimator.ofFloat(mAnimTv1, "scaleY", 0F, 3F, 1F);
        animator.setDuration(5000);
        animator.start();
    }

    /**
     *自定义属性
     */
    private void startAnim3(){
        ObjectAnimator animator = ObjectAnimator.ofObject(mBallIv1, "fallingPos",
                new BallImageView.FalllingBallEvaluator(), new Point(0, 0), new Point(500, 500));
        animator.setDuration(2000);
        animator.start();
    }

    /**
     *playSequentially()表示动画组合依次播放
     *playTogether()表示动画组合同时开始激活，至于激活之后的组合中的各个动画的运行时间由他们自己决定
     */
    private void startAnim4(){
        ObjectAnimator animator1 = ObjectAnimator.ofInt(mAnimTv1, "BackgroundColor",
                0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mAnimTv1, "translationY", 0, 300, 0);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mAnimTv2, "translationY", 0, 400, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator1, animator2, animator3);
        //animatorSet.playTogether(animator1, animator2, animator3);
        animatorSet.setDuration(3000);
        animatorSet.start();
    }

    /**
     *Animator.Builder:
     * play(xxx)表示播放哪个动画
     * with(xxx)表示和前面的动画一起执行
     * before(xxx)表示先执行这个动画，再执行前面的动画
     * after(Animator anim)表示在执行前面的动画后才执行该动画
     * after(long delay)表示延迟N毫秒之后执行动画
     *
     * 都是以play()中当前所播放的动画为基准
     *
     * setTarget(xxx)表示动画都以这个控件为准，组合里面的动画都会作用在该目标控件上，
     * 原先每个动画里面的控件将失效
     */
    private void startAnim5(){
        ObjectAnimator animator1 = ObjectAnimator.ofInt(mAnimTv1, "BackgroundColor",
                0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mAnimTv1, "translationY", 0, 300, 0);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mAnimTv2, "translationY", 0, 400, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator1).with(animator2).after(animator3);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.setDuration(5000);
        animatorSet.start();
    }

    /**
     *路径动画
     */
    private void startAnim6(){

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.pro_start_anim){
            //startAnim1();
            //startAnim2();
            //startAnim3();
            startAnim4();
        }
    }
}
