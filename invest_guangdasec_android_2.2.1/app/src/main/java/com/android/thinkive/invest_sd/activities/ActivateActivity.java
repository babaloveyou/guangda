package com.android.thinkive.invest_sd.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKActivity;
import com.android.thinkive.framework.storage.DatabaseStorage;
import com.android.thinkive.framework.util.PreferencesUtil;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.constants.Constant;
import com.android.thinkive.invest_sd.util.ActivateManger;
import com.android.thinkive.invest_sd.widget.LoadingDialog;
import com.thinkive.aqf.info.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * <p>
 * 描述：
 * </p>
 *
 * @author 刘剑威
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/5/6
 */
public class ActivateActivity extends TKActivity implements View.OnClickListener {
    private final static String ACTIVATE_FLAG = "is_activated";
    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private final static String SERVER_PHONE_NUMBER = "";
    private final static String SMS_PDUS = "pdus";
    private final static int RESEND_VERIFY_CODE = 0x1000;
    private final static int SET_VERIFY_CODE = 0x1001;

    private EditText mMobileEdtTxt;
    private EditText mVerifyCodeEdtTxt;
    private TextView mSendBtn;
    private Button mActivateBtn;
    private Button mIgnoreBtn;
    private ImageView mBackIv;
    private LoadingDialog loadingDialog;
    private int mDuration = 120;
    private String mVerifyCode;
    private String mMobile;
    private View but_clear_phone;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case RESEND_VERIFY_CODE:
                    if(mDuration > 0){
                        Message message = Message.obtain(mHandler,RESEND_VERIFY_CODE);
                        mHandler.sendMessageDelayed(message,1000);
                        disableResend();
                        mSendBtn.setText(getString(R.string.activate_resend_verify_code,mDuration));
                        mDuration--;
                    }else{
                        enableResend();
                        mSendBtn.setText(R.string.activate_send_verify_code);
                        mDuration = 120;
                    }
                    break;
                case SET_VERIFY_CODE:
                    String verifyCode = (String) msg.obj;
                    mVerifyCodeEdtTxt.setText(verifyCode);
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    private BroadcastReceiver mSmsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
                SmsMessage[] messages = getMessagesFromIntent(intent);
                for (SmsMessage message : messages) {
                    String phoneNumber = message.getOriginatingAddress();
                    String body = message.getDisplayMessageBody();
                    Log.d("","phoneNumber = " + phoneNumber + " body = " + body);
                    if (phoneNumber != null && phoneNumber.equals(SERVER_PHONE_NUMBER)) {
                        if (body.length() > 6) {
                            String verifyCodeStr = body.substring(0, 6);
                            try {
                                int verifyCode = Integer.parseInt(verifyCodeStr);
                                Message msg = Message.obtain(mHandler, SET_VERIFY_CODE);
                                msg.obj = String.valueOf(verifyCode);
                                mHandler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        public SmsMessage[] getMessagesFromIntent(Intent intent) {
            Object[] messages = (Object[]) intent.getSerializableExtra(SMS_PDUS);
            byte[][] pduObjs = new byte[messages.length][];
            for (int i = 0; i < messages.length; i++) {
                pduObjs[i] = (byte[]) messages[i];
            }
            byte[][] pdus = new byte[pduObjs.length][];
            int pduCount = pdus.length;
            SmsMessage[] msgs = new SmsMessage[pduCount];
            for (int i = 0; i < pduCount; i++) {
                pdus[i] = pduObjs[i];
                msgs[i] = SmsMessage.createFromPdu(pdus[i]);
            }
            return msgs;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate);
        findViews();
        initData();
        initViews();
        setListeners();
    }

    protected void findViews() {
        mMobileEdtTxt = (EditText) findViewById(R.id.edtTxt_mobile_number);
        mVerifyCodeEdtTxt = (EditText) findViewById(R.id.edtTxt_verify_code);
        mSendBtn = (TextView) findViewById(R.id.btn_send_verify_code);
        mActivateBtn = (Button) findViewById(R.id.btn_activate);
        mIgnoreBtn = (Button) findViewById(R.id.btn_ignore);
        mBackIv = (ImageView) findViewById(R.id.iv_back);
        but_clear_phone =  findViewById(R.id.but_clear_phone);

    }

    protected void setListeners() {
        but_clear_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMobileEdtTxt.setText("");
            }
        });
        mMobileEdtTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(mMobileEdtTxt!=null&&mMobileEdtTxt.getText()!=null&&mMobileEdtTxt.getText().toString().length()>0){
                    but_clear_phone.setVisibility(View.VISIBLE);
                }else{
                    but_clear_phone.setVisibility(View.GONE);
                }
            }
        });
        mSendBtn.setOnClickListener(this);
        mActivateBtn.setOnClickListener(this);
        mIgnoreBtn.setOnClickListener(this);
        mBackIv.setOnClickListener(this);
    }

    protected void initData() {
    }

    protected void initViews() {
        loadingDialog = new LoadingDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send_verify_code:
                mMobile = mMobileEdtTxt.getText().toString();
                if(TextUtils.isEmpty(mMobile)||mMobile.length()!=11){
                    ToastUtils.Toast(ActivateActivity.this,"请输入正确的手机号码");
                    return;
                }
                loadingDialog.show("正在发送验证码");
                ActivateManger.getInstance(this).sendmsg(mMobile, new ActivateManger.ActivateCallback() {
                    @Override
                    public void onSucceed(String flag) {
                        loadingDialog.dismiss();
                        ToastUtils.Toast(ActivateActivity.this,"已经发送手机验证码到您的手机");
                        startCountDown();
                    }

                    @Override
                    public void onFailed(String errorinfo) {
                        loadingDialog.dismiss();
                        ToastUtils.Toast(ActivateActivity.this,"发送验证码失败:"+errorinfo);
                    }
                });

                break;
            case R.id.btn_activate:
                mMobile = mMobileEdtTxt.getText().toString();
                mVerifyCode = mVerifyCodeEdtTxt.getText().toString();
                if(TextUtils.isEmpty(mMobile)||mMobile.length()!=11){
                    ToastUtils.Toast(ActivateActivity.this,"请输入正确的手机号码");
                    return;
                }
                if(TextUtils.isEmpty(mVerifyCode)){
                    ToastUtils.Toast(ActivateActivity.this,"验证码不能为空");
                    return;
                }
                loadingDialog.show("正在激活");
                ActivateManger.getInstance(this).activatemsg(mMobile, mVerifyCode, new ActivateManger.ActivateCallback() {
                @Override
                public void onSucceed(String flag) {
                    loadingDialog.dismiss();
                    if(mMobile != null && !TextUtils.isEmpty(mMobile)) {
                        PreferencesUtil.putString(ActivateActivity.this, Constant.MOBILE_NUMBER, mMobile);
                        DatabaseStorage storage = new DatabaseStorage(ActivateActivity.this);
                        storage.saveData("mobilePhone", mMobile);
                    }
                    ToastUtils.Toast(ActivateActivity.this, "激活成功");
                    Intent intent = new Intent();
                    intent.putExtra("isactivite",true);
                    setResult(RESULT_OK, intent);
                    finish();
                }

                @Override
                public void onFailed(String errorinfo) {
                    loadingDialog.dismiss();
                    ToastUtils.Toast(ActivateActivity.this, "激活失败:" + errorinfo);
                }
            });
                break;
            case R.id.btn_ignore:
                Intent intent = new Intent();
                intent.putExtra("isactivite",false);
                setResult(RESULT_OK, intent);
                this.finish();
                break;
            case R.id.iv_back:
                Intent intent1 = new Intent();
                intent1.putExtra("isactivite",false);
                setResult(RESULT_OK, intent1);
                this.finish();
             break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (mHandler.hasMessages(RESEND_VERIFY_CODE)) {
                mHandler.removeMessages(RESEND_VERIFY_CODE);
            }
            if (mHandler.hasMessages(SET_VERIFY_CODE)) {
                mHandler.removeMessages(SET_VERIFY_CODE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        unregisterReceiver(mSmsReceiver);
    }


    private void disableResend(){
        mSendBtn.setEnabled(false);
        mSendBtn.setClickable(false);
        mSendBtn.setTextColor(Color.parseColor("#999999"));
    }

    private void enableResend(){
        mSendBtn.setEnabled(true);
        mSendBtn.setClickable(true);
        mSendBtn.setTextColor(Color.parseColor("#4359aa"));
    }

    public void startCountDown(){
        Message msg = Message.obtain(mHandler,RESEND_VERIFY_CODE);
        mHandler.sendMessage(msg);
    }

}
