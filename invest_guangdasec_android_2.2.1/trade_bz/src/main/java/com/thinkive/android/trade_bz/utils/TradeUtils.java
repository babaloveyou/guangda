package com.thinkive.android.trade_bz.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.util.DeviceUtil;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.others.tools.ThinkiveTools;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * <p>
 * 描述：交易需要用到的工具类
 * </p>
 *
 * @author 黎丝军、王志鸿
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/6/3
 */
public class TradeUtils {

    /**
     * 获取一个Url的“#！”之前的东西
     *
     * @param url 完整的Url
     */
    public static String getPreUrl(String url) {
        String results = "";
        int nameEndPos = url.indexOf("#!/");
        // 判断url是否包含“#！”，
        if (nameEndPos > 0) { // 包含则用“#！”前的内容作为webview的名字
            results = url.substring(0, nameEndPos);
        } else { // 不包含就直接用整个url作为webview的名字
            results = url;
        }
        return results;
    }

    /**
     * 获取指定位数的随机数字符串
     *
     * @param num 多少位随机数
     * @return 随机数字符串
     */
    public static String getRandomStr(int num) {
        StringBuilder data = new StringBuilder();
        int i = 0;
        for (; i < num; ++i) {
            data.append(Math.floor(Math.random() * 10));
        }
        return data.toString();
    }

    /**
     * 获取前一天时间
     *
     * @return
     */
    public static String getYesterday() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        c.add(Calendar.DAY_OF_WEEK, -1); //将当前日期加7天
        return df.format(c.getTime());
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new Date());
        return date;
    }

    /**
     * 获取一星期前时间
     */
    public static String getLastWeek() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        c.add(Calendar.DAY_OF_WEEK, -7); //将当前日期减7天
        return df.format(c.getTime());
    }

    /**
     * 获取一星期后时间
     */
    public static String getAfterWeek() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        c.add(Calendar.DAY_OF_WEEK, +7); //将当前日期加7天
        return df.format(c.getTime());
    }

    /**
     * 获取60天前时间
     */
    public static String getSixtyDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        c.add(Calendar.DATE, -60); //将当前日期加60天
        return df.format(c.getTime());
    }

    /**
     * 获取一个月前的时间
     */
    public static String getOneMouthDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        c.add(Calendar.MONTH, -1); //将当前日期加一个月
        return df.format(c.getTime());
    }


    /**
     * 获取三个月前的时间
     */
    public static String getThreeMouthDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        c.add(Calendar.MONTH, -3); //将当前日期加三个月
        return df.format(c.getTime());
    }

    /**
     * 获取1年前的时间
     */
    public static String getOneYearBefore() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
        return df.format(c.getTime());
    }

    /**
     * 截取掉股票代码的第一位数
     *
     * @param str
     * @return
     */
    public static String subOneNum(String str) {
        if (str != null && str.length() == 6) {
            str = str.substring(1);
        } else if (str == null || TextUtils.isEmpty(str)) {
            str = "";
        }
        return str;
    }

    /**
     * 取整数，逢千进位
     *
     * @return 0, 000, 000, 00
     */
    public static String formatDouble(Double num) {
        DecimalFormat df = new DecimalFormat(",##0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(num);
    }

    /**
     * 取整数，逢千进位
     *
     * @return 0, 000, 000, 00
     */
    public static String formatDouble(String str) {
        Double num = 0.0;
        if (str != null) {
            num = Double.parseDouble(str);
        }
        DecimalFormat df = new DecimalFormat(",##0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(num);
    }

    /**
     * 逢千进位，保留小数
     *
     * @return 0, 000, 000, 00
     */
    public static String formatDoubleUnit(Double num) {
        DecimalFormat df = new DecimalFormat(",##0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(num);
    }

    /**
     * 保留小数点后三位
     *
     * @param num 处理前的小数
     * @return num保留两位小数后的结果
     */
    public static String formatDouble3(String num) {
        double doubleNum = 0f;
        if (!TextUtils.isEmpty(num)) {
            try {
                doubleNum = Double.parseDouble(num);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return formatDouble3(doubleNum);
    }

    /**
     * 逢千进位，保留小数
     *
     * @return 0, 000, 000, 00
     */
    public static String formatDouble3(Double num) {
        DecimalFormat df = new DecimalFormat("##0.000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(num);
    }

    /**
     * 保留小数点后两位
     *
     * @param num 处理前的小数
     * @return num保留两位小数后的结果
     */
    public static String formatDouble2(String num) {
        double doubleNum = 0f;
        if (!TextUtils.isEmpty(num)) {
            try {
                doubleNum = Double.parseDouble(num);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return formatDouble2(doubleNum);
    }

    /**
     * 保留小数点后两位
     *
     * @param num 处理前的小数
     * @return num保留两位小数后的结果
     */
    public static String formatDouble2(Double num) {
        String str = "";
        //保留小数点后两位
        if (num != 0 && num > 0) {
            DecimalFormat df = new DecimalFormat("##0.00");
            str = df.format(num);
        }
        return str;
    }

    /**
     * 取整数
     *
     * @param num 需要取整的数字
     * @return num取整后的结果
     */
    public static String formatDouble0(String num) {
        Double doubleNum = Double.parseDouble(num);
        return String.valueOf(Integer.parseInt(new DecimalFormat("0").format(doubleNum)));
    }

    /**
     * 银行账号隐藏中间部分
     *
     * @param str
     * @return
     */
    public static String HideSomeData(String str) {
        String result = "";
        try {
            if (str != null && !TextUtils.isEmpty(str)) {
                String start = str.substring(0, 4);
                String end = str.substring(str.length() - 4, str.length());
                result = start + "****" + end;
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 防止科学计数法出现
     *
     * @return 被格式化后的数据，不包含科学计数法中的那个E
     */
    public static String formatDoubleE(String originalNumStr) {
        DecimalFormat df = new DecimalFormat("##0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        double d = 0;
        try {
            d = Double.parseDouble(originalNumStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return df.format(d);
    }

    /**
     * 限制输入框输入小数点后的位数
     *
     * @param editText 对象
     * @param s        内容
     * @param left     小数点左边限制位数
     * @param right    小数点右边限制位数
     */
    public static void onLimitAfterPoint(EditText editText, CharSequence s, int left, int right) {
        if (s == null || TextUtils.isEmpty(s))
            return;
        String text = s.toString();
        //此时已经输入了小数点
        if (s.toString().contains(".")) {
            CharSequence left1 = text.subSequence(0, text.lastIndexOf("."));
            CharSequence right1 = text.subSequence(text.lastIndexOf(".") + 1, s.length());
            if (left1.length() > left) {
                left1 = left1.subSequence(0, left);
                editText.setText(left1 + "." + right1);
                editText.setSelection(editText.getText().length());
            }
            if (right1.length() > right) {
                right1 = right1.subSequence(0, right);
                editText.setText(left1 + "." + right1);
                editText.setSelection(editText.getText().length());
            }
            //还没有输入小数位
        } else if (!s.toString().contains(".")) {
            if (s.length() > left) {
                s = s.subSequence(0, left);
                editText.setText(s);
                editText.setSelection(s.length());
            }
        }
        //当输入的第一位是小数点，前方自动补“0”
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            editText.setText(s);
            editText.setSelection(2);
        }
        //当输入的第一位是0，限制整数位只能输入一位
        if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                editText.setText(s.subSequence(0, 1));
                editText.setSelection(1);
                return;
            }
        }
    }

    /**
     * 按数字大小格式化传入的数字
     * 小于999999的数字显示整数
     * 大于等于1000000时，以万为单位，保留两位小数
     * 大于等于100000000时，已亿为单位，保留两位小数
     *
     * @param num 等待接受格式化的数字
     * @return 格式化完毕后的数字
     */
    public static String formateDataWithQUnit(String num) {
        if (num == null || TextUtils.isEmpty(num))
            return "";
        String result = num;
        //保留小数点后两位
        DecimalFormat df = new DecimalFormat("##0.00");
        //转化成小数
        double doubleNum = Double.parseDouble(num);
        //判断
        if (doubleNum <= 999999) {
            //转化成整数返回
            result = String.valueOf((int) doubleNum);
        } else if (doubleNum >= 1000000) {
            double data = doubleNum / 10000;
            result = df.format(data) + "万";
        } else if (doubleNum >= 100000000) {
            double data = doubleNum / 100000000;
            result = df.format(data) + "亿";
        }
        return result;
    }

    /**
     * 注：根据股票交易规则，单笔委托最大不可超过1000000股
     * 1.若是最大可买大于1000000股，默认返回1000000股
     * 2.若最大可买数不能被100整除，则去减去余数变整数
     *
     * @param intNum 传入进来的最大可买
     * @return 返回最大可买数
     */
    public static String formatNumToHundreds(int intNum) {
        int tempNum = 0;
        if (intNum >= 1000000) {
            tempNum = 1000000;
        } else if (intNum < 1000000) {
            if (intNum <= 100) {
                tempNum = intNum;
            } else if (intNum > 100) {
                if (intNum % 100 != 0) {
                    tempNum = intNum - (intNum % 100);
                } else if (intNum % 100 == 0) {
                    tempNum = intNum;
                }
            }
        }
        return String.valueOf(tempNum);
    }

    public static String formatNumToHundreds(int intNum, int storeUnit) {
        int tempNum = 0;
        if (intNum >= 1000000) {
            tempNum = 1000000;
        } else if (intNum < 1000000) {
            if (intNum <= storeUnit) {
                tempNum = intNum;
            } else if (intNum > storeUnit) {
                if (intNum % storeUnit != 0) {
                    tempNum = intNum - (intNum % storeUnit);
                } else if (intNum % storeUnit == 0) {
                    tempNum = intNum;
                }
            }
        }
        return String.valueOf(tempNum);
    }

    /**
     * 判断开始日期和截止日期的大小
     *
     * @param beginDate
     * @param endDate   1.开始日期不能大于于当前日期
     *                  2.开始日期不能大于截止日期
     */
    public static boolean checkOutDate(String beginDate, String endDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = df.format(new Date());
        try {
            Date begin = df.parse(beginDate);
            Date end = df.parse(endDate);
            Date cur = df.parse(curDate);
            if (begin.getTime() > cur.getTime()) {
                return true;
            }
            if (begin.getTime() > end.getTime()) {
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 正则表达式，判断当前字符串是否为数字类型
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    public static void hideSystemKeyBoard(Activity activity) {
        // 收起系统键盘
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    /**
     * 设置让一个EditText获取焦点并弹出键盘
     *
     * @param activity             外部调用的activity
     * @param editText             键盘的目标EditText
     * @param isShowSystemKeyboard 是否弹出系统的键盘，如果为false，那么弹出的是框架的自绘键盘
     */
    public static void showKeyBoard(final Activity activity, final EditText editText, final boolean isShowSystemKeyboard) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        // 延迟半秒弹出键盘，防止因为界面未加载完而导致键盘弹不出来
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isShowSystemKeyboard) { // 强行弹出系统键盘的代码
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
                } else { // 执行一次EditText的点击事件就可以弹出自绘键盘
                    editText.performClick();
                }
                //                editText.performClick();
            }
        }, 500);
    }

    /**
     * 获取本机当前手机号码
     */
    public static String getCurPhoneNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        return tm.getLine1Number();//获取本机号码
    }

    /**
     * 检测用户是否操作过快
     *
     * @return 用户是否操作过快
     * 注：此处的值不建议修改，为了延长持仓刷新，如果时间过长请使用下一个
     */
    private static long lastClickTime;

    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 3000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 检测用户是否操作过快
     *
     * @return 用户是否操作过快
     */
    private static long lastClickTime2;

    public synchronized static boolean isFastClick2() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime2 < 1000) {
            return true;
        }
        lastClickTime2 = time;
        return false;
    }

    /**
     * 开启个股行情页面。
     *
     * @param context       外部调用环境
     * @param codeTableBean 要打开哪个个股的详情界面
     */
    public static void startPriceDetailStock(Context context, CodeTableBean codeTableBean) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("stockName", codeTableBean.getName());
            jsonObject.put("stockMarket", codeTableBean.getMarket());
            jsonObject.put("stockCode", codeTableBean.getCode());
            jsonObject.put("stockType", codeTableBean.getStockType());
        } catch (JSONException je) {
            je.printStackTrace();
        }
        AppMessage appMessage = new AppMessage(60105, jsonObject);
        appMessage.setTargetModule("self-stock");
        MessageManager.getInstance(context).sendMessage(appMessage);
    }
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
        }
        return versionName;
    }
    /**
     * 设置操作站点
     *
     * @param context
     */
    public static String setOpStation2(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager)context.getSystemService(TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        String phone_no = ThinkiveTools.getDataByMsg(context, "activePhone");
        String macAddress = DeviceUtil.getMacAddress(context);
        // 校验mac地址是否合法
        boolean isMacValid = false;
        if (!TextUtils.isEmpty(macAddress)) {
            for (char macChar : macAddress.toCharArray()) {
                if (macChar != '0' && macChar != ':') {
                    isMacValid = true;
                }
            }
        }

        if (!isMacValid) {
            macAddress = " ";
        }
        String mobile_number = PreferencesUtils.getString(context, "mobile_number");
        String opStation2 =
                       "1" + "|" +
                        DeviceUtil.getIpAddress(context) + "|" +
                        macAddress + "|" +
                        " " + "|" +
                        " " + "|" +
                        (TextUtils.isEmpty(mobile_number) ? " " : "FZ-" + mobile_number) + "|" +
                        "Android " + szImei + "|" +
                        "FZ" + "|" + getAppVersionName(context);


                        LogUtil.printLog("d", "获取到的op_station：" + TradeLoginManager.OP_STATION_2);
        System.out.println("ppppppppppppppppppppppp===" + opStation2);
        return opStation2;
    }
}
