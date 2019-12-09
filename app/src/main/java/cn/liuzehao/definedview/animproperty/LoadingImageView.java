package cn.liuzehao.definedview.animproperty;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import cn.liuzehao.definedview.R;

/**
 * Created by liuzehao on 2019-11-27.
 * 表示加载中的控件，动画效果为上下跳动，同时跳动的时候显示不同图片
 */
public class LoadingImageView extends AppCompatImageView {
    private int mTop;
    private int mCurImgIndex = 0;
    private static final int IMG_COUNT = 3;

    public LoadingImageView(Context context) {
        super(context, null);
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mTop = top;
    }

    private void init(){
        //ValueAnimator animator = ValueAnimator.ofInt(0, 100, 0);
        ValueAnimator animator = ValueAnimator.ofInt(0xffffff00, 0xff0000ff, 0xffffff00);//定义颜色时必须包括A R G B 4个值
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(2000);
        animator.setEvaluator(new ArgbEvaluator());
        //animator.setEvaluator(new MyEvaluator());//设置数值进度转换器
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                setBackgroundColor(value);
                //setTop(mTop - value);//getTop和setTop函数所得到的的和设置的坐标都是相对父控件的坐标位置
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setBackgroundColor(0xffffff00);
                //setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

                /*mCurImgIndex++;
                switch (mCurImgIndex % IMG_COUNT){
                    case 0:
                        setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
                        break;
                    case 1:
                        setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
                        break;
                    case 2:
                        setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
                        break;
                        default:
                }*/
            }
        });
        animator.start();
    }
}
