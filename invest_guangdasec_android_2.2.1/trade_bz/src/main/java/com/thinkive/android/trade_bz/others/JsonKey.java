package com.thinkive.android.trade_bz.others;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import java.lang.annotation.*;

/**
 * 给实体类属性标识其对应的服务器json数据的key的注解
 * 在实体类中的各个属性上加上注解，标识其在服务器返回的jsonObject中对应的key。
 * 然后再在我们的json解析工具类{@link JsonParseUtils}中，根据这些注解去解析json数据，生成实体类对象。
 *
 * Announcements：
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/4
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonKey {

    /**
     * 与注解下的属性对应的服务器json key
     */
    String value();
}
