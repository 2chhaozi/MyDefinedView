package cn.liuzehao.definedview.animinterpolator;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.liuzehao.definedview.R;

/**
 * Created by liuzehao on 2019-11-21.
 */
public class ScanDemoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mScan;
    private ImageView mCircle1;
    private ImageView mCircle2;
    private ImageView mCircle3;
    private ImageView mCircle4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_scan);

        mCircle1 = findViewById(R.id.circle1);
        mCircle2 = findViewById(R.id.circle2);
        mCircle3 = findViewById(R.id.circle3);
        mCircle4 = findViewById(R.id.circle4);
        mScan = findViewById(R.id.start_scan);
        mScan.setOnClickListener(this);

    }

    private void startAnim(){
        Animation animation1 = AnimationUtils.loadAnimation(ScanDemoActivity.this, R.anim.sacle_alpha_anim);
        Animation animation2 = AnimationUtils.loadAnimation(ScanDemoActivity.this, R.anim.sacle_alpha_anim);
        Animation animation3 = AnimationUtils.loadAnimation(ScanDemoActivity.this, R.anim.sacle_alpha_anim);
        Animation animation4 = AnimationUtils.loadAnimation(ScanDemoActivity.this, R.anim.sacle_alpha_anim);
        mCircle1.startAnimation(animation1);
        animation2.setStartOffset(600);
        mCircle2.startAnimation(animation2);
        animation3.setStartOffset(1200);
        mCircle3.startAnimation(animation3);
        animation4.setStartOffset(1800);
        mCircle4.startAnimation(animation4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_scan:
                startAnim();
                break;
        }
    }
}
