package cn.wycode.train.adapter.baseadapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by huangyi on 16/2/26.
 * RecycleView的分割线
 */
public class MyRecycleViewDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };


    private Context mContext;
    private int orientation;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    public Drawable mDivider;

    private boolean isGridView;

    /**
     * @param context     上下文
     * @param orientation 传入decoration
     * @param isGridView  是否是GridView
     */
    public MyRecycleViewDecoration(Context context, int orientation, boolean isGridView) {
        this.isGridView = isGridView;
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
//        mDivider=context.getDrawable(R.drawable.ic_picture_loading);
        a.recycle();
        this.mContext = context;
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        this.orientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (orientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    /**
     * 竖向
     *
     * @param c      画笔
     * @param parent 父类
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        if (isGridView) {
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            float row = childCount / layoutManager.getSpanCount();
            childCount = (int) Math.ceil(row);
        }
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
//            final int bottom = top + mDivider.getIntrinsicHeight();
            //改为1像素分割线更美观
//            final int bottom = top + 1;

            /************项目修改不要分割线*****************/
            final int bottom = 0;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 横向
     *
     * @param c      画笔
     * @param parent 父类
     */
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        if (isGridView) {
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            childCount = layoutManager.getSpanCount();
        }
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            //改为1像素分割线更美观
//            final int right = left + mDivider.getIntrinsicHeight();
//            final int right = left + 1;

            /************项目修改不要分割线*****************/
            final int right = 0;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (orientation == VERTICAL_LIST) {
//            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            outRect.set(0, 0, 0, 1);
        } else {
//            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            outRect.set(0, 0, 1, 0);
        }
    }

}
