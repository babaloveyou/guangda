package com.thinkive.android.trade_bz.a_stock.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;

import java.util.ArrayList;

/**
 * 股票搜索列表的试图适配器
 * 列表项布局：item_pop_search_stocks.xml.xml
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/22
 */
public class SearchStocksAdapter extends AbsBaseAdapter<CodeTableBean> {
    private Context mContext;
    private int mHeight=-1;
    public SearchStocksAdapter(Context context) {
        super(context, R.layout.item_pop_search_stocks);
        mContext = context;
    }
    @Override
    protected void onFillComponentData(ViewHolder holder, CodeTableBean bean) {
        TextView codeTv = (TextView) holder.getComponentById(R.id.tv_item_search_stock_code);
        codeTv.setText(bean.getCode());
        TextView nameTv = (TextView) holder.getComponentById(R.id.tv_item_search_stock_name);
        nameTv.setText(bean.getName());
//        if (mHeight != -1) {
//            LinearLayout.LayoutParams codeTvLayoutParams = (LinearLayout.LayoutParams) codeTv.getLayoutParams();//取控件textView当前的布局参数
//            codeTvLayoutParams.height = SizeUtil.dp2px(mContext,mHeight);// 控件的高强制设成20
//            codeTv.setLayoutParams(codeTvLayoutParams);
//            LinearLayout.LayoutParams nameTvLayoutParams = (LinearLayout.LayoutParams) nameTv.getLayoutParams();//取控件textView当前的布局参数
//            nameTvLayoutParams.height = SizeUtil.dp2px(mContext,mHeight);// 控件的高强制设成20
//            nameTv.setLayoutParams(codeTvLayoutParams);
//        }
        // 下面的代码是为了实现奇数行和偶数行颜色不同。
        ArrayList<CodeTableBean> dataList = getListData();
        int position = dataList.indexOf(bean);
    }
}
