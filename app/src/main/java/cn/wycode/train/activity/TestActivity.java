package cn.wycode.train.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;

import butterknife.Bind;
import cn.wycode.train.BaseActivity;
import cn.wycode.train.R;
import cn.wycode.train.controller.TrainController;
import cn.wycode.train.entity.RouteImage;
import cn.wycode.train.utils.ToastUtil;

/**
 * 测试
 * Created by wangyu on 16/4/11.
 */
public class TestActivity extends BaseActivity implements TrainController.TrainEvent {

    @Bind(R.id.iv_test)
    ImageView ivTest;
    @Bind(R.id.iv_test_train)
    ImageView ivTestTrain;

    TrainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void initView() {


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        ivTestTrain.setTranslationY(ivTest.getHeight() / 2 - ivTestTrain.getHeight());

        controller = new TrainController(ivTestTrain, ivTest.getHeight());

        controller.setListener(this);

        controller.start();
    }

    @Override
    public void next() {
        controller.next(new RouteImage(1, 2));
    }

    @Override
    public void gameOver() {
        ToastUtil.showToastDefault(mContext,"游戏结束!");
    }
}
