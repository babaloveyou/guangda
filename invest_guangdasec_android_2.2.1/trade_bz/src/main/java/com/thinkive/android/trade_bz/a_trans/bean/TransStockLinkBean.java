package com.thinkive.android.trade_bz.a_trans.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 转股交易证券联动（301700）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/29
 */
public class TransStockLinkBean extends BaseBean {
    /**
     *最大可买数量
     */
    @JsonKey("stock_max_amount")
    private String stock_max_amount ="";
    /**
     *证券类型的一种表示
     * o 两网退市转让 只允许做 OB、OS
     * t 挂牌公司协议转让 只允许做 1e、1f、1g、1h、1j、1k
     * m 挂牌公司做市转让 只允许做 OB、OS
     */
    @JsonKey("stock_trdid")
    private String stock_trdid ="";

    public TransStockLinkBean() {

    }

    public String getStock_max_amount() {
        return stock_max_amount;
    }

    public void setStock_max_amount(String stock_max_amount) {
        this.stock_max_amount = stock_max_amount;
    }

    public String getStock_trdid() {
        return stock_trdid;
    }

    public void setStock_trdid(String stock_trdid) {
        this.stock_trdid = stock_trdid;
    }
}
