package com.thinkive.android.trade_bz.views.popupwindows;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.android.thinkive.framework.CoreApplication;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.utils.SizeUtil;

/**
 * 用PopupWindow 弹出一个ListView列表
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/31
 */

public class PopupWindowInListView extends PopupWindow {

    /**
     * PopupWindow布局
     */
    private View mCustomView;

    /**
     * list
     */
    private ListView mListView;


    /**
     * 初始化
     *
     * @param context
     * @param itemsOnClick
     */
    public PopupWindowInListView(Context context, ListView.OnItemClickListener itemsOnClick) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCustomView = inflater.inflate(R.layout.fragment_common_listview, null);
        //设置布局
        this.setContentView(mCustomView);
        mListView = (ListView) mCustomView.findViewById(R.id.lv_pop);
        //添加监听
        mListView.setOnItemClickListener(itemsOnClick);

    }

    /**
     * 设置相关参数
     *
     * @param parentView
     * @param width
     * @param height
     * @param x
     * @param y
     */
    public void showPopwindow(View parentView, int width, int height, int x, int y) {
        //设置宽度
        this.setWidth(width);
        //设置高度
        this.setHeight(height);
        //是否获得焦点
        this.setFocusable(true);
        Drawable drawable = CoreApplication.getInstance().getResources().getDrawable(R.drawable.shape_bg_alph_popwindow);
        this.setBackgroundDrawable(drawable);
        //下拉偏移量
        this.showAsDropDown(parentView, x, y);
    }

    /**
     * 设置数据
     *
     * @param adapter
     */
    public void setListAdapter(ListAdapter adapter) {
        mListView.setAdapter(adapter);
    }

    /**
     * 根据popupwindow中的listview的适配器，动态设置本类popupwindow的高度
     *
     * @param adapter   本类popupwindow中的listview的适配器
     * @param maxHeight 本类popupwindow的最大高度
     * @return height 设置后，本类popupwindow的高度
     */
    //    mAccountPopWindow = new PopupWindow(mPopupWindowView, mAccountParentLl.getWidth(), SizeUtil.dp2px(mActivity, 105 + 1), false);
    public int getListViewHeight(ListAdapter adapter, int maxHeight) {
        int count = adapter.getCount();
        int height = SizeUtil.dp2px(CoreApplication.getInstance().getBaseContext(), 35f * count + 0.5f * (count > 1 ? count - 1 : 0));
        if (height > maxHeight) {
            height = maxHeight;
        }
        return height;
    }

    /**
     * 根据popupwindow中的listview的适配器，动态设置本类popupwindow的高度
     * 最大高度默认为400
     *
     * @param adapter 本类popupwindow中的listview的适配器
     * @return height 设置后，本类popupwindow的高度
     */
    public int getListViewHeight(ListAdapter adapter) {
        return getListViewHeight(adapter,400);
    }
}
