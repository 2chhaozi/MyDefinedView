package cn.liuzehao.definedview.animproperty;

import android.animation.TypeEvaluator;

/**
 * Created by liuzehao on 2019-11-28.
 *
 * 倒序输出动画进度
 */
public class ReverseEvaluator implements TypeEvaluator<Integer> {

    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int)(endValue - fraction * (endValue - startValue));
    }
}
