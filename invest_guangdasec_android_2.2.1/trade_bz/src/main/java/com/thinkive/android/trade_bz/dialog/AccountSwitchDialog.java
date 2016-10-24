package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;

import java.util.ArrayList;

/**
 * 账号选择对话框
 * 当用户选择某个可以使用多种账号的功能模块（港股通、密码、银证转账）时，弹出本对话框供用户选择
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/28
 */
public class AccountSwitchDialog extends AbsTradeDialog {
    /**
     * 回调外部调用环境的接口实例，
     */
    private IAccountSelectListener mListener;
    /**
     * 当前登录了几种账户
     */
    private ArrayList<String> mResult;
    private TextView mTvNormal;
    private TextView mTvCredit;
    private TextView mTvOption;
    /**
     * 构造方法
     * 给本类变量赋初值、初始化本对话框布局
     * @param context  外部调用环境对象
     * @param listener 对话框返回结果的回调接口
     */
    public AccountSwitchDialog(Context context, ArrayList<String> result,IAccountSelectListener listener) {
        super(context);
        mResult = result;
        mListener = listener;
        initDialogLayout();
        setLayout();
    }
    /**
     * 初始化对话框界面
     */
    @Override
    public void initDialogLayout() {
        super.initDialogLayout();
        // 设置顶部标题栏不显示
        setTitleBarVisiable(false);
        // 设置下方“确认”、“取消”按钮栏不显示
        setButtonBarVisiable(false);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_select_account, null);
        mTvNormal = (TextView) view.findViewById(R.id.tv_account_switch_normal);
        mTvCredit = (TextView) view.findViewById(R.id.tv_account_switch_credit);
        mTvOption = (TextView) view.findViewById(R.id.tv_account_switch_option);
        setSubViewToParent(view);
        if(mResult != null && mResult.size() > 0){
           for(int  i = 0;i<mResult.size(); i++){
              String result =  mResult.get(i);
               if(result.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)){
                   mTvNormal.setVisibility(View.VISIBLE);
               }else if(result.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)){
                   mTvCredit.setVisibility(View.VISIBLE);
               }else if(result.equals(TradeLoginManager.LOGIN_TYPE_OPTION)){
                   mTvOption.setVisibility(View.VISIBLE);
               }
           }
        }
        // 设置按钮“普通账户”的单击事件
        mTvNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.callbackSelectResult(0);
                dismiss();
            }
        });
        // 设置按钮“融资融券账户”的单击事件
        mTvCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.callbackSelectResult(1);
                dismiss();
            }
        });
        // 设置按钮“个股期权账户”的单击事件
        mTvOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.callbackSelectResult(2);
                dismiss();
            }
        });
    }

    /**
     * 回调外部调用环境的接口
     */
    public interface IAccountSelectListener {
        /**
         * 回调告知外部环境，用户选择了哪个账户进行操作，也就是单击了本对话框上的哪个按钮
         * @param selectResult 0：用户选择了普通账户；1：用户选择了融资融券账户。
         *                     这里的数字含义规则和传给H5的loginType入参保持一致。
         */
        void callbackSelectResult(int selectResult);
    }

}
