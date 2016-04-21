package cn.wycode.train.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.wycode.train.R;
import cn.wycode.train.activity.GameActivity;
import cn.wycode.train.adapter.baseadapter.CommonAdapter4RecyclerView;
import cn.wycode.train.adapter.baseadapter.CommonHolder4RecyclerView;
import cn.wycode.train.adapter.baseadapter.ListenerWithPosition;
import cn.wycode.train.entity.RouteImage;
import cn.wycode.train.utils.DisplayUtils;

/**
 * 地图块的适配器
 * Created by wangyu on 16/4/7.
 */
public class RouteAdapter extends CommonAdapter4RecyclerView<RouteImage>
        implements ListenerWithPosition.OnClickWithPositionListener<CommonHolder4RecyclerView> {

    public RouteAdapter(Context context, List<RouteImage> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonHolder4RecyclerView holder, RouteImage routeImage) {

        ViewGroup.LayoutParams params = holder.mConvertView.getLayoutParams();
        //noinspection SuspiciousNameCombination
        params.height = GameActivity.mapRectWidth;
        params.width = GameActivity.mapRectWidth;
        holder.mConvertView.setLayoutParams(params);

        holder.setImageViewImage(R.id.iv_game_route, routeImage.imageResourceId);

        holder.setOnItemClickListener(this);


    }

    @Override
    public void onClick(View v, int position, CommonHolder4RecyclerView holder) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.route_rotate);
        v.startAnimation(animation);
        animation.setAnimationListener(new AnimationListener(position));

    }

    class AnimationListener implements Animation.AnimationListener {
        int position;

        public AnimationListener(int position) {
            this.position = position;
        }


        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            EventBus.getDefault().post(new RouteItemClickedEvent(position));
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }


    }

    public static final class RouteItemClickedEvent {
        public int position;

        public RouteItemClickedEvent(int position) {
            this.position = position;
        }
    }
}
