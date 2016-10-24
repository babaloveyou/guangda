package com.android.thinkive.invest_sd.util;

import android.content.Context;
import android.text.TextUtils;

import com.android.thinkive.framework.download.DownloadItemBean;
import com.android.thinkive.framework.download.DownloadManager;
import com.android.thinkive.framework.download.DownloadStatus;
import com.android.thinkive.framework.network.NetWorkService;
import com.android.thinkive.framework.network.RequestBean;
import com.android.thinkive.framework.network.ResponseListener;
import com.android.thinkive.framework.util.AppUtil;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.android.thinkive.invest_sd.beans.AppVersionBean;
import com.android.thinkive.invest_sd.jsonbean.AppVersionJsonBean;
import com.android.thinkive.invest_sd.widget.UpgradeDialog;
import com.android.thinkive.invest_sd.widget.UpgradeDownloadDialog;
import com.thinkive.aqf.utils.ToastUtils;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 版本更新帮助类，内聚检查更新、弹出更新、下载新版本对话框的代码。
 * 这里的单例模式，有空改进成最时髦的懒汉式
 * Announcements：
 *
 * @author 王志鸿
 * @corporation Thinkive
 * @date 2016/5/21
 */
public class UpgradeHelperManager {

    /**
     * 刚刚请求下来的版本信息。
     */
    private AppVersionBean mVersionInfoBean;
    /**
     * 懒汉式单例模式，O(∩_∩)O     ←_←!卖萌可耻
     */
    private static UpgradeHelperManager mInstance;

    /**
     * 单例模式构造方法，初始化本类变量
     */
    private UpgradeHelperManager() {
    }

    /**
     * 单例模式获取本类对象的方法
     *
     * @return 本类的唯一单例对象
     */
    public synchronized static UpgradeHelperManager getInstance() {
        if (mInstance == null) {
            mInstance = new UpgradeHelperManager();
        }
        return mInstance;
    }

    /**
     * 检查更新，并根据检查结果执行相关操作。
     * 首先判断当前是否正在下载新版本，
     * 如果是，则会弹出之前的下载对话框
     * 如果不是，则会调用框架方法发请求检查更新，并根据更新结果做不同处理。
     * 如果有可用的更新，那么弹出新版本更新提示对话框{@link UpgradeDialog}
     *
     * @param context          外部调用环境
     * @param isToastNoUpgrade false：未检查到更新时不提示用户没有检查到更新。
     */
    public void checkUpgrade(Context context, boolean isToastNoUpgrade) {
        if (mVersionInfoBean == null) { // 如果还不曾请求到一个可用的新版本
            // 发起请求，获取服务器上的新版本信息
            requestForNewVersions(context, isToastNoUpgrade);
        } else { // 已得到一个可用的新版本
            String mDownloadUrl = mVersionInfoBean.getDownload_url();
            // 防止服务器上的下载地址为null
            if (!TextUtils.isEmpty(mDownloadUrl)) {
                // 获取当前版本的下载状态
                DownloadStatus downloadStatus = getDownloadState(context, mDownloadUrl);
                if (downloadStatus == null || // 如果还没有开始下载
                        downloadStatus == DownloadStatus.STATUS_CANCELED) { // 或者下载已经取消
                    // 弹出版本更新提示对话框
                    onCheckHasUpgrade(context);
                } else { // 如果已经开始下载
                    // 弹出下载对话框
                    onCheckUpdating(context);
                }
            }
        }

    }

    /**
     * 当前版本已是最新版本时执行
     *
     * @param isToastNoUpgrade 是否弹出toast提示用户当前版本已经是最新版本
     */
    private void onCheckNoUpgrade(Context context, boolean isToastNoUpgrade) {
        if (isToastNoUpgrade) {
            ToastUtils.Toast(context, "已是最新版本");
        }
    }

    /**
     * 当检查到有可用的更新时
     *
     * @param context 外部调用环境
     */
    private void onCheckHasUpgrade(Context context) {
        UpgradeDialog dialog = new UpgradeDialog(context);
        dialog.setAppVersionBean(mVersionInfoBean);
        dialog.show();
    }

    /**
     * 目前正在下载一个可用的更新版本时
     */
    private void onCheckUpdating(Context context) {
        UpgradeDownloadDialog downloadDialog = new UpgradeDownloadDialog(context);
        if (mVersionInfoBean != null) {
            downloadDialog.initDialog(mVersionInfoBean);
            downloadDialog.show();
        }
    }

    /**
     * 获取当前的下载状态
     *
     * @return 当前的下载状态，返回null表示目前还没有开始下载。
     */
    public DownloadStatus getDownloadState(Context context, String downloadUrl) {
        DownloadItemBean downloadItemBean = DownloadManager.getInstance(context).
                getDownloadItemInfo(downloadUrl);
        if (downloadItemBean != null) {
            return downloadItemBean.getStatus();
        } else {
            return null; // 返回null表示目前还没有开始下载。
        }
    }

    /**
     * 发起请求，查询服务器上的可用新版本记录
     */
    private void requestForNewVersions(final Context cnt, final boolean isToastNoUpgrade) {
        RequestBean bean = new RequestBean();
        HashMap<String, String> params = new HashMap<>();
        params.put("channel", "1");
        params.put("soft_no", cnt.getPackageName());
        params.put("version_code", String.valueOf(AppUtil.getVersionCode(cnt)));
        params.put("funcNo", "1108001");
        bean.setUrlName("UPGRADE_URL");
        bean.setParam(params);
        NetWorkService.getInstance().request(bean, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                AppVersionJsonBean jsonben = JsonParseUtil.parseJsonToObject(jsonObject, AppVersionJsonBean.class);
                if (jsonben.getError_no() != 0) {
                    ToastUtils.Toast(cnt, "请求获取新版软件出错！原因是：" + jsonben.getError_info());
                    return;
                }
                // 框架里面已经做了判断，这里我们再象征性判断下，防止报错。
                if (jsonben.getResults() != null && jsonben.getResults().size() > 0) { // 如果有可用的更新
                    AppVersionBean appVersionBean = jsonben.getResults().get(0);
                    if (appVersionBean != null) { // 说明有可用更新
                        mVersionInfoBean = appVersionBean;
                        onCheckHasUpgrade(cnt);
                    }
                } else { // 如果没有可用的更新
                    onCheckNoUpgrade(cnt, isToastNoUpgrade);
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                ToastUtils.Toast(cnt, "请求获取新版软件出错！原因是：" + e.getMessage());
            }
        });
    }
}
