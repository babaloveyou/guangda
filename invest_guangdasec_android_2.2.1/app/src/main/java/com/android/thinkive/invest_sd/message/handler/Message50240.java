package com.android.thinkive.invest_sd.message.handler;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.WindowManager;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.download.DownloadDialog;
import com.android.thinkive.framework.download.DownloadItemBean;
import com.android.thinkive.framework.download.DownloadListener;
import com.android.thinkive.framework.download.DownloadManager;
import com.android.thinkive.framework.download.DownloadStatus;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.util.FormatUtil;
import com.android.thinkive.framework.util.Log;
import com.lm.artifex.mupdfdemo.MuPDFActivity;

import org.json.JSONObject;

/**
 * Created by liujianwei on 15/6/30.
 */
public class Message50240 implements IMessageHandler {
    private DownloadDialog mDownloadDialog;
    private Context mContext;

    @Override
    public String handlerMessage(Context context, AppMessage appMessage) {
        mContext = context;
        JSONObject content = appMessage.getContent();
        final String url = content.optString("url");
        String title = content.optString("title");
        if (TextUtils.isEmpty(url)) {
            return MessageManager.getInstance(context).buildMessageReturn(-5024001, "pdf文件链接不能为空", null);
        }
        DownloadItemBean downloadItemBean = DownloadManager.getInstance(context).getDownloadItemInfo(url);
        if (downloadItemBean != null && downloadItemBean.getStatus() == DownloadStatus.STATUS_FINISHED) {
            String filePath = downloadItemBean.getSavePath();
            Log.d(" 50240 open pdf ========== ");
            openPDF(filePath);
        } else {
            Log.d(" 50240 download pdf ========== ");
            loadPdf(url, title);
        }
        return MessageManager.getInstance(context).buildMessageReturn(1, null, null);
    }

    private void loadPdf(final String url, String title) {
        DownloadManager.getInstance(mContext).startDownloadTask(url, new DownloadListener() {
            @Override
            public void onDownloadStarted(DownloadItemBean downloadItemBean) {

            }

            @Override
            public void onProgressUpdate(DownloadItemBean itemBean) {
                int progress = (int) (itemBean.getFinishedSize() * 100 / itemBean.getTotalSize());
                long finishedSize = itemBean.getFinishedSize();
                long totalSize = itemBean.getTotalSize();
                mDownloadDialog.setTitleContent("下载中...");
                mDownloadDialog.setProgressBarVaule(progress);
                mDownloadDialog.setDownLoadFinishedSize("已完成: " + FormatUtil.getAppSize(finishedSize) + "/" + FormatUtil.getAppSize(totalSize));
                mDownloadDialog.setDownloadPercent(FormatUtil.getPercent(finishedSize, totalSize));
            }

            @Override
            public void onDownloadFailed(DownloadItemBean downloadItemBean, String s) {
                mDownloadDialog.setTitleContent("下载失败");
                mDownloadDialog.setRightButtonContent("我知道了");
            }

            @Override
            public void onDownloadPaused(DownloadItemBean downloadItemBean) {

            }

            @Override
            public void onDownloadResumed(DownloadItemBean downloadItemBean) {

            }

            @Override
            public void onDownloadCanceled(DownloadItemBean downloadItemBean) {
                if (mDownloadDialog != null && mDownloadDialog.isShowing()) {
                    mDownloadDialog.dismiss();
                }
            }

            @Override
            public void onDownLoadFinished(DownloadItemBean downloadItemBean) {
                if (mDownloadDialog.isShowing()) {
                    mDownloadDialog.dismiss();
                }
                String filePath = downloadItemBean.getSavePath();
                Log.d("upgrade file path = " + filePath);
                openPDF(filePath);
            }
        });
        CoreApplication.getInstance().getHandler().post(new Runnable() {
            @Override
            public void run() {
                mDownloadDialog = new DownloadDialog(mContext, url);
                mDownloadDialog.setProgressBarMax(100);
                mDownloadDialog.setProgressBarVaule(0);
                mDownloadDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                mDownloadDialog.show();
            }
        });
    }


    private void openPDF(String filePath) {
        Intent intent = new Intent(mContext, MuPDFActivity.class);
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_VIEW);
        intent.putExtra("filePath", filePath);
        mContext.startActivity(intent);
        Log.i("下载成功，正在打开PDF文件：" + filePath);
    }


}
