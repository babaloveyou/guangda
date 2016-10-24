package com.thinkive.android.trade_bz.a_option.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.compatible.ListenerController;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.activity.OptionSelectListActivity;
import com.thinkive.android.trade_bz.a_option.bean.OptionContractBean;
import com.thinkive.android.trade_bz.a_option.bll.OptionExerciseServicesImpl;
import com.thinkive.android.trade_bz.a_option.controll.OptionExerciseController;
import com.thinkive.android.trade_bz.a_stock.bean.StockBuySellDish;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;


/**
 * 个股期权 行权
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/17
 */
public class OptionExerciseFragment extends AbsBaseFragment {

    private TKFragmentActivity mActivity;
    private OptionExerciseServicesImpl mServices;
    private OptionExerciseController mController;
    private Resources mResources;
    /**
     *子布局
     */
    private View mChildView;
    /**
     *嵌套子布局的View
     */
    private ScrollView mScrollView;
    /**
     * 选择合约
     */
    private TextView mTvSelectContract;
    /**
     *标的证券
     */
    private TextView mTvCodeAndName;
    /**
     *到期月份
     */
    private TextView mTvCutDate;
    /**
     *行权价格
     */
    private TextView mTvExercisePrice;
    /**
     *合约代码
     */
    private TextView mTvContractCode;
    /**
     *合约名称
     */
    private TextView mTvContractName;
    /**
     *数量减
     */
    private TextView mTvNumCut;
    /**
     *数量输入框
     */
    private EditText mEdtEntrustNum;
    /**
     *数量加
     */
    private TextView mTvNumAdd;
    /**
     *最大可用量
     */
    private TextView mTvMaxEnable;
    /**
     *点击 “全部”
     */
    private TextView mTvClickAllMax;
    /**
     *提交按钮
     */
    private Button mBtnClickCommit;
    /**
     * 五档盘行的集合，里面的每个LinearLayout代表五档盘的一行
     */
    private ArrayList<LinearLayout> mBuySaleDiskLlList;

    //***********************五档买入布局***********************
    private LinearLayout mLlBuy1;
    private LinearLayout mLlBuy2;
    private LinearLayout mLlBuy3;
    private LinearLayout mLlBuy4;
    private LinearLayout mLlBuy5;
    //***********************五档买入价格***********************
    private TextView mTvBuy1Price;
    private TextView mTvBuy2Price;
    private TextView mTvBuy3Price;
    private TextView mTvBuy4Price;
    private TextView mTvBuy5Price;
    //***********************五档买入数量***********************
    private TextView mTvBuy1Amount;
    private TextView mTvBuy2Amount;
    private TextView mTvBuy3Amount;
    private TextView mTvBuy4Amount;
    private TextView mTvBuy5Amount;
    //***********************五档卖出布局***********************
    private LinearLayout mLlSale5;
    private LinearLayout mLlSale4;
    private LinearLayout mLlSale3;
    private LinearLayout mLlSale2;
    private LinearLayout mLlSale1;
    //***********************五档卖出价格***********************
    private TextView mTvSale5Price;
    private TextView mTvSale4Price;
    private TextView mTvSale3Price;
    private TextView mTvSale2Price;
    private TextView mTvSale1Price;
    //***********************五档卖出数量***********************
    private TextView mTvSale5Amount;
    private TextView mTvSale4Amount;
    private TextView mTvSale3Amount;
    private TextView mTvSale2Amount;
    private TextView mTvSale1Amount;
    /**
     * 合约数据集
     */
    private ArrayList<OptionContractBean> mContractDataList;
    /**
     * 当前所选合约的 bean
     */
    private OptionContractBean mContractBean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_scrollview, null);
        mChildView = inflater.inflate(R.layout.fragment_option_exercise, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mBuySaleDiskLlList = new ArrayList<LinearLayout>();
        mScrollView = (ScrollView)view.findViewById(R.id.sll_layout);
        mScrollView.addView(mChildView);

        mTvSelectContract = (TextView)mChildView.findViewById(R.id.tv_select_contract);
        mTvCodeAndName = (TextView)mChildView.findViewById(R.id.tv_code_and_name);
        mTvCutDate = (TextView)mChildView.findViewById(R.id.tv_cut_date);
        mTvExercisePrice = (TextView)mChildView.findViewById(R.id.tv_exercise_price);
        mTvContractCode = (TextView)mChildView.findViewById(R.id.tv_contract_code);
        mTvContractName = (TextView)mChildView.findViewById(R.id.tv_contract_name);
        mTvNumCut = (TextView)mChildView.findViewById(R.id.tv_num_cut);
        mEdtEntrustNum = (EditText)mChildView.findViewById(R.id.edt_entrust_num);
        mTvNumAdd = (TextView)mChildView.findViewById(R.id.tv_num_add);
        mTvMaxEnable = (TextView)mChildView.findViewById(R.id.tv_max_enable);
        mTvClickAllMax = (TextView)mChildView.findViewById(R.id.tv_click_all_max);
        mBtnClickCommit = (Button)mChildView.findViewById(R.id.btn_click_commit);

        //**************************************五档买入布局*************************
        mLlBuy1 = (LinearLayout)mChildView.findViewById(R.id.ll_buy1);
        mLlBuy2 = (LinearLayout)mChildView.findViewById(R.id.ll_buy2);
        mLlBuy3 = (LinearLayout)mChildView.findViewById(R.id.ll_buy3);
        mLlBuy4 = (LinearLayout)mChildView.findViewById(R.id.ll_buy4);
        mLlBuy5 = (LinearLayout)mChildView.findViewById(R.id.ll_buy5);
        mBuySaleDiskLlList.add(mLlBuy1);
        mBuySaleDiskLlList.add(mLlBuy2);
        mBuySaleDiskLlList.add(mLlBuy3);
        mBuySaleDiskLlList.add(mLlBuy4);
        mBuySaleDiskLlList.add(mLlBuy5);
        //**************************************五档买入价格************************
        mTvBuy1Price = (TextView)mChildView.findViewById(R.id.tv_buy1_price);
        mTvBuy2Price = (TextView)mChildView.findViewById(R.id.tv_buy2_price);
        mTvBuy3Price = (TextView)mChildView.findViewById(R.id.tv_buy3_price);
        mTvBuy4Price = (TextView)mChildView.findViewById(R.id.tv_buy4_price);
        mTvBuy5Price = (TextView)mChildView.findViewById(R.id.tv_buy5_price);
        //**************************************五档买入数量*************************
        mTvBuy1Amount = (TextView)mChildView.findViewById(R.id.tv_buy1_amount);
        mTvBuy2Amount = (TextView)mChildView.findViewById(R.id.tv_buy2_amount);
        mTvBuy3Amount = (TextView)mChildView.findViewById(R.id.tv_buy3_amount);
        mTvBuy4Amount = (TextView)mChildView.findViewById(R.id.tv_buy4_amount);
        mTvBuy5Amount = (TextView)mChildView.findViewById(R.id.tv_buy5_amount);
        //**************************************五档卖出布局**************************
        mLlSale5 = (LinearLayout)mChildView.findViewById(R.id.ll_sale5);
        mLlSale4 = (LinearLayout)mChildView.findViewById(R.id.ll_sale4);
        mLlSale3 = (LinearLayout)mChildView.findViewById(R.id.ll_sale3);
        mLlSale2 = (LinearLayout)mChildView.findViewById(R.id.ll_sale2);
        mLlSale1 = (LinearLayout)mChildView.findViewById(R.id.ll_sale1);
        mBuySaleDiskLlList.add(mLlSale5);
        mBuySaleDiskLlList.add(mLlSale4);
        mBuySaleDiskLlList.add(mLlSale3);
        mBuySaleDiskLlList.add(mLlSale2);
        mBuySaleDiskLlList.add(mLlSale1);
        //**************************************五档卖出价格**************************
        mTvSale5Price = (TextView)mChildView.findViewById(R.id.tv_sale5_price);
        mTvSale4Price = (TextView)mChildView.findViewById(R.id.tv_sale4_price);
        mTvSale3Price = (TextView)mChildView.findViewById(R.id.tv_sale3_price);
        mTvSale2Price = (TextView)mChildView.findViewById(R.id.tv_sale2_price);
        mTvSale1Price = (TextView)mChildView.findViewById(R.id.tv_sale1_price);
        //**************************************五档卖出数量*************************
        mTvSale5Amount = (TextView)mChildView.findViewById(R.id.tv_sale5_amount);
        mTvSale4Amount = (TextView)mChildView.findViewById(R.id.tv_sale4_amount);
        mTvSale3Amount = (TextView)mChildView.findViewById(R.id.tv_sale3_amount);
        mTvSale2Amount = (TextView)mChildView.findViewById(R.id.tv_sale2_amount);
        mTvSale1Amount = (TextView)mChildView.findViewById(R.id.tv_sale1_amount);
    }
    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mTvSelectContract, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvNumCut, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvNumAdd, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvClickAllMax, mController);
        registerListener(AbsBaseController.ON_CLICK, mBtnClickCommit, mController);
        //选择为五档盘注册监听
        for (LinearLayout ll : mBuySaleDiskLlList) {
            registerListener(ListenerController.ON_CLICK, ll, mController);
        }
    }

    @Override
    protected void initData() {
        mActivity=(TKFragmentActivity)getActivity();
        mResources = CoreApplication.getInstance().getResources();
        mController = new OptionExerciseController(this);
        mServices = new OptionExerciseServicesImpl(this);
    }

    @Override
    protected void initViews() {
        //请求合约
        mServices.requestOptionContract();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 得到合约数据
     */
    public void onGetOptionContractList(ArrayList<OptionContractBean> dataList){
        if(dataList == null || dataList.size() < 0){
            mTvSelectContract.setHint(mResources.getString(R.string.option_buy17));
        }else{
            mTvSelectContract.setHint(mResources.getString(R.string.option_buy1));
            mContractDataList = dataList;
        }
    }

    /**
     * 点击选择合约
     */
    public void onClickSelectContract(){
        if(mContractDataList != null && mContractDataList.size() > 0){
                Intent intent = new Intent(mActivity,OptionSelectListActivity.class);
                intent.putParcelableArrayListExtra("data_select",mContractDataList);
                intent.putExtra("useType","option_contract");
                startActivityForResult(intent,68);
            }else{
                ToastUtils.toast(mActivity,mResources.getString(R.string.option_buy17));
        }
    }

    /**
     * 合约选择完成时
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        int position = data.getIntExtra("data_select_result",0);
        mContractBean =  mContractDataList.get(position);
        //todo:缺字段
        mTvCodeAndName.setText(mContractBean.getStock_code()+" "+"name没有");
        mTvCutDate.setText(mContractBean.getEnd_date());
        mTvExercisePrice.setText(mContractBean.getExercise_price());
        mTvContractCode.setText(mContractBean.getOption_code());
        mTvContractName.setText(mContractBean.getOption_name());
    }
    /**
     * 点击数量减
     */
    public void onClickNumCut(){
        int num = getIntEntrustNum();
        if (num != 0) {
            mEdtEntrustNum.setText(String.valueOf(num - 100));
        }
    }
    /**
     * 点击数量加
     */
    public void onClickNumAdd(){
        int num = getIntEntrustNum();
        if (num != 0) {
            mEdtEntrustNum.setText(String.valueOf(num + 100));
        }
    }
    /**
     * 点击 “全部”
     */
    public void onClickMaxAll(){
        mEdtEntrustNum.setText(mTvMaxEnable.getText().toString());
    }
    /**
     * 点击提交委托按钮
     */
    public void onClickBtnCommit(){
        String num = getStringEntrustNum();
        //未选择合约
        if(mContractBean == null){
            ToastUtils.toast(mActivity,mResources.getString(R.string.option_buy18));
            return;
        }
        //未输入委托数量
        if(TextUtils.isEmpty(num)){
            ToastUtils.toast(mActivity,mResources.getString(R.string.option_buy21));
            return;
        }
    }

    /**
     * 设置单个数据到五档盘TextViews上面
     */
    private void setWuDangDataToViews(TextView textView, String data, String textColor) {
        textView.setText(data);
        if (!textColor.equals("")) {
            textView.setTextColor(Color.parseColor(textColor));
        }
    }

    /**
     * 在成功从服务器获取五档买卖盘数据时调用
     * 设置五档买卖盘数据和当前委托价格数据（当前委托价格，买入时取买一，卖出时取卖一）。
     */
    public void onGetWuDangDishData(StockBuySellDish bean, String market, String exchangeType) {
        ArrayList<String> priceColorList = bean.getPriceColorsList();
        ArrayList<String> valueList = bean.getValueBuySale();
        setWuDangDataToViews(mTvSale5Price, valueList.get(0), priceColorList.get(0));
        setWuDangDataToViews(mTvSale4Price, valueList.get(1), priceColorList.get(1));
        setWuDangDataToViews(mTvSale3Price, valueList.get(2), priceColorList.get(2));
        setWuDangDataToViews(mTvSale2Price, valueList.get(3), priceColorList.get(3));
        setWuDangDataToViews(mTvSale1Price, valueList.get(4), priceColorList.get(4));
        setWuDangDataToViews(mTvSale5Amount, valueList.get(5), priceColorList.get(5));
        setWuDangDataToViews(mTvSale4Amount, valueList.get(6), priceColorList.get(6));
        setWuDangDataToViews(mTvSale3Amount, valueList.get(7), priceColorList.get(7));
        setWuDangDataToViews(mTvSale2Amount, valueList.get(8), priceColorList.get(8));
        setWuDangDataToViews(mTvSale1Amount, valueList.get(9), priceColorList.get(9));
        setWuDangDataToViews(mTvBuy1Price, valueList.get(10), priceColorList.get(10));
        setWuDangDataToViews(mTvBuy2Price, valueList.get(11), priceColorList.get(11));
        setWuDangDataToViews(mTvBuy3Price, valueList.get(12), priceColorList.get(12));
        setWuDangDataToViews(mTvBuy4Price, valueList.get(13), priceColorList.get(13));
        setWuDangDataToViews(mTvBuy5Price, valueList.get(14), priceColorList.get(14));
        setWuDangDataToViews(mTvBuy1Amount, valueList.get(15), priceColorList.get(15));
        setWuDangDataToViews(mTvBuy2Amount, valueList.get(16), priceColorList.get(16));
        setWuDangDataToViews(mTvBuy3Amount, valueList.get(17), priceColorList.get(17));
        setWuDangDataToViews(mTvBuy4Amount, valueList.get(18), priceColorList.get(18));
        setWuDangDataToViews(mTvBuy5Amount, valueList.get(19), priceColorList.get(19));
    }

    /**
     * 清空界面上的所有数据
     */
    public void onClearAllData(){
        mTvSelectContract.setText("");
        mTvCodeAndName.setText("");
        mTvCutDate.setText("");
        mTvExercisePrice.setText("");
        mTvContractCode.setText("");
        mTvContractName.setText("");
        mTvMaxEnable.setText("");
        mEdtEntrustNum.setText("");
        mContractBean = null;
        clearWuDangData();
    }

    /**
     * 清空五档买卖盘和当前委托价格上的数据
     */
    public void clearWuDangData() {
        mTvSale5Price.setText("");
        mTvSale4Price.setText("");
        mTvSale3Price.setText("");
        mTvSale2Price.setText("");
        mTvSale1Price.setText("");
        mTvSale5Amount.setText("");
        mTvSale4Amount.setText("");
        mTvSale3Amount.setText("");
        mTvSale2Amount.setText("");
        mTvSale1Amount.setText("");
        mTvBuy1Price.setText("");
        mTvBuy2Price.setText("");
        mTvBuy3Price.setText("");
        mTvBuy4Price.setText("");
        mTvBuy5Price.setText("");
        mTvBuy1Amount.setText("");
        mTvBuy2Amount.setText("");
        mTvBuy3Amount.setText("");
        mTvBuy4Amount.setText("");
        mTvBuy5Amount.setText("");
        // 还原五档盘上TextView的颜色
        mTvSale5Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvSale4Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvSale3Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvSale2Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvSale1Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvSale5Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvSale4Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvSale3Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvSale2Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvSale1Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvBuy1Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvBuy2Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvBuy3Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvBuy4Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvBuy5Price.setTextColor(mResources.getColor(R.color.trade_text_color6));
        mTvBuy1Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvBuy2Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvBuy3Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvBuy4Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
        mTvBuy5Amount.setTextColor(mResources.getColor(R.color.trade_text_color9));
    }
    /**
     * 获取委托数量输入框上面的数据
     * 返回int类型的值
     */
    private int getIntEntrustNum() {
        int result = 0;
        String priceEditTextContent = mEdtEntrustNum.getText().toString().trim();
        if (!TextUtils.isEmpty(priceEditTextContent)) {
            try {
                result = Integer.parseInt(priceEditTextContent);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return result;
    }
    /**
     * 获取委托数量输入框上面的数据
     * 返回String类型的值
     */
    private String getStringEntrustNum() {
        return mEdtEntrustNum.getText().toString().trim();
    }
}



