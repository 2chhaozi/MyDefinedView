package cn.liuzehao.definedview.animproperty;

import android.animation.TimeInterpolator;

/**
 * Created by liuzehao on 2019-11-27.
 */
public class MyInterpolator implements TimeInterpolator {
    private static final String TAG = "liuzehao";
    @Override
    public float getInterpolation(float input) {
        return 1 - input;
    }
}
