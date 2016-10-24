package com.thinkive.android.trade_bz.a_net.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 网络投票(投票议案信息查询 301531)
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/2
 */
public class NetVoteEntrustBean extends BaseBean {
    /**
     * {"vote_relation":"",
     * "vote_info":"关于继续对外公开转让控股子公司福建省明溪青珩林场有限责任公司股权的议案",
     * "v_id":"1",
     * "meeting_seq":"201648774",
     * "vote_role":"A",
     * "vote_leijino":"",
     * "exchange_type":"1",
     * "ref_code":"600103",
     * "vote_type":"P"}
     */

    /**
     * {"vote_relation":"",
     * "vote_info":"关于选举董事的议案",
     * "v_id":"1.00",
     * "meeting_seq":"201648731",
     * "vote_role":"A",
     * "vote_leijino":"6",
     * "exchange_type":"1",
     * "ref_code":"600057",
     * "vote_type":"L"}
     */


    @JsonKey("meeting_seq")
    private String meeting_seq= ""; //股东大会编码
    @JsonKey("v_id")
    private String v_id= ""; //议案编号
    @JsonKey("vote_info")
    private String vote_info= ""; //议案名称
    @JsonKey("vote_type")
    private String vote_type= ""; //议案类型 P表示普通议案（非累积型议案）  L表示累积型议案
    @JsonKey("vote_leijino")
    private String vote_leijino = ""; //主议案和子议案的标志 有数据表示主议案
    @JsonKey("vote_role")
    private String vote_role= ""; //股东身份
    @JsonKey("vote_relation")
    private String vote_relation= ""; //议案关系
    @JsonKey("stock_account")
    private String stock_account= ""; //股东账号
    @JsonKey("ref_code")
    private String ref_code= ""; //投票产品代码指引
    @JsonKey("exchange_type")
    private String exchange_type= ""; //交易市场类别

    private boolean isCheckAgree = false;
    private boolean isCheckOppose = false;
    private boolean isCheckGiveUp = false;
    private String vote_result = ""; //投票结果 普通：1赞同 2反对 3弃权  累计：固定填1(多个用逗号分隔，)
    private String vote_number = ""; //投票数量 累计投票必填，普通投票填0（默认为0）(多个用逗号分隔，)

    public NetVoteEntrustBean() {

    }

    public String getMeeting_seq() {
        return meeting_seq;
    }

    public void setMeeting_seq(String meeting_seq) {
        this.meeting_seq = meeting_seq;
    }

    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getVote_info() {
        return vote_info;
    }

    public void setVote_info(String vote_info) {
        this.vote_info = vote_info;
    }

    public String getVote_type() {
        return vote_type;
    }

    public void setVote_type(String vote_type) {
        this.vote_type = vote_type;
    }

    public String getVote_leijino() {
        return vote_leijino;
    }

    public void setVote_leijino(String vote_leijino) {
        this.vote_leijino = vote_leijino;
    }

    public String getVote_role() {
        return vote_role;
    }

    public void setVote_role(String vote_role) {
        this.vote_role = vote_role;
    }

    public String getVote_relation() {
        return vote_relation;
    }

    public void setVote_relation(String vote_relation) {
        this.vote_relation = vote_relation;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getRef_code() {
        return ref_code;
    }

    public void setRef_code(String ref_code) {
        this.ref_code = ref_code;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public boolean isCheckAgree() {
        return isCheckAgree;
    }

    public void setCheckAgree(boolean checkAgree) {
        isCheckAgree = checkAgree;
    }

    public boolean isCheckOppose() {
        return isCheckOppose;
    }

    public void setCheckOppose(boolean checkOppose) {
        isCheckOppose = checkOppose;
    }

    public boolean isCheckGiveUp() {
        return isCheckGiveUp;
    }

    public void setCheckGiveUp(boolean checkGiveUp) {
        isCheckGiveUp = checkGiveUp;
    }

    public static Creator<NetVoteEntrustBean> getCREATOR() {
        return CREATOR;
    }

    public String getVote_result() {
        return vote_result;
    }

    public void setVote_result(String vote_result) {
        this.vote_result = vote_result;
    }

    public String getVote_number() {
        return vote_number;
    }

    public void setVote_number(String vote_number) {
        this.vote_number = vote_number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.meeting_seq);
        dest.writeString(this.v_id);
        dest.writeString(this.vote_info);
        dest.writeString(this.vote_type);
        dest.writeString(this.vote_leijino);
        dest.writeString(this.vote_role);
        dest.writeString(this.vote_relation);
        dest.writeString(this.stock_account);
        dest.writeString(this.ref_code);
        dest.writeString(this.exchange_type);
        dest.writeByte(this.isCheckAgree ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCheckOppose ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCheckGiveUp ? (byte) 1 : (byte) 0);
        dest.writeString(this.vote_result);
        dest.writeString(this.vote_number);
    }

    protected NetVoteEntrustBean(Parcel in) {
        this.meeting_seq = in.readString();
        this.v_id = in.readString();
        this.vote_info = in.readString();
        this.vote_type = in.readString();
        this.vote_leijino = in.readString();
        this.vote_role = in.readString();
        this.vote_relation = in.readString();
        this.stock_account = in.readString();
        this.ref_code = in.readString();
        this.exchange_type = in.readString();
        this.isCheckAgree = in.readByte() != 0;
        this.isCheckOppose = in.readByte() != 0;
        this.isCheckGiveUp = in.readByte() != 0;
        this.vote_result = in.readString();
        this.vote_number = in.readString();
    }

    public static final Creator<NetVoteEntrustBean> CREATOR = new Creator<NetVoteEntrustBean>() {
        @Override
        public NetVoteEntrustBean createFromParcel(Parcel source) {
            return new NetVoteEntrustBean(source);
        }

        @Override
        public NetVoteEntrustBean[] newArray(int size) {
            return new NetVoteEntrustBean[size];
        }
    };
}
