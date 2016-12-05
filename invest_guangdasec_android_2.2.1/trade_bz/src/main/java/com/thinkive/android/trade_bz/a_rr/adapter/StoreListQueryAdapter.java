package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/2.
 */
public class StoreListQueryAdapter extends AbsBaseAdapter<MyStoreStockBean> {
    private Context mContext;
    private int mHeight = -1;

    public StoreListQueryAdapter(Context context) {
        super(context, R.layout.item_pop_search_stocks);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, MyStoreStockBean bean) {
        TextView codeTv = (TextView) holder.getComponentById(R.id.tv_item_search_stock_code);
        codeTv.setText(bean.getStock_code());
        TextView nameTv = (TextView) holder.getComponentById(R.id.tv_item_search_stock_name);
        nameTv.setText(bean.getStock_name());
        //        if (mHeight != -1) {
        //            LinearLayout.LayoutParams codeTvLayoutParams = (LinearLayout.LayoutParams) codeTv.getLayoutParams();//取控件textView当前的布局参数
        //            codeTvLayoutParams.height = SizeUtil.dp2px(mContext,mHeight);// 控件的高强制设成20
        //            codeTv.setLayoutParams(codeTvLayoutParams);
        //            LinearLayout.LayoutParams nameTvLayoutParams = (LinearLayout.LayoutParams) nameTv.getLayoutParams();//取控件textView当前的布局参数
        //            nameTvLayoutParams.height = SizeUtil.dp2px(mContext,mHeight);// 控件的高强制设成20
        //            nameTv.setLayoutParams(codeTvLayoutParams);
        //        }
        // 下面的代码是为了实现奇数行和偶数行颜色不同。
        ArrayList<MyStoreStockBean> dataList = getListData();
        int position = dataList.indexOf(bean);
    }
}

