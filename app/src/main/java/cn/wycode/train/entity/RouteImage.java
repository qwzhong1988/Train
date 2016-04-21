package cn.wycode.train.entity;

import android.util.Log;


import cn.wycode.train.R;

/**
 * 路径地图块
 * Created by wangyu on 16/4/7.
 */
public class RouteImage {

    public int imageResourceId;

    //左0,上1,右2,下3
    public int imageDirection;


    //0直线,1直角
    public int type;

    public static final int TYPE_LINE = 0;
    public static final int TYPE_ANGLE = 1;

    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_TOP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_BOTTOM = 3;

    private RouteImage() {

    }

    /**
     * 构造一个随机路径图
     *
     * @return 路径图对象
     */
    public static RouteImage getRandomRouteImage() {
        int type = (int) (Math.random() * 2);
        int imageDirection = (int) (Math.random() * 4);
        RouteImage routeImage = new RouteImage(type, imageDirection);
        Log.d("RouteImage", routeImage.toString());
        return routeImage;
    }

    public RouteImage(int type, int imageDirection) {
        this.type = type;
        this.imageDirection = imageDirection;

        setImageResourceId(type, imageDirection);
    }

    private void setImageResourceId(int type, int imageDirection) {
        switch (type) {
            case 0:
                switch (imageDirection) {
                    case 0:
                    case 2:
                        this.imageResourceId = R.drawable.type0_direction0;
                        break;
                    case 1:
                    case 3:
                        this.imageResourceId = R.drawable.type0_direction1;
                        break;
                }
                break;

            case 1:
                switch (imageDirection) {
                    case 0:
                        this.imageResourceId = R.drawable.type1_direction0;
                        break;
                    case 1:
                        this.imageResourceId = R.drawable.type1_direction1;
                        break;
                    case 2:
                        this.imageResourceId = R.drawable.type1_direction2;
                        break;
                    case 3:
                        this.imageResourceId = R.drawable.type1_direction3;
                        break;
                }
                break;
        }
    }

    /**
     * 向右转90°
     */
    public void changeImageDirection() {
        if (imageDirection != 3) {
            this.imageDirection += 1;
        } else {
            this.imageDirection = 0;
        }
        setImageResourceId(type, imageDirection);

    }

    @Override
    public String toString() {
        return "RouteImage{" +
                "imageResourceId=" + imageResourceId +
                ", imageDirection=" + imageDirection +
                ", type=" + type +
                '}';
    }
}
