package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.View;

import com.thinkive.android.trade_bz.R;


/**
 *
 * Description：个股期权 确认框 (这里继承AbsTradeDialog，而不直接使用AbsTradeDialog是因为，我想直接在Fragment中使用AbsTradeDiaolog的匿名子类，但是因为AbsTradeDialog中的一些方法都是包可见的，所以重新使用了一个继承类，放宽了访问权限) <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/8/1 <br>
 */
public abstract class AbsOptionComfirmDialog extends AbsTradeDialog{

    public AbsOptionComfirmDialog(Context context) {
        super(context);
        initDialogLayout();
        setLayout();
        setTitleText(R.string.option_tips1);//这个是默认的信息，确认框标题，可以自己覆盖设置的默认值
    }

    @Override
    public void setSubViewToParent(View subView) {
        super.setSubViewToParent(subView);
    }

    @Override
    protected abstract void onClickOk();

    @Override
    public void onClickCancel() {
        super.onClickCancel();
    }
}
