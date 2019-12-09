package cn.liuzehao.definedview.animproperty;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by liuzehao on 2019-11-28.
 */
public class LetterTextView extends AppCompatTextView {

    public LetterTextView(Context context) {
        super(context, null);
    }

    public LetterTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        //A~Z对应数字是65~90
        ValueAnimator animator = ValueAnimator.ofObject(new CharEvaluator(), new Character('A')
        , new Character('Z'));
        animator.setDuration(10000);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char text = (Character)animation.getAnimatedValue();
                setText(String.valueOf(text));
            }
        });
        animator.start();

    }

    class CharEvaluator implements TypeEvaluator<Character>{

        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startInt = startValue;
            int endint = endValue;
            int curInt = (int)(startInt + fraction * (endint -startInt));
            char result = (char) curInt;
            return result;
        }
    }
}
