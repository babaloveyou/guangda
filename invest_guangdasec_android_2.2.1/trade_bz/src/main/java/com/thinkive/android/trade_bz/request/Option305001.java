package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionExerciseEndDateBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionExercisePriceBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionInfoBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Description：期权代码信息查询 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/18 <br>
 */
public class Option305001 extends BaseOptionRequest{
    public static final String BUNDLE_KEY_STOCK_OPTION="Option305001";
    public static final String BUNDLE_KEY_EXERCISE_END_DATE_OPTION="Option30500102";
    public static final String BUNDLE_KEY_EXERCISE_PRICE_OPTION="Option30500103";

    /**
     *
     * Description：构造方法 <br>
     * Author：晏政清 <br>
     * Corporation：深圳市思迪信息技术股份有限公司 <br>
     * Date：2016/7/18 <br>
     */
    public Option305001(HashMap<String,String> paramMap,IRequestAction action){
        super(action);
        paramMap.put("funcNo", "305001");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    /**
     *
     * Description：对成功返回的结果进行初步处理 <br>
     * Author：晏政清 <br>
     * Corporation：深圳市思迪信息技术股份有限公司 <br>
     * Date：2016/7/18 <br>
     */
    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            Bundle bundle=new Bundle();
            JSONArray resultTargets=jsonObject.getJSONArray("dsName");
            if(resultTargets!=null && resultTargets.length()==3)
            {
                /**
                 * 获取期权代码信息列表
                 */
                String resultTarget = resultTargets.get(0).toString();
                JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
                ArrayList<OptionInfoBean> optionInfoBeans=JsonParseUtils.createBeanList(OptionInfoBean.class, jsonArray);
                bundle.putParcelableArrayList(BUNDLE_KEY_STOCK_OPTION,optionInfoBeans);
                /**
                 * 获取行权到期月份列表
                 */
                String resultTarget2=resultTargets.get(1).toString();
                JSONArray jsonArray2 = jsonObject.getJSONArray(resultTarget2);
                ArrayList<OptionExerciseEndDateBean> optionExerciseEndDateBeans=JsonParseUtils.createBeanList(OptionExerciseEndDateBean.class, jsonArray2);
                bundle.putParcelableArrayList(BUNDLE_KEY_EXERCISE_END_DATE_OPTION,optionExerciseEndDateBeans);
                /**
                 * 获取行权价格列表
                 */
                String resultTarget3=resultTargets.get(2).toString();
                JSONArray jsonArray3 = jsonObject.getJSONArray(resultTarget3);
                ArrayList<OptionExercisePriceBean> optionExercisePriceBeans=JsonParseUtils.createBeanList(OptionExercisePriceBean.class, jsonArray3);
                bundle.putParcelableArrayList(BUNDLE_KEY_EXERCISE_PRICE_OPTION,optionExercisePriceBeans);
            }else if(resultTargets!=null && resultTargets.length()==1){
                /**
                 * 获取期权代码信息列表
                 */
                String resultTarget = resultTargets.get(0).toString();
                JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
                ArrayList<OptionInfoBean> optionInfoBeans=JsonParseUtils.createBeanList(OptionInfoBean.class, jsonArray);
                bundle.putParcelableArrayList(BUNDLE_KEY_STOCK_OPTION,optionInfoBeans);
            }

            transferAction(REQUEST_SUCCESS,bundle);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
