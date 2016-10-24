package com.android.thinkive.invest_sd.message.handler;

import android.content.Context;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.util.EncryptUtil;
//import com.chinapay.authplugin.activity.Initialize;
//import com.chinapay.authplugin.util.CPGlobaInfo;
//import com.chinapay.authplugin.util.Utils;

import org.json.JSONObject;

/**
 * Created by liujianwei on 15/7/17.
 */
public class Message60300 implements IMessageHandler {
    private String env;
    private String appSysId;
    private String cardNo;
    private String cerType;
    private String cerNo;
    private String cerName;
    private String cardMobile;
    private String privateKey;
    private Context mContext;

    @Override
    public String handlerMessage(Context context, AppMessage appMessage) {
        mContext = context;
        JSONObject content = appMessage.getContent();
        env = content.optString("env");
        appSysId = content.optString("appSysId");
        cardNo = content.optString("cardNo");
        cerType = content.optString("cerType");
        cerNo = content.optString("cerNo");
        cerName = content.optString("cerName");
        cardMobile = content.optString("cardMobile");
        privateKey = content.optString("privateKey");
        startAuthActiviy();
        return MessageManager.getInstance(context).buildMessageReturn(1, null, null);
    }

    private String buildXmlParam() {
        String xmlParam = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
                + "<CpPay application=\"LunchPay.Req\">"
                + "<env>" + env + "</env>"
                + "<appSysId>" + appSysId + "</appSysId>"
                + "<cardNo>" + cardNo + "</cardNo>"
                + "<cerType>" + cerType + "</cerType>"
                + "<cerNo>" + cerNo + "</cerNo>"
                + "<cerName>" + cerName + "</cerName>"
//                + "<cardMobile>" + cardMobile + "</cardMobile>"
                + "<sign>" + EncryptUtil.encryptToMD5(appSysId + cardNo + cerType + cerNo + cerName + privateKey) + "</sign>"
                + "</CpPay>";
        return xmlParam;
    }

    private void startAuthActiviy() {
//        Utils.setPackageName(mContext.getPackageName());
//        Intent intent = new Intent(mContext, Initialize.class);
//        String xmlParam = buildXmlParam();
//        Log.d("50275 xml Param = " + xmlParam);
//        intent.putExtra(CPGlobaInfo.XML_TAG, xmlParam);
//        mContext.startActivity(intent);
    }
}
