package cn.liuzehao.definedview.animproperty;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by liuzehao on 2019-12-03.
 */
public class FallingBallImageView extends AppCompatImageView {

    public FallingBallImageView(Context context) {
        super(context, null);
    }

    public FallingBallImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     *这个set函数所对应的属性应该是fallingPos或者FallingPos
     *在该函数中，参数类型是Point对象，所以在构造ObjectAnimator时必须使用ofObject函数
     */
    public void setFallingPos(Point pos){
        layout(pos.x, pos.y, pos.x + getWidth(), pos.y + getHeight());
    }
}
