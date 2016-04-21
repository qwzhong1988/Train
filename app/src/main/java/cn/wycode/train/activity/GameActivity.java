package cn.wycode.train.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.wycode.train.BaseActivity;
import cn.wycode.train.R;
import cn.wycode.train.adapter.RouteAdapter;
import cn.wycode.train.adapter.baseadapter.MyRecycleViewDecoration;
import cn.wycode.train.controller.TrainController;
import cn.wycode.train.entity.RouteImage;
import cn.wycode.train.entity.Train;
import cn.wycode.train.utils.DisplayUtils;
import cn.wycode.train.utils.ToastUtil;

/**
 * 游戏页面
 * Created by wangyu on 16/4/7.
 */
public class GameActivity extends BaseActivity implements TrainController.TrainEvent {

    @Bind(R.id.game_people)
    TextView gamePeople;
    @Bind(R.id.game_rcv)
    RecyclerView gameRcv;
    @Bind(R.id.iv_train)
    ImageView ivTrain;

    RouteAdapter adapter;


    List<RouteImage> routes = new ArrayList<>();

    private final int numOfColumn = 18;
    private final int numOfRow = 10;

    public static int mapWidth, mapHeight; //地图宽高
    public static int mapRectWidth, mapRectHeight; //地图块 宽高


    TrainController controller;

    Train train;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, numOfColumn);
        gameRcv.setLayoutManager(layoutManager);
        MyRecycleViewDecoration decor = new MyRecycleViewDecoration(this, LinearLayoutManager.VERTICAL, true);
        MyRecycleViewDecoration decor1 = new MyRecycleViewDecoration(this, LinearLayoutManager.HORIZONTAL, true);
        gameRcv.addItemDecoration(decor);
        gameRcv.addItemDecoration(decor1);
        gameRcv.setItemAnimator(null);
        ViewGroup.LayoutParams params = gameRcv.getLayoutParams();
        params.width = DisplayUtils.getScreenWidth(this) - DisplayUtils.dp2px(80, this);
        params.height = params.width * numOfRow / numOfColumn + numOfRow * 2;
        //不显示半圆
        gameRcv.setOverScrollMode(View.OVER_SCROLL_NEVER);

        mapWidth = params.width;
        mapHeight = params.height;

        mapRectHeight = mapRectWidth = mapWidth / numOfColumn;

        gameRcv.setLayoutParams(params);

        initRoutes();

        adapter = new RouteAdapter(this, routes, R.layout.item_route);
        gameRcv.setAdapter(adapter);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initTrain();
    }

    /**
     * 初始化火车
     */
    private void initTrain() {

        ivTrain.setTranslationY(mapHeight / 2 - mapRectHeight / 2 - ivTrain.getHeight());

        controller = new TrainController(ivTrain, mapRectHeight);
        controller.setListener(this);

        controller.start();
        //初始化火车实体,默认位置为-1
        train = new Train(-1);


    }

    private void initRoutes() {
        for (int i = 0; i < numOfRow * numOfColumn; i++) {
            routes.add(RouteImage.getRandomRouteImage());
        }
    }

    // Called in Android UI's main thread
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void routeItemClicked(RouteAdapter.RouteItemClickedEvent event) {
//        Log.e("***", event.position + "");
        routes.get(event.position).changeImageDirection();

        adapter.notifyItemChanged(event.position);


    }

    @Override
    public void next() {
        calculatePosition();
        controller.next(routes.get(train.currentPostion));
    }


    @Override
    public void gameOver() {
        ToastUtil.showToastDefault(this,"游戏结束!");
    }

    /**
     * 计算当前位置
     */
    private void calculatePosition() {
        //第一次进入
        if (train.currentPostion == -1) {
            train.currentPostion = numOfColumn * 5 - 1;
        } else {
            switch ((int) ivTrain.getRotation() % 360) {
                //向左
                case 0:
                    //最左
                    if (train.currentPostion % numOfColumn == 0) {
                        gameOver();
                    } else {
                        train.currentPostion -= 1;
                    }
                    break;
                //向上
                case 90:
                case -270:
                    if (train.currentPostion < numOfColumn) {
                        gameOver();
                    } else {
                        train.currentPostion -= numOfColumn;
                    }
                    break;
                //向右
                case 180:
                case -180:
                    if (train.currentPostion % numOfColumn == numOfColumn - 1) {
                        gameOver();
                    } else {
                        train.currentPostion += 1;
                    }
                    break;
                //向下
                case 270:
                case -90:
                    if (train.currentPostion >= numOfColumn * (numOfRow - 1)) {
                        gameOver();
                    } else {
                        train.currentPostion += numOfColumn;
                    }
                    break;

            }

        }
    }
}
