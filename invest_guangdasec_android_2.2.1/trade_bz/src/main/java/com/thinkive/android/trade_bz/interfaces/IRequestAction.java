package com.thinkive.android.trade_bz.interfaces;

import android.content.Context;
import android.os.Bundle;

/**
 * 请求子线程程序回调到主线程用的接口。
 *
 * Announcements：
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/6
 */
public interface IRequestAction {

    /**
     * 请求成功，且数据正常获取的时候执行
     * @param context
     * @param bundle
     */
    void onSuccess(Context context, Bundle bundle);

    /**
     * 请求失败，包括服务器返回正确结果，但前端程序解析数据时出错导致的失败时执行。
     * @param context
     * @param bundle
     */
    void onFailed(Context context, Bundle bundle);
}
