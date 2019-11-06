package cn.liuzehao.definedview.viewone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import cn.liuzehao.definedview.R;

/**
 * Created by liuzehao on 2019-11-05.
 */
public class BasicView extends View {
    private Paint mPaint;
    public BasicView(Context context) {
        super(context, null);
    }

    public BasicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        /**
         *Paint.Style.FILL:仅填充内部
         *Paint.Style.FILL_AND_STROKE:填充内部和描边
         *Paint.Style.STROKE:仅描边
         */
        mPaint.setStyle(Paint.Style.STROKE);
        //描边宽度：单位px
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         *画布背景设置：
         *drawColor(int color):参数color的取值必须是8位的0xAARRGGBB样式颜色值
         *drawARGB(int a, int r, int g, int b)：允许分别传入A、R、G、B分量，每个颜色值的取值
         *范围都是0~255(一般用十六进制表示)，内部会通过这些颜色分量构造出对应的颜色值
         */
        canvas.drawRGB(0xff, 0, 0xff);
        canvas.drawCircle(190, 200, 150, mPaint);//画圆
    }

    private void drawLine(Canvas canvas){
        /**
         *画线
         *startX:起点X坐标
         *startY:起点Y坐标
         *stopX：终点X坐标
         *stopY: 终点Y坐标
         */
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(50);
        canvas.drawLine(100, 100, 200, 200, mPaint);
    }

    private void drawPotin(Canvas canvas){
        //点大小与StrokeWidth有关，与style无关
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(50);
        canvas.drawPoint(100, 100, mPaint);
    }
}
/**
 *• G代表绿色值（ Green ），取值范围是0~255 (对应十六进制数 ox00~oxFF) 取值越
 * 小,绿色越少 。当取0时，表示绿色完全不可见；当取255时 ，绿色完全显示
 *• B表蓝色值 (Blue)，取值范围是0~255(对应十六进制数Ox00~xFF)，取值越小，
 * 蓝色越少。当取0时，表示蓝色完全不可见；当取255 时，蓝色完全显示。
 */