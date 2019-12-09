package cn.liuzehao.definedview.animproperty;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import cn.liuzehao.definedview.R;

/**
 * Created by liuzehao on 2019-12-06.
 */
public class MyMenuLayout extends RelativeLayout implements View.OnClickListener {

    private Button mMainItem, mItem1, mItem2, mItem3, mItem4, mItem5;
    private boolean mIsMenuOpen = false;
    private static final int RADIUS = 700;

    public MyMenuLayout(Context context) {
        super(context, null);
    }

    public MyMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.menu_show_layout, this);
        init(view);
    }

    private void init(View view){
        mMainItem = findViewById(R.id.main_menu);
        mItem1 = findViewById(R.id.menu_item_1);
        mItem2 = findViewById(R.id.menu_item_2);
        mItem3 = findViewById(R.id.menu_item_3);
        mItem4 = findViewById(R.id.menu_item_4);
        mItem5 = findViewById(R.id.menu_item_5);

        mMainItem.setOnClickListener(this);
        mItem1.setOnClickListener(this);
        mItem2.setOnClickListener(this);
        mItem3.setOnClickListener(this);
        mItem4.setOnClickListener(this);
        mItem5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_menu:
                if(mIsMenuOpen){
                    mIsMenuOpen = false;
                    closeMenu();
                }else{
                    mIsMenuOpen = true;
                    openMenu();
                }
                break;
            case R.id.menu_item_1:
                Toast.makeText(getContext(), "menu_item_1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_2:
                Toast.makeText(getContext(), "menu_item_2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_3:
                Toast.makeText(getContext(), "menu_item_3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_4:
                Toast.makeText(getContext(), "menu_item_4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_5:
                Toast.makeText(getContext(), "menu_item_5", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void closeMenu(){
        doAnimClose(mItem1, 0, 5, RADIUS);
        doAnimClose(mItem2, 1, 5, RADIUS);
        doAnimClose(mItem3, 2, 5, RADIUS);
        doAnimClose(mItem4, 3, 5, RADIUS);
        doAnimClose(mItem5, 4, 5, RADIUS);
    }

    private void openMenu(){
        doAnimOpen(mItem1, 0, 5, RADIUS);
        doAnimOpen(mItem2, 1, 5, RADIUS);
        doAnimOpen(mItem3, 2, 5, RADIUS);
        doAnimOpen(mItem4, 3, 5, RADIUS);
        doAnimOpen(mItem5, 4, 5, RADIUS);
    }

    private void doAnimOpen(View view, int index, int total, int radius){
        double degree = Math.toRadians(90) / (total -1) * index;
        int translateX = (int) - (radius * Math.sin(degree));
        int translateY = (int) - (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translateX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translateY),
                ObjectAnimator.ofFloat(view, "scaleX", 0, 1),
                ObjectAnimator.ofFloat(view, "scaleY", 0, 1),
                ObjectAnimator.ofFloat(view, "rotation", 0, 720),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1));
        set.setDuration(1000).start();
    }

    private void doAnimClose(View view, int index, int total, int radius){
        double degree = Math.toRadians(90) / (total -1) * index;
        int translateX = (int) - (radius * Math.sin(degree));
        int translateY = (int) - (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translateX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translateY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1, 0),
                ObjectAnimator.ofFloat(view, "scaleY", 1, 0),
                ObjectAnimator.ofFloat(view, "rotation", 0, 720),
                ObjectAnimator.ofFloat(view, "alpha", 1, 0));
        set.setDuration(1000).start();
    }
}
