package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.android.thinkive.framework.network.ProtocolType;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;

/**
 * 新框架下的请求基类
 * 所有的错误情况都在本类中处理，子类只处理返回了结果，且是正确结果的情况。
 * 本类还提供回调到主线程的方法{@link #transferAction(int, Bundle)}
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/3
 */
public abstract class BaseSocketRequest extends BaseRequest {
    /**
     * @param action
     */
    public BaseSocketRequest(IRequestAction action) {
        super(action);
        mRequestBean.setProtocolType(ProtocolType.SOCKET);
    }
}
