package com.android.thinkive.invest_sd.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.beans.AppVersionBean;
import com.android.thinkive.invest_sd.util.ThinkiveUtil;


/**
 * 新版本更新提示对话框。
 * 当有可用更新的时候，弹出本对话框提示、引导用户进行App版本更新。
 * Announcements：
 *
 * @author 王志鸿
 * @corporation Thinkive
 * @date 2016/3/23
 */
public class UpgradeDialog extends Dialog {
    /**
     * 外部调用环境
     */
    private Context mContext;
    /**
     * 展示新版本更新提示信息
     */
    private TextView mTvUpgradeContent;
    /**
     * “立即更新”按钮
     */
    private TextView mTvUpgradeAtOnce;
    /**
     * “稍后再说”按钮
     */
    private TextView mTvLater;
    /**
     * {@link #mTvLater}、{@link #mTvUpgradeAtOnce}两个按钮之间的空隙
     */
    private View mViewBlank;
    /**
     * 保存新版本信息数据的实体类
     */
    private AppVersionBean mAppVersionBean;
    /**
     * 是否强制更新
     */
    private boolean mIsForceUpdate = false;

    /**
     * 构造方法初始化对话框的布局和布局监听事件。
     *
     * @param context 外部调用环境
     */
    public UpgradeDialog(Context context) {
        super(context, R.style.dialog);
        mContext = context;
        initSetting();
        createView();
        setListeners();
    }

    /**
     * 生成对话框的布局。
     */
    private void createView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_upgrade, null);
        findViews(rootView);
        WindowManager wm = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        this.setContentView(rootView, new android.view.ViewGroup.LayoutParams((int) ((double) metrics.widthPixels * 0.9D), -2));
    }

    /**
     * 初始化对话框的一些属性
     */
    private void initSetting() {
        this.requestWindowFeature(1);
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }

    private void findViews(View view) {
        mTvUpgradeContent = (TextView) view.findViewById(R.id.tv_upgrade_content);
        mTvUpgradeAtOnce = (TextView) view.findViewById(R.id.tv_upgrade_at_once);
        mTvLater = (TextView) view.findViewById(R.id.tv_upgrade_later);
        mViewBlank = view.findViewById(R.id.view_upgrade_blank);
    }

    private void setListeners() {
        mTvUpgradeAtOnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                UpgradeDownloadDialog downloadDialog = new UpgradeDownloadDialog(mContext);
//                downloadDialog.initDialog(mAppVersionBean.getDownload_url(), mIsForceUpdate);
                downloadDialog.initDialog(mAppVersionBean);
                downloadDialog.startDownload();
                downloadDialog.show();
            }
        });
        mTvLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 设置新版本信息的数据到本类中保存
     *
     * @param appVersionBean 保存新版本信息的数据的实体类
     */
    public void setAppVersionBean(AppVersionBean appVersionBean) {
        mAppVersionBean = appVersionBean;
        setContent(mAppVersionBean.getDescription());
        if (mIsForceUpdate =
                ThinkiveUtil.isForceUpgrade(appVersionBean.getUpdate_flag())) {
            mTvLater.setVisibility(View.GONE);
            mViewBlank.setVisibility(View.GONE);
        } else {
            mTvLater.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置本对话框显示的文字内容
     *
     * @param content 本对话框显示的文字内容
     */
    private void setContent(String content) {
        mTvUpgradeContent.setText(Html.fromHtml(ThinkiveUtil.formatInfoContent(content)));
    }
}
