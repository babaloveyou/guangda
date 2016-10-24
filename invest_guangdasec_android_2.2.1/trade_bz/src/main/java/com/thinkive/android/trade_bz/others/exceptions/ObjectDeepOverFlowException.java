package com.thinkive.android.trade_bz.others.exceptions;

/**
 * 描述：对象继承深度过深
 *
 * @author xuhao
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015-02-17
 */
public class ObjectDeepOverFlowException extends RuntimeException {
    public ObjectDeepOverFlowException() {
    }

    public ObjectDeepOverFlowException(String detailMessage) {
        super(detailMessage);
    }

    public ObjectDeepOverFlowException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ObjectDeepOverFlowException(Throwable throwable) {
        super(throwable);
    }
}
