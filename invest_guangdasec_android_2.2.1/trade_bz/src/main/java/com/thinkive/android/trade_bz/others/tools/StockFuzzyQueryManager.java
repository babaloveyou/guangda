package com.thinkive.android.trade_bz.others.tools;


import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.ICallBack;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.adapter.SearchStocksAdapter;
import com.thinkive.android.trade_bz.utils.SizeUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.views.popupwindows.PopupWindowInListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 股票模糊查询管理器。（A股）
 * 本类需要行情发起请求，获取模糊查询结果；然后弹出popupwindow来展示模糊查询的结果。
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/25
 */
public class StockFuzzyQueryManager {
    /**
     * 输入查询的等待毫秒数
     */
    private static final int INPUT_TIME_DIVIDER = 500;
    /**
     * 上一次输入股票代码的时间
     */
    private long mLastInputTime = 0;
    /**
     * 本类的单例实例
     */
    private static StockFuzzyQueryManager sInstance;
    /**
     * 显示模糊查询结果的popupwindow
     * 他的宽度由{@link #mPopupwindowWidth}指定，高度由
     */
    private PopupWindowInListView mPopupWindow;
    /**
     * 查询到的股票列表适配器
     */
    private SearchStocksAdapter mSearchStocksAdapter;
    /**
     * {@link #mPopupWindow}的宽度
     */
    private int mPopupwindowWidth;
    /**
     * 弹出框所依靠的控件
     */
    private View mWidthReferView;
    /**
     * 外部调用环境
     */
    private Context mContext;
    /**
     * 获取本类对象的方法
     * @return 本类的单例对象
     */
    public synchronized static StockFuzzyQueryManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new StockFuzzyQueryManager(context);
        }
        return sInstance;
    }

    /**
     * 单例模式下的私有构造方法
     */
    private StockFuzzyQueryManager(Context context) {
        mContext = context;
        mSearchStocksAdapter = new SearchStocksAdapter(context);
    }

    /**
     * 执行模糊查询，并显示popupWindow来展示查询结果
     * 通过给行情发消息，让行情去根据queryKey去执行模糊查询，并异步返回给我们查询结果集
     * @param queryKey
     * @param searchType  1：HS  2：HK  3：HK+HS  4 :HS+HK
     * 模糊查询的关键字
     */
    public void execQuery(String queryKey,String searchType,final View parentView) {
        final long curTime = Calendar.getInstance().getTimeInMillis();
        if (curTime - mLastInputTime > INPUT_TIME_DIVIDER) {
            sendMsgToHqForStockList(queryKey,searchType, new IHqCallBackStock() {
                @Override
                public void onGetStockMsg(ArrayList<CodeTableBean> dataList) {
                    mSearchStocksAdapter.setListData(dataList);
                    mSearchStocksAdapter.notifyDataSetChanged();
                    mLastInputTime = curTime;
                    mPopupWindow.getListView().setSelection(0);
                    showQueryPopupWindow(parentView);
                }
            });
        }
    }

    /**
     * 执行模糊查询，并显示popupWindow来展示查询结果
     * 通过给行情发消息，让行情去根据queryKey去执行模糊查询，并异步返回给我们查询结果集
     * @param queryKey
     * @param searchType  1：HS  2：HK  3：HK+HS  4 :HS+HK
     * 模糊查询的关键字
     */
    public void execQuery(String queryKey,String searchType,IHqCallBackStock callBackStock) {
        sendMsgToHqForStockList(queryKey, searchType, callBackStock);
    }

    /**
     * 给行情模块发送消息，让行情模块给本类返回股票搜索提示列表
     * 这里的模块通信异步返回结果。
     */
    private void sendMsgToHqForStockList(String curEditStockCode,String searchType, final IHqCallBackStock iHqCallBackStock){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("searchKey", curEditStockCode);
            jsonObject.put("num", "30");
            jsonObject.put("searchType", searchType);
            AppMessage msg = new AppMessage("self-stock", 60101, jsonObject);
            msg.setCallBack(new ICallBack() {
                @Override
                public void callback(Object o) {
                    ArrayList<CodeTableBean> dataList = new ArrayList<CodeTableBean>();
                    try {
                        if(o != null && !o.equals("")){
                            String result = o.toString();
                            JSONObject resultJsonObject = new JSONObject(result);
                            JSONArray resultJsonArray = resultJsonObject.getJSONArray("results");
                            int resultJsonArrayLength = resultJsonArray.length();
                            CodeTableBean tempBean;
                            for (int i = 0; i < resultJsonArrayLength; i++) {
                                tempBean = JsonParseUtil.parseJsonToObject(resultJsonArray.getJSONObject(i), CodeTableBean.class);
                                dataList.add(tempBean);
                            }
                            iHqCallBackStock.onGetStockMsg(dataList);
                        }else{
                            ToastUtils.toast(CoreApplication.getInstance(), CoreApplication.getInstance().getResources().getString(R.string.toast_call_back_hq));
                        }
                    } catch (JSONException je) {
                        je.printStackTrace();
                    }
                }
            });
            MessageManager.getInstance(CoreApplication.getInstance()).sendMessage(msg);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    public interface IHqCallBackStock {
        void onGetStockMsg(ArrayList<CodeTableBean> dataList);
    }

    /**
     * 执行查询自选股，并将数据设置到弹窗列表的adapter上。
     * 通过给行情发消息，让行情去根据queryKey去执行模糊查询，并异步返回给我们查询结果集
     */
    public void execQueryOptional() {
        long curTime = Calendar.getInstance().getTimeInMillis();
        if (curTime - mLastInputTime > INPUT_TIME_DIVIDER) {
            ArrayList<CodeTableBean> dataList = sendMsgToHqForOptionalStocks();
            mSearchStocksAdapter.setListData(dataList);
            mSearchStocksAdapter.notifyDataSetChanged();
            mLastInputTime = curTime;
        }
    }

    /**
     * 自选股同步，同步接收消息
     * @return
     */
    private ArrayList<CodeTableBean> sendMsgToHqForOptionalStocks() {
        ArrayList<CodeTableBean> dataList = new ArrayList<CodeTableBean>();
        try {
            JSONObject jsonObject = new JSONObject();
            AppMessage msg = new AppMessage("self-stock", 60100, jsonObject);
            String result = MessageManager.getInstance(CoreApplication.getInstance()).sendMessage(msg);
            JSONObject resultJsonObject = new JSONObject(result);
            JSONArray resultJsonArray = resultJsonObject.getJSONArray("results");
            int resultJsonArrayLength = resultJsonArray.length();
            CodeTableBean tempBean;
            for (int i = 0; i < resultJsonArrayLength; i++) {
                tempBean = JsonParseUtil.parseJsonToObject(resultJsonArray.getJSONObject(i), CodeTableBean.class);
                dataList.add(tempBean);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return dataList;
    }

    /**
     * 显示popupwindow
     * @param parentView
     * popupwindow将会显示在谁的下方
     */
    public void showQueryPopupWindow(View parentView) {
        if (mPopupwindowWidth == 0 && mWidthReferView != null) {
            mPopupwindowWidth = mWidthReferView.getWidth();
        }
        dismissQueryPopupWindow();
        int count = mSearchStocksAdapter.getCount();
        if(count < 8){
            mPopupWindow.showPopwindow(parentView,mPopupwindowWidth,mPopupWindow.getListViewHeight(mSearchStocksAdapter, SizeUtil.dp2px(CoreApplication.getInstance().getBaseContext(), 35f * 4 + 0.5f * 3)), 0, 0);
        }else{
            mPopupWindow.showPopwindow(parentView,mPopupwindowWidth, SizeUtil.dp2px(CoreApplication.getInstance().getBaseContext(), 35f * 4 + 0.5f * 3), 0, 0);
        }
    }

    /**
     * 停止模糊查询，隐藏之前用来展示查询结果的popupwindow
     */
    public void dismissQueryPopupWindow() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    /**
     * 设置popupwindow的宽度
     * @param width
     * popupwindow的宽度
     */
    public void setPopupwindowWidth(int width) {
        mPopupwindowWidth = width;
    }

    /**
     * 设置popupwindow的备用宽度参考对象
     * 当popupwindow的宽度设置失效的时候，也就是{@link #mPopupwindowWidth}等于0，
     * 恰好此时又要显示popupwindow的情况下；
     * 为了避免宽度为0导致popupwindow无法显示的问题，当宽度为0时，
     * 将使用widthReferView的宽度作为popupwindow的宽度
     * @param widthReferView
     * 此控件宽度，作为popupwindow的备用宽度值
     */
    public void setPopupWindowReserveWidthReferView(View widthReferView) {
        mWidthReferView = widthReferView;
    }

    public void initListViewPopupwindow(AdapterView.OnItemClickListener itemClickListener) {
        mPopupWindow = new PopupWindowInListView(mContext, itemClickListener);
        mPopupWindow.setListAdapter(mSearchStocksAdapter);
    }

    public SearchStocksAdapter getSearchStocksAdapter() {
        return mSearchStocksAdapter;
    }
}
