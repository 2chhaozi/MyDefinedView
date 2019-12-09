package cn.liuzehao.definedview.animproperty;

import android.animation.TypeEvaluator;

/**
 * Created by liuzehao on 2019-11-27.
 */

public class MyEvaluator implements TypeEvaluator<Integer> {

    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int)(200 + startInt + fraction * (endValue - startInt));
    }
}
