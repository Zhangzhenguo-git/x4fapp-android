package com.x4fhuozhu.app.util;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by vonde on 2018/02/06.
 */

public class RxRecyclerViewDivider extends RecyclerView.ItemDecoration {

    private int space = 0;
    private int leftSpace = 0;
    private int rightSpace = 0;
    private int topSpace = 0;
    private int bottomSpace = 0;
    private int leftRight;
    private int topBottom;
    private boolean isTop = false;

    public RxRecyclerViewDivider(int leftSpace, int rightSpace, int topSpace, int bottomSpace) {
        this.leftSpace = leftSpace;
        this.rightSpace = rightSpace;
        this.topSpace = topSpace;
        this.bottomSpace = bottomSpace;
    }

    public RxRecyclerViewDivider(int leftSpace, int rightSpace, int topSpace, int bottomSpace, boolean isTop) {
        this.leftSpace = leftSpace;
        this.rightSpace = rightSpace;
        this.topSpace = topSpace;
        this.bottomSpace = bottomSpace;
        this.isTop = isTop;
    }

    public RxRecyclerViewDivider(int space, boolean isTop) {
        this.space = space;
        this.isTop = isTop;
    }

    public RxRecyclerViewDivider(int space) {
        this.space = space;
        this.topBottom = space;
        this.leftRight = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

//        if (space == 0) {
//            outRect.left = leftSpace;
//            outRect.right = rightSpace;
//            outRect.bottom = topSpace;
//            outRect.top = bottomSpace;
//        } else {
//            //不是第一个的格子都设一个左边和底部的间距
//            outRect.left = space;
//            outRect.right = space;
//            outRect.bottom = space;
//            outRect.top = space;
//        }
//
//        if (isTop) {
//            if (parent.getChildLayoutPosition(view) == 0) {
//                outRect.top = 0;
//            }
////            outRect.left = 0;
////            outRect.right = 0;
//            outRect.left = space;
//            outRect.right = space;
//        }

        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager(); //判断总的数量是否可以整除
        int totalCount = layoutManager.getItemCount();
        int surplusCount = totalCount % layoutManager.getSpanCount();
        int childPosition = parent.getChildAdapterPosition(view);
        if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {
            //竖直方向的
            if (surplusCount == 0 && childPosition > totalCount - layoutManager.getSpanCount() - 1) {
                //后面几项需要bottom
                outRect.bottom = topBottom;
            } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
                outRect.bottom = topBottom;
            }
            if ((childPosition + 1) % layoutManager.getSpanCount() == 0) {
                //被整除的需要右边
                outRect.right = leftRight;
            }
            outRect.top = topBottom;
            outRect.left = leftRight;
        } else {
            if (surplusCount == 0 && childPosition > totalCount - layoutManager.getSpanCount() - 1) {
                //后面几项需要右边
                outRect.right = leftRight;
            } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
                outRect.right = leftRight;
            }
            if ((childPosition + 1) % layoutManager.getSpanCount() == 0) {
                //被整除的需要下边
                outRect.bottom = topBottom;
            }
            outRect.top = topBottom;
            outRect.left = leftRight;
        }

    }

}
