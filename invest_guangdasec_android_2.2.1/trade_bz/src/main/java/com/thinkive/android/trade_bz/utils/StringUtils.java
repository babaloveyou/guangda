package com.thinkive.android.trade_bz.utils;

import android.text.TextUtils;

import com.android.thinkive.framework.CoreApplication;
import com.thinkive.android.trade_bz.R;

public class StringUtils {
    public StringUtils() {

    }

    /**
     * 截取点之前的数据
     */
    public static String subStringBefor(String s) {
        String result = "";
        if (s == null || TextUtils.isEmpty(s)) {
            result = CoreApplication.getInstance().getResources().getString(R.string.no_text_set);
        }else if(s.contains(".")){
//        double str = Double.parseDouble(s.substring(0, s.lastIndexOf(".")));
//        result=TradeUtils.formatDouble(str);
            result = s.substring(0,s.lastIndexOf("."));
            if(TextUtils.isEmpty(result)){
                result=CoreApplication.getInstance().getResources().getString(R.string.no_text_set);
            }
        }else{
            result = s;
        }
        return result;
    }

    /**
     * 截取点之后的数据
     */
    public static String subStringAfter(String s) {
        String result = "";
        if (s == null || s.equals("")) {
            result =  "";
        }else if(s.contains(".")){
            String data = s.substring(s.lastIndexOf("."));
            if(!TextUtils.isEmpty(data)){
                result = data;
            }
        }
        return result;
    }

    public static int getNumOfInnerString(String outString, String innerString) {
         int counter = 0;
        if (outString.indexOf(innerString) == -1) {
            return 0;
        } else if (outString.indexOf(innerString) != -1) {
            counter++;
            getNumOfInnerString(outString.substring(outString.indexOf(innerString) +
                    innerString.length()), innerString);
            return counter;
        }
        return 0;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

//    public static boolean isEquals(String actual, String expected) {
//        return ObjectUtils.isEquals(actual, expected);
//    }
//
//    public static String nullStrToEmpty(String str) {
//        return str == null?"":str;
//    }
//
//    public static String capitalizeFirstLetter(String str) {
//        if(isEmpty(str)) {
//            return str;
//        } else {
//            char c = str.charAt(0);
//            return Character.isLetter(c) && !Character.isUpperCase(c)?(new StringBuilder(str.length())).append(Character.toUpperCase(c)).append(str.substring(1)).toString():str;
//        }
//    }
//
//    public static String utf8Encode(String str) {
//        if(!isEmpty(str) && str.getBytes().length != str.length()) {
//            try {
//                return URLEncoder.encode(str, "UTF-8");
//            } catch (UnsupportedEncodingException var2) {
//                throw new RuntimeException("UnsupportedEncodingException occurred. ", var2);
//            }
//        } else {
//            return str;
//        }
//    }
//
//    public static String utf8Encode(String str, String defaultReturn) {
//        if(!isEmpty(str) && str.getBytes().length != str.length()) {
//            try {
//                return URLEncoder.encode(str, "UTF-8");
//            } catch (UnsupportedEncodingException var3) {
//                return defaultReturn;
//            }
//        } else {
//            return str;
//        }
//    }
//
//    public static String getHrefInnerHtml(String href) {
//        if(isEmpty(href)) {
//            return "";
//        } else {
//            String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
//            Pattern hrefPattern = Pattern.compile(hrefReg, Pattern.CASE_INSENSITIVE);
//            Matcher hrefMatcher = hrefPattern.matcher(href);
//            return hrefMatcher.matches()?hrefMatcher.group(1):href;
//        }
//    }

//    public static String htmlEscapeCharsToString(String source) {
//        return isEmpty(source)?source:source.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
//    }

//    public static String fullWidthToHalfWidth(String s) {
//        if(isEmpty(s)) {
//            return s;
//        } else {
//            char[] source = s.toCharArray();
//
//            for(int i = 0; i < source.length; ++i) {
//                if(source[i] == 12288) {
//                    source[i] = 32;
//                } else if(source[i] >= '！' && source[i] <= '～') {
//                    source[i] -= 'ﻠ';
//                } else {
//                    source[i] = source[i];
//                }
//            }
//
//            return new String(source);
//        }
//    }
//
//    public static String halfWidthToFullWidth(String s) {
//        if(isEmpty(s)) {
//            return s;
//        } else {
//            char[] source = s.toCharArray();
//
//            for(int i = 0; i < source.length; ++i) {
//                if(source[i] == 32) {
//                    source[i] = 12288;
//                } else if(source[i] >= 33 && source[i] <= 126) {
//                    source[i] += 'ﻠ';
//                } else {
//                    source[i] = source[i];
//                }
//            }
//
//            return new String(source);
//        }
//    }
}
