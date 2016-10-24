package com.android.thinkive.invest_sd.util;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;

/**
 * Created by liujianwei on 15/6/18. RSA加密解密工具类
 */
public class RSAUtil {

	/** 可以先注册到虚拟机中,再通过名称使用;也可以不注册,直接传入使用 */
	public static final Provider pro = new BouncyCastleProvider();

	/**
	 * 采用公钥加密
	 * 
	 * @param modulus
	 * @param publicExponent
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String encryptByPublicKey(String modulus,
			String publicExponent, String content) throws Exception {
		byte[] modulusArray =  new BigInteger(modulus, 16).toByteArray();
		byte[] publicExponentArray =new BigInteger(publicExponent, 16).toByteArray();
		RSAPublicKey publicKey = generateRSAPublicKey(modulusArray,
				publicExponentArray);
		byte[] encryMessage = encrypt(publicKey, (" " +content).getBytes("UTF-8"));
		encryMessage = Hex.encode(encryMessage);
		return new String(encryMessage, "UTF-8");
//		return bytesToHex(encryMessage);
	}

	/**
	 * * 生成公钥 *
	 *
	 * @param modulus
	 *            *
	 * @param publicExponent
	 *            *
	 * @return RSAPublicKey *
	 * @throws Exception
	 */
	private static RSAPublicKey generateRSAPublicKey(byte[] modulus,
			byte[] publicExponent) throws Exception {
		KeyFactory keyFac = null;
		try
		{
			keyFac = KeyFactory.getInstance("RSA", pro);
		}
		catch (NoSuchAlgorithmException ex)
		{
			throw new Exception(ex.getMessage());
		}
		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
		try
		{
			return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
		}
		catch (InvalidKeySpecException ex)
		{
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * * 加密 *
	 *
	 * @param pk
	 *            加密的密钥 *
	 * @param data
	 *            待加密的明文数据 *
	 * @return 加密后的数据 *
	 * @throws Exception
	 */
	private static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("RSA", pro);
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			byte[] raw = cipher.doFinal(data);
			return raw;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 将2进制字节数组转换为16进制字符串
	 *
	 * @param bytes
	 *            字节数组
	 * @return String hex 返回16进制内容的字符串，比较类似UDB的密钥
	 */
	public static String bytesToHex(byte[] bytes) {
		// 16进制结果
		String hex = "";
		// 存放byte字节对象的临时变量
		String temp = "";

		// 对字节数组元素进行处理
		for (int i = 0; i < bytes.length; i++) {
			// byte的取值范围是从-127-128，需要& 0xFF (11111111) 使得byte原来的负值变成正的
			temp = Integer.toHexString(bytes[i] & 0xFF);
			// 长度为1，那么需要补充一位 0
			if (temp.length() == 1) {
				hex = hex + "0" + temp;
			} else {
				// 长度为2，直接拼接即可
				hex = hex + temp;
			}
		}
		// 返回大写的字符串
		// return hex.toUpperCase();
		return hex;
	}

	/**
	 * * 解密 *
	 *
	 * @param pk 解密的密钥 *
	 * @param raw 已经加密的数据 *
	 * @return 解密后的明文 *
	 * @throws Exception
	 */
	public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception
	{
		Cipher cipher = Cipher.getInstance("RSA", pro);
		cipher.init(Cipher.DECRYPT_MODE, pk);
		byte[] re = cipher.doFinal(raw);
		return re;
	}

	/**
	 * * 生成私钥 *
	 *
	 * @param modulus         *
	 * @param privateExponent *
	 * @return RSAPrivateKey *
	 * @throws Exception
	 */
	public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception
	{
		KeyFactory keyFac = null;
		try
		{
			keyFac = KeyFactory.getInstance("RSA", pro);
		}
		catch (NoSuchAlgorithmException ex)
		{
			throw new Exception(ex.getMessage());
		}
		RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
		try
		{
			return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
		}
		catch (InvalidKeySpecException ex)
		{
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * 签名
	 *
	 * @param signedMsg
	 * @param key
	 * @return
	 */
	public static String sign(String signedMsg, String key) {
		try {
			MessageDigest digit = MessageDigest.getInstance("MD5");
			digit.update((signedMsg + key).getBytes("UTF-8"));
			return byte2hex(digit.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * byte array to hex
	 *
	 * @param b
	 *            byte array
	 * @return hex string
	 */
	public static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp;
		for (int i = 0; i < b.length; i++) {
			stmp = Integer.toHexString(b[i] & 0xFF).toUpperCase();
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			} else {
				hs.append(stmp);
			}
		}
		return hs.toString();
	}

	/**
	 * 转换成url参数
	 *
	 * @param map
	 * @param isSort
	 *            是否排序
	 * @param removeKey
	 *            不包含的key元素集
	 * @return
	 */
	public static String getURLParam(Map map, boolean isSort, Set removeKey) {
		StringBuffer param = new StringBuffer();
		List msgList = new ArrayList();
		for (Iterator it = map.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			String value = (String) map.get(key);
			if (removeKey != null && removeKey.contains(key)) {
				continue;
			}
			msgList.add(key + "=" + value);
		}

		if (isSort) {
			// 排序
			Collections.sort(msgList);
		}

		for (int i = 0; i < msgList.size(); i++) {
			String msg = (String) msgList.get(i);
			if (i > 0) {
				param.append("&");
			}
			param.append(msg);
		}

		return param.toString();
	}
}
