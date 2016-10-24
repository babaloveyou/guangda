package com.thinkive.android.trade_bz.a_stock.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.bean.LoginInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/13.
 */
public abstract class HolderAccountAdapter extends BaseAdapter {
    private ArrayList<String> mHolderAccounts=new ArrayList<>();
    private MultiTradeActivity mActivity;
    private TextView mHoderAccountTv;

    public HolderAccountAdapter(MultiTradeActivity activity, ArrayList<String> holderAccounts, TextView holderAccount) {
        mActivity = activity;
        mHoderAccountTv = holderAccount;
        processDate(holderAccounts);
    }

    @Override
    public int getCount() {
        return mHolderAccounts.size();
    }

    @Override
    public String getItem(int position) {
        return mHolderAccounts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_account_pop_lv, parent, false);
        }
        final TextView accountTv = (TextView) convertView.findViewById(R.id.tv_holder_account);
        final ImageView iv = (ImageView) convertView.findViewById(R.id.iv_item_select);
        LinearLayout parentToClick = (LinearLayout) convertView.findViewById(R.id.ll_click_parent);
        String itemString = mHolderAccounts.get(position);
        accountTv.setText(itemString);
        if (LoginInfo.getSelectHolderAccount().equals(itemString)) {
            iv.setImageResource(R.drawable.is_select);
        } else {
            iv.setImageResource(R.drawable.is_not_select);
        }
        parentToClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(accountTv.getText().toString(), position);
            }
        });
        return convertView;
    }

    protected abstract void onItemClick(String accountString, int position);

    public void setDataList(ArrayList<String> dataList) {
        //将被选中的account放到数据集最前面
        processDate(dataList);
        notifyDataSetChanged();
    }

    private void processDate(ArrayList<String> dataList) {
        mHolderAccounts.clear();
        for (String s : dataList) {
            if (LoginInfo.getSelectHolderAccount().equals(s)) {
                mHolderAccounts.add(0, s);
            } else {
                mHolderAccounts.add(s);
            }
        }
    }
}
