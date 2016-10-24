package com.thinkive.android.trade_bz.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Map Utils：提供Map相关的工具方法
 *
 * @author 徐鑫炎
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015-1-5
 */
public class MapUtils {

    /**
     * key和value之间的默认分隔符
     */
    public static final String DEFAULT_KEY_AND_VALUE_SEPARATOR = ":";
    /**
     * 默认key-value键值对的分隔符
     */
    public static final String DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR = ",";

    /**
     * 是否为null或者size为0
     * <p/>
     * <pre>
     * isEmpty(null)   =   true;
     * isEmpty({})     =   true;
     * isEmpty({1, 2})    =   false;
     * </pre>
     *
     * @param sourceMap 源Map
     * @return 如果map为null或者size为0，返回true，否则返回false
     */
    public static <K, V> boolean isEmpty(Map<K, V> sourceMap) {
        return (sourceMap == null || sourceMap.size() == 0);
    }

    /**
     * 增加键值对到map，key不能为null或者空
     *
     * @param map   目标map
     * @param key   key
     * @param value value
     * @return <ul>
     * <li>如果map为null，返回false</li>
     * <li>如果key为null或者空，返回false</li>
     * <li>return {@link Map#put(Object, Object)}</li>
     * </ul>
     */
    public static boolean putMapNotEmptyKey(Map<String, String> map, String key, String value) {
        if (map == null || StringUtils.isEmpty(key)) {
            return false;
        }

        map.put(key, value);
        return true;
    }

    /**
     * 增加键值对到map，key和value不能为null或者空
     *
     * @param map   目标map
     * @param key   key
     * @param value value
     * @return <ul>
     * <li>如果map为null，返回false</li>
     * <li>如果key为null或者空，返回false</li>
     * <li>如果value为null或者空，返回false</li>
     * <li>return {@link Map#put(Object, Object)}</li>
     * </ul>
     */
    public static boolean putMapNotEmptyKeyAndValue(
      Map<String, String> map, String key, String value) {
        if (map == null || StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return false;
        }

        map.put(key, value);
        return true;
    }

    /**
     * 增加键值对到map，key不能为null或者空，value为空或者null，使用defaultValue
     *
     * @param map          目标map
     * @param key          key
     * @param value        value
     * @param defaultValue 默认值
     * @return <ul>
     * <li>如果map为null，返回false</li>
     * <li>如果key为null或者空，返回false</li>
     * <li>如果value为null或者空，把defaultValue放到到map里面，返回true</li>
     * <li>如果value不为null和空，把value放到到map里面，返回true</li>
     * </ul>
     */
    public static boolean putMapNotEmptyKeyAndValue(Map<String, String> map, String key, String value,
      String defaultValue) {
        if (map == null || StringUtils.isEmpty(key)) {
            return false;
        }

        map.put(key, StringUtils.isEmpty(value) ? defaultValue : value);
        return true;
    }

    /**
     * 增加键值对到map，key不能为null
     *
     * @param map   目标map
     * @param key   key
     * @param value value
     * @return <ul>
     * <li>如果map为null，返回false</li>
     * <li>如果key为null，返回false</li>
     * <li>return {@link Map#put(Object, Object)}</li>
     * </ul>
     */
    public static <K, V> boolean putMapNotNullKey(Map<K, V> map, K key, V value) {
        if (map == null || key == null) {
            return false;
        }

        map.put(key, value);
        return true;
    }

    /**
     * 增加键值对到map，key不能为null，value不能为null
     *
     * @param map   目标map
     * @param key   key
     * @param value value
     * @return <ul>
     * <li>如果map为null，返回false</li>
     * <li>如果key为null，返回false</li>
     * <li>如果value为null，返回false</li>
     * <li>return {@link Map#put(Object, Object)}</li>
     * </ul>
     */
    public static <K, V> boolean putMapNotNullKeyAndValue(Map<K, V> map, K key, V value) {
        if (map == null || key == null || value == null) {
            return false;
        }

        map.put(key, value);
        return true;
    }

    /**
     * 通过值获取对应的key，为从头到尾的第一个匹配项
     * <ul>
     * <strong>注意</strong>
     * <li>对HashMap来说，条目的顺序跟put进来的顺序不一样，建议使用TreeMap</li>
     * </ul>
     *
     * @param <V>
     * @param map   源map
     * @param value 值
     * @return <ul>
     * <li>如果map为null，返回null</li>
     * <li>如果value存在，返回key</li>
     * <li>如果value不存在，返回null</li>
     * </ul>
     */
    public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        if (isEmpty(map)) {
            return null;
        }

        for (Entry<K, V> entry : map.entrySet()) {
            if (ObjectUtils.isEquals(entry.getValue(), value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 解析键值对为map，忽略空键
     * <p/>
     * <pre>
     * parseKeyAndValueToMap("","","",true)=null
     * parseKeyAndValueToMap(null,"","",true)=null
     * parseKeyAndValueToMap("a:b,:","","",true)={(a,b)}
     * parseKeyAndValueToMap("a:b,:d","","",true)={(a,b)}
     * parseKeyAndValueToMap("a:b,c:d","","",true)={(a,b),(c,d)}
     * parseKeyAndValueToMap("a=b, c = d","=",",",true)={(a,b),(c,d)}
     * parseKeyAndValueToMap("a=b, c = d","=",",",false)={(a, b),( c , d)}
     * parseKeyAndValueToMap("a=b, c=d","=", ",", false)={(a,b),( c,d)}
     * parseKeyAndValueToMap("a=b; c=d","=", ";", false)={(a,b),( c,d)}
     * parseKeyAndValueToMap("a=b, c=d", ",", ";", false)={(a=b, c=d)}
     * </pre>
     *
     * @param source                   源数据
     * @param keyAndValueSeparator     键值分隔符
     * @param keyAndValuePairSeparator 键值对分隔符
     * @param ignoreSpace              是否忽略开头或者结尾的空格
     * @return
     */
    public static Map<String, String> parseKeyAndValueToMap(String source, String keyAndValueSeparator,
      String keyAndValuePairSeparator, boolean ignoreSpace) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        if (StringUtils.isEmpty(keyAndValueSeparator)) {
            keyAndValueSeparator = DEFAULT_KEY_AND_VALUE_SEPARATOR;
        }
        if (StringUtils.isEmpty(keyAndValuePairSeparator)) {
            keyAndValuePairSeparator = DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR;
        }
        Map<String, String> keyAndValueMap = new HashMap<String, String>();
        String[] keyAndValueArray = source.split(keyAndValuePairSeparator);
        if (keyAndValueArray == null) {
            return null;
        }

        int seperator;
        for (String valueEntity : keyAndValueArray) {
            if (!StringUtils.isEmpty(valueEntity)) {
                seperator = valueEntity.indexOf(keyAndValueSeparator);
                if (seperator != -1) {
                    if (ignoreSpace) {
                        MapUtils.putMapNotEmptyKey(keyAndValueMap, valueEntity.substring(0, seperator).trim(),
                          valueEntity.substring(seperator + 1).trim());
                    } else {
                        MapUtils.putMapNotEmptyKey(keyAndValueMap, valueEntity.substring(0, seperator),
                          valueEntity.substring(seperator + 1));
                    }
                }
            }
        }
        return keyAndValueMap;
    }

    /**
     * 解析键值对为map，忽略空键
     *
     * @param source      源数据
     * @param ignoreSpace 是否忽略开头或者结尾的空格
     * @return
     * @see {@link MapUtils#parseKeyAndValueToMap(String, String, String, boolean)}, 键值分隔符为
     * {@link #DEFAULT_KEY_AND_VALUE_SEPARATOR}，键值对分隔符为{@link #DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR}
     */
    public static Map<String, String> parseKeyAndValueToMap(String source, boolean ignoreSpace) {
        return parseKeyAndValueToMap(
          source,
          DEFAULT_KEY_AND_VALUE_SEPARATOR,
          DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR,
          ignoreSpace);
    }

    /**
     * 解析键值对为map，忽略空键，忽略开头或者结尾的空格
     *
     * @param source 源数据
     * @return
     * @see {@link MapUtils#parseKeyAndValueToMap(String, String, String, boolean)}, 键值分隔符为
     * {@link #DEFAULT_KEY_AND_VALUE_SEPARATOR}，键值对分隔符为{@link #DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR}
     */
    public static Map<String, String> parseKeyAndValueToMap(String source) {
        return parseKeyAndValueToMap(
          source,
          DEFAULT_KEY_AND_VALUE_SEPARATOR,
          DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR,
          true);
    }

    /**
     * map转换为Json格式字符串
     *
     * @param map 源map
     * @return Json格式字符串
     */
    public static String toJson(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }

        StringBuilder paras = new StringBuilder();
        paras.append("{");
        Iterator<Entry<String, String>> ite = map.entrySet().iterator();
        while (ite.hasNext()) {
            Entry<String, String> entry = (Entry<String, String>) ite.next();
            paras.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
            if (ite.hasNext()) {
                paras.append(",");
            }
        }
        paras.append("}");
        return paras.toString();
    }
}
