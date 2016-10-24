package com.thinkive.android.trade_bz.views.popupwindows;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;

/**
 * 从底部弹出的PopupWindow
 * 注：目前只适用于网络投票，一键表决
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/15
 */
public class PopupWindowShowBottom extends PopupWindow {

    private View view;

    private TextView mTvAgree,mTvOppose,mTvGiveUp,mTvCancel;

    public PopupWindowShowBottom(Context mContext, View.OnClickListener itemsOnClick) {

        this.view = LayoutInflater.from(mContext).inflate(R.layout.pop_show_bottom, null);

        mTvAgree = (TextView) view.findViewById(R.id.pop_all_agree);
        mTvOppose = (TextView) view.findViewById(R.id.pop_all_oppose);
        mTvGiveUp = (TextView) view.findViewById(R.id.pop_all_give_up);
        mTvCancel = (TextView) view.findViewById(R.id.pop_all_cancel);

        // 取消按钮
        mTvCancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });
        // 设置按钮监听
        mTvAgree.setOnClickListener(itemsOnClick);
        mTvOppose.setOnClickListener(itemsOnClick);
        mTvGiveUp.setOnClickListener(itemsOnClick);

        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });


        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.pop_show_bottom);

    }
}
