package com.thinkive.android.trade_bz.utils;

import android.content.Context;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.dialog.LoadingDialog;

/**
 * 状态加载框工具类
 */

public class LoadingDialogUtil {
    private LoadingDialog loadingDialog;//请求数据状态框
    private Context context;//上下文

    public LoadingDialogUtil(Context context) {
        this.loadingDialog = new LoadingDialog(context);
        this.context = context;
    }

    /**
     *
     * Description：显示请求数据状态框,其中resText为要显示的提示内容的id，默认传0时，显示内容为R.string.querying_data <br>
     * Author：晏政清 <br>
     * Corporation：深圳市思迪信息技术股份有限公司 <br>
     * Date：2016/9/5 <br>
     */
    public void showLoadingDialog(int resText){
        if(!loadingDialog.isShowing()){
            if(resText == 0){
                loadingDialog.show(R.string.querying_data);
            }else{
                loadingDialog.show(resText);
            }
        }
    }

    /**
     * 隐藏显示数据状态框
     */
    public void hideDialog(){
        if(loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
    }
}
