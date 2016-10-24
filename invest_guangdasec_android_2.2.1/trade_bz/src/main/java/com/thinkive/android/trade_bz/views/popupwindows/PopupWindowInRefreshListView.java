package com.thinkive.android.trade_bz.views.popupwindows;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;


/**
 * 用PopupWindow 弹出一个可上拉加载，下拉刷新的listview
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/9/2
 */

public class PopupWindowInRefreshListView extends PopupWindow {

    /**
     * PopupWindow布局
     */
    private View mCustomView;

    /**
     * ListView 对象
     */
    private ListView mListView;
    /**
     * 自定义的ListView
     */
    private PullToRefreshListView mRefershListView;


    /**
     * 初始化
     *
     * @param context
     * @param itemsOnClick
     */
    public PopupWindowInRefreshListView(Context context, ListView.OnItemClickListener itemsOnClick,PullToRefreshListView.OnRefreshListener RefreshListener) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCustomView = inflater.inflate(R.layout.fragment_common_refresh_listview, null);
        //设置布局
        this.setContentView(mCustomView);
        mRefershListView = (PullToRefreshListView)mCustomView.findViewById(R.id.lv_refresh_pop);
        mListView=mRefershListView.getRefreshableView();
        //添加监听
        mListView.setOnItemClickListener(itemsOnClick);
        mRefershListView.setOnRefreshListener(RefreshListener);
        mListView.setDivider(null);
        //是否获得焦点
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);
    }

    /**
     * 设置相关参数
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
     * 设置listview选中位置
     * @param selectionPos
     */
    public void setSelection(int selectionPos) {
        mListView.setSelection(selectionPos);
    }

    /**
     * 刷新listview
     */
    public void setmRefershListViewsh(){
        mListView.removeAllViews();
    }
    /**
     * 根据popupwindow中的listview的适配器，动态设置本类popupwindow的高度
     *
     * @param adapter 本类popupwindow中的listview的适配器
     * @param maxHeight 本类popupwindow的最大高度
     * @return height 设置后，本类popupwindow的高度
     */
    public int getListViewHeight(ListAdapter adapter, int maxHeight) {
        int height = 0;
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            View temp = adapter.getView(i, null, mListView);
            temp.measure(0, 0);
            height += temp.getMeasuredHeight();
        }
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
        return getListViewHeight(adapter, 400);
    }

    /**
     * 设置是否可下拉刷新
     * @param pullRefreshEnabled
     */
    public void setPRefreshEnabled(boolean pullRefreshEnabled) {
        mRefershListView.setPullRefreshEnabled(pullRefreshEnabled);
    }

    /**
     * 设置是否上拉加载更多
     * @param pullLoadEnabled
     */
    public void setPLoadEnabled(boolean pullLoadEnabled) {
        mRefershListView.setPullLoadEnabled(pullLoadEnabled);
    }

    /**
     * 刷新完成
     */
    public void onPDownRefreshComplete(){
        mRefershListView.onPullDownRefreshComplete();
    }

    /**
     * 下拉完成
     */
    public void onPUpRefreshComplete(){
        mRefershListView.onPullUpRefreshComplete();
    }

    /**
     * 最后刷新时间
     * @param label
     */
    public void setLastUpdatedData(CharSequence label){
        mRefershListView.setLastUpdatedLabel(label);
    }
}
