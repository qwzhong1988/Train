package cn.wycode.train.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import cn.wycode.train.BaseActivity;
import cn.wycode.train.R;

/**
 * Created by wangyu on 16/4/7.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.button)
    Button button;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.btn_test)
    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentViewWithDefaultTitle(R.layout.activity_main, "首页");
    }

    @Override
    protected void initView() {
        setOnClickListeners(this, button, imageView,btnTest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                openActivity(GameActivity.class);
                break;
            case R.id.imageView:
                Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.route_rotate);
                v.startAnimation(animation);
                break;
            case R.id.btn_test:
                openActivity(TestActivity.class);
                break;
        }
    }
}
