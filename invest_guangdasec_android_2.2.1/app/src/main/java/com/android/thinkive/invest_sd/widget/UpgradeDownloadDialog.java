package com.android.thinkive.invest_sd.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.thinkive.framework.ThinkiveInitializer;
import com.android.thinkive.framework.download.DownloadItemBean;
import com.android.thinkive.framework.download.DownloadListener;
import com.android.thinkive.framework.download.DownloadManager;
import com.android.thinkive.framework.upgrade.UpgradeInfoBean;
import com.android.thinkive.framework.upgrade.UpgradeManager;
import com.android.thinkive.framework.util.FormatUtil;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.beans.AppVersionBean;
import com.android.thinkive.invest_sd.util.ThinkiveUtil;

/**
 * 新版本更新内容（apk文件或H5增量更新包）下载、安装对话框。
 *
 * Announcements：这是一个单例模式的dialog，修改时小心避免内存溢出。
 *
 * @author 王志鸿
 * @corporation Thinkive
 * @date 2016/3/23
 */
public class UpgradeDownloadDialog extends Dialog {

    /**
     * 外部调用环境
     */
    private Context mContext;
    /**
     * 展示要下载的文件已完成下载的大小
     */
    private TextView mTvFinishPart;
    /**
     * 展示要下载的文件的总大小
     */
    private TextView mTvTotalPart;
    /**
     * “取消”按钮。
     * 非强制更新时按下此按钮取消下载关闭对话框；
     * 强制更新时按下此按钮，关闭App
     */
    private TextView mTvCancel;
    /**
     * “隐藏对话框”按钮，按下后对话框关闭，下载进程后台继续进行。
     */
    private TextView mTvHideDialog;
    /**
     * 展示下载状态（“正在下载”、“已完成”等）
     */
    private TextView mTvDownloadState;
    /**
     * 下载进度条
     */
    private ProgressBar mPbDownload;
    /**
     * {@link #mTvHideDialog}、{@link #mTvCancel}两个按钮之间的间隙
     */
    private View mViewBlank;
    /**
     * 框架中的下载管理器
     */
    private DownloadManager mDownloadManager;
//    /**
//     * 文件的下载地址
//     */
//    private String mDownloadUrl;

    /**
     * 确定本对话框是否是强制更新模式。
     */
    private boolean mIsForceUpdate = false;

    private AppVersionBean mAppVersionBean;
    /**
     * 下载任务的监听回调方法，便于在下载过程中执行一些操作
     */
    private DownloadListener mDownloadListener = new DownloadListener() {

        @Override
        public void onDownloadStarted(DownloadItemBean downloadItemBean) {
            mTvDownloadState.setText(R.string.upgrade_download_state_downloading);
            // 计算并设置文件大小到界面
            long totalSize = downloadItemBean.getTotalSize();
            mTvTotalPart.setText(FormatUtil.getAppSize(totalSize));
        }

        @Override
        public void onProgressUpdate(DownloadItemBean downloadItemBean) {
            // 获取已完成、和全文件的字节大小
            double finishSize = downloadItemBean.getFinishedSize();
            double totalSize = downloadItemBean.getTotalSize();
            // 算出当前进度百分比，并设置
            double progress = finishSize / totalSize * 100;
            mPbDownload.setProgress((int) progress);
            // 计算并设置当前下载完成了多少到界面
            mTvFinishPart.setText(FormatUtil.getAppSize((long) finishSize));
        }

        @Override
        public void onDownloadFailed(DownloadItemBean downloadItemBean, String s) {
            mTvDownloadState.setText(R.string.upgrade_download_state_failed);
        }

        @Override
        public void onDownloadPaused(DownloadItemBean downloadItemBean) {
            mTvDownloadState.setText(R.string.upgrade_download_state_pause);
        }

        @Override
        public void onDownloadResumed(DownloadItemBean downloadItemBean) {
            mTvDownloadState.setText(R.string.upgrade_download_state_resume);
        }

        @Override
        public void onDownloadCanceled(DownloadItemBean downloadItemBean) {
            mTvDownloadState.setText(R.string.upgrade_download_state_cancel);
        }

        @Override
        public void onDownLoadFinished(DownloadItemBean downloadItemBean) {
            mTvDownloadState.setText(R.string.upgrade_download_state_finish);
            UpgradeManager upgradeManager = UpgradeManager.getInstance(mContext);
            // 判断是否是H5升级，然后给upgradeManager设置标志位
            if ("1".equals(mAppVersionBean.getIsH5())) {
                upgradeManager.setUpgradeType(UpgradeManager.UpgradeType.H5);
            } else {
                upgradeManager.setUpgradeType(UpgradeManager.UpgradeType.NATIVE);
            }
            // 将外壳的版本数据类型转换成框架的版本数据类型
            UpgradeInfoBean.VersionInfoBean setVersionInfoBean = new UpgradeInfoBean.VersionInfoBean();
            setVersionInfoBean.setVersion_name(mAppVersionBean.getVersion_name());
            setVersionInfoBean.setDescription(mAppVersionBean.getDescription());
            setVersionInfoBean.setDownload_url(mAppVersionBean.getDownload_url());
            setVersionInfoBean.setUpdate_flag(Integer.parseInt(mAppVersionBean.getUpdate_flag()));
            setVersionInfoBean.setVersion_code(Integer.parseInt(mAppVersionBean.getVersion_code()));
            setVersionInfoBean.setSoft_no(mAppVersionBean.getSoft_no());
            setVersionInfoBean.setSoft_name(mAppVersionBean.getSoft_name());
            setVersionInfoBean.setIsH5(Integer.parseInt(mAppVersionBean.getIsH5()));
            setVersionInfoBean.setIncremental_update(Integer.parseInt(mAppVersionBean.getIncremental_update()));
            // 为框架设置数据
            upgradeManager.setVersionInfoBean(setVersionInfoBean);
            // 安装下载的文件，不一定是apk哦。
            upgradeManager.installUpgradeFile(downloadItemBean.getSavePath());
            // 隐藏本对话框
            dismiss();
        }
    };

    /**
     * 单例模式构造方法，初始化对话框
     * @param context 外部调用环境
     */
    public UpgradeDownloadDialog(Context context) {
        super(context, R.style.dialog);
        mContext = context;
        mDownloadManager = DownloadManager.getInstance(mContext);
        initSetting();
    }

    @Override
    public void show() {
        super.show();
        DownloadItemBean downloadItemBean = mDownloadManager.getDownloadItemInfo(mAppVersionBean.getDownload_url());
        if (downloadItemBean != null) {
            mDownloadManager.updateDownloadTaskListener(downloadItemBean, mDownloadListener);
            // 计算并设置apk文件大小到界面
            long totalSize = downloadItemBean.getTotalSize();
            mTvTotalPart.setText(FormatUtil.getAppSize(totalSize));
        }
    }

    /**
     * 初始化对话框的状态数据
     * 首次显示本类对话框前必须执行本方法
     * @param bean App的版本数据对象
     */
    public void initDialog(AppVersionBean bean) {
        mAppVersionBean = bean;
        mIsForceUpdate = ThinkiveUtil.isForceUpgrade(bean.getUpdate_flag());
        createView();
    }

    private void createView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_upgrade_download, null);
        findViews(rootView);
        if (mIsForceUpdate) { // 强制更新
            mTvHideDialog.setVisibility(View.GONE);
            mViewBlank.setVisibility(View.GONE);
            mTvCancel.setVisibility(View.VISIBLE);
        } else { // 非强制更新
            mTvHideDialog.setVisibility(View.VISIBLE);
            mViewBlank.setVisibility(View.VISIBLE);
            mTvCancel.setVisibility(View.VISIBLE);
        }

        // 设置对话框显示面积
        WindowManager wm = (WindowManager)this.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        // 设置对话框尺寸，宽度为屏幕宽度*0.9，高度为自适应
        this.setContentView(rootView, new ViewGroup.LayoutParams((int) ((double) metrics.widthPixels * 0.9D), ViewGroup.LayoutParams.WRAP_CONTENT));
        //
        setListeners();
    }

    private void initSetting() {
        this.requestWindowFeature(1);
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }

    private void findViews(View view) {
        mTvTotalPart = (TextView) view.findViewById(R.id.tv_download_total_part);
        mTvFinishPart = (TextView) view.findViewById(R.id.tv_download_finish_part);
        mTvCancel = (TextView) view.findViewById(R.id.tv_download_cancel);
        mTvHideDialog = (TextView) view.findViewById(R.id.tv_download_hide);
        mTvDownloadState = (TextView) view.findViewById(R.id.tv_download_state);
        mPbDownload = (ProgressBar) view.findViewById(R.id.pb_download);
        mViewBlank = view.findViewById(R.id.view_download_blank);
    }

    private void setListeners() {
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle(R.string.dialog_title_general_tip);
                if (mIsForceUpdate) { // 强制更新
                    builder.setMessage(R.string.dialog_msg_cancel_upgrade_and_close_force);
                    builder.setNegativeButton(R.string.dialog_ok, new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ThinkiveInitializer.getInstance().exit();
                        }
                    });
                    builder.setPositiveButton(R.string.dialog_not, new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                } else { // 非强制更新

                    builder.setMessage(R.string.dialog_msg_cancel_upgrade_and_close_no_force);
                    builder.setNegativeButton(R.string.dialog_ok, new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            IMApplication.getInstance().exit();
                            dismiss();
                            mDownloadManager.cancelDownload(mAppVersionBean.getDownload_url());
                        }
                    });
                    builder.setPositiveButton(R.string.dialog_not, new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                }
                builder.show();


            }
        });
        mTvHideDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 开始下载新版apk文件
     */
    public void startDownload() {
        // 开始下载任务
        mDownloadManager.startDownloadTask(mAppVersionBean.getDownload_url(), mDownloadListener);
    }

//    /**
//     * 根据服务器返回的标志位，确定标志位的含义，是否是要求进行强制更新
//     * 0：表示不进行强制更新；1：表示进行强制更新
//     *
//     * @param updateFlag 服务器返回的标志位
//     * @return 是否是要求进行强制更新
//     */
//    public static boolean isForceUpgrade(String updateFlag) {
//        boolean result = false;
//        if (TextUtils.isEmpty(updateFlag)) {
//            return result;
//        }
//        if (updateFlag.equals("0")) {
//            result = false;
//        } else if (updateFlag.equals("1")) {
//            result = true;
//        }
//        return result;
//    }
}
