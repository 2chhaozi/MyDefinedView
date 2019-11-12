package cn.liuzehao.definedview.basicview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
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
        Paint mPaint = generatePaint(Color.RED, Paint.Style.FILL_AND_STROKE, 50);
        canvas.drawLine(100, 100, 200, 200, mPaint);
    }

    private void drawPotin(Canvas canvas){
        //点大小与StrokeWidth有关，与style无关
        Paint mPaint = generatePaint(Color.BLACK, Paint.Style.FILL, 50);
        canvas.drawPoint(100, 100, mPaint);
    }

    private void drawRect(Canvas canvas){
        Paint mPaint = generatePaint(Color.RED, Paint.Style.STROKE, 10);
        //绘制int数据类型的矩形
        canvas.drawRect(10, 10, 100, 100, mPaint);
        //绘制float数据类型的矩形
        RectF rectF = new RectF(30, 30, 150, 150);
        canvas.drawRect(rectF, mPaint);
    }

    private void drawPath(Canvas canvas){
        Paint mPaint = generatePaint(Color.BLACK, Paint.Style.STROKE, 10);
        //绘制三角形
        Path path1 = new Path();
        path1.moveTo(10, 10);//起始点
        path1.lineTo(10, 100);//结束点，lineTo()可被多次调用，直到close()
        path1.lineTo(300, 100);
        path1.close();//闭环
        canvas.drawPath(path1, mPaint);

        Path path2 = new Path();
        path1.moveTo(10, 10);//设置绘制的起点
        RectF rectF = new RectF(100, 10, 200, 100);
        //startAngle:弧开始的角度，以X轴正方形为O度,forceMoveTo表示是否将弧的起始点作为绘制的起始位置
        path2.arcTo(rectF, 0, 90, true);
        canvas.drawPath(path2, mPaint);
    }

    private void drawRegion1(Canvas canvas){
        Paint mPaint = generatePaint(Color.BLACK, Paint.Style.FILL, 1);

        Path path1 = new Path();
        RectF rect  = new RectF(50, 50, 200, 500);
        path1.addOval(rect, Path.Direction.CCW);
        //根据路径绘制椭圆与正方形的交集区域
        Region reg = new Region();
        reg.setPath(path1, new Region(50, 50, 200, 200));
        canvas.drawPath(path1, mPaint);

    }

    /**
     * DIFFERENCE(O) //最终区域为 region1与region2不同的区域
     * INTERSECT(1) //最终区域为 region2与region2相交的区域
     * UNION(2) //最终区域为 region1与region2组合在一起的区
     * XOR(3) //最终区域为 region1与region2相交之外的区域
     * REVERSE_DIFFERENCE(4) //最终区域为region1与regonl2不同的区域
     * REPLACE(5); //最终区域为region2的区域
     */
    private void drawRegion2(Canvas canvas){
        Paint mPaint = generatePaint(Color.RED, Paint.Style.STROKE, 2);

        Rect rect1 = new Rect(100, 100, 400, 200);
        Rect rect2 = new Rect(200, 0, 300, 300);
        canvas.drawRect(rect1, mPaint);
        canvas.drawRect(rect2, mPaint);
        //取2个区域的交集
        Region region1 = new Region(rect1);
        Region region2 = new Region(rect2);
        region1.op(region2, Region.Op.INTERSECT);

        Paint paint_fill = generatePaint(Color.BLACK, Paint.Style.FILL, 1);
        //drawRegion(canvas, region1, paint_fill);

    }

    private void drawCanvas(Canvas canvas){
        //每次调用drawXXX绘制时都会产生一个全新的Canvas透明图层覆盖在屏幕上，不会随着之后Canvas的平移之类操作而改变
        Paint mPaint1 = generatePaint(Color.RED, Paint.Style.STROKE, 1);
        canvas.drawRect(0, 0, 200, 100, mPaint1);
        //画布平移,操作是不可逆的，之后的绘制都是在这操作基础上绘制的
        canvas.translate(100, 100);
        mPaint1 = generatePaint(Color.GREEN, Paint.Style.STROKE, 1);
        canvas.drawRect(0, 0, 200, 100, mPaint1);
    }

    //画布剪裁、保存、恢复
    private void drawClip(Canvas canvas){
        canvas.drawColor(Color.RED);
        canvas.save();//保存

        canvas.clipRect(100, 100, 800, 800);//剪裁
        canvas.drawColor(Color.GREEN);

        canvas.restore();//恢复整屏画布
        canvas.drawColor(Color.BLUE);
    }

    private Paint generatePaint(int color, Paint.Style style, int width){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(width);
        return paint;
    }

}
/**
 *• G代表绿色值（ Green ），取值范围是0~255 (对应十六进制数 ox00~oxFF) 取值越
 * 小,绿色越少 。当取0时，表示绿色完全不可见；当取255时 ，绿色完全显示
 *• B表蓝色值 (Blue)，取值范围是0~255(对应十六进制数Ox00~xFF)，取值越小，
 * 蓝色越少。当取0时，表示蓝色完全不可见；当取255 时，蓝色完全显示。
 */