package cn.liuzehao.definedview.animproperty;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by liuzehao on 2019-11-29.
 */
public class BallImageView extends AppCompatImageView {

    public BallImageView(Context context) {
        super(context, null);
    }

    public BallImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        ValueAnimator animator = ValueAnimator.ofObject(new FalllingBallEvaluator(),
                new Point(0, 0), new Point(500, 500));
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point mCurP = (Point) animation.getAnimatedValue();
                layout(mCurP.x, mCurP.y, mCurP.x + getWidth(), mCurP.y + getHeight());
            }
        });
        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        animation.setDuration(5000);
        startAnimation(animation);
        animator.start();
    }


  static class FalllingBallEvaluator implements TypeEvaluator<Point>{
        private Point mPoint = new Point();
        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            mPoint.x = (int)(startValue.x + fraction * (endValue.x - startValue.x));
            if(fraction * 2 <= 1){
                mPoint.y = (int)(startValue.y + fraction * 2 * (endValue.y - startValue.y));
            }else{
                mPoint.y = endValue.y;
            }
            return mPoint;
        }
    }
}
