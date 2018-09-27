package com.hitler.core.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

import org.springframework.util.StringUtils;


/**
 * 加密工具类
 * @author onsoul
 * 2018年8月3日 下午2:28:03
 */
public class EncryptUtil {

	private static final int MAX_LENGTH = 32;

	/**
	 * user pwd sha512
	 * @param text
	 *            salt+password
	 * @return result
	 */
	public static String sha512(String text) {
		MessageDigest sha512Digest;
		try {
			sha512Digest = MessageDigest.getInstance("SHA-512");
			byte[] result = sha512Digest.digest(text.getBytes());
			for (int i = 0; i < 512; i++) {
				result = sha512Digest.digest(result);
			}
			return Base64.getEncoder().encodeToString(result);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 构建盐
	public static String salt(String text) {
		if (!StringUtils.isEmpty(text)) {
			text += System.currentTimeMillis();
			String encode = Base64.getEncoder().encodeToString(text.getBytes());
			return (encode.length() > MAX_LENGTH) ? encode.substring(0, MAX_LENGTH) : encode;
		}
		return null;
	}

	public static String makeToken() {
		String id = UUID.randomUUID().toString().replace("-", "");
		return md5(id);
	}

	/**
	 * 对字符串md5加密
	 *
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * base64加密方法
	 * @param plainText
	 * @return
	 */
	public static String encodedBase64(String plainText) {
		String encoded = null;
		try {
			byte[] bytes = plainText.getBytes("UTF-8");
			encoded = Base64.getEncoder().encodeToString(bytes);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encoded;
	}

	/**
	 * base64解密方法
	 * @param plainText
	 * @return
	 */
	public static String decodedBase64(String plainText) {
		byte[] decoded = null;
		try {
			byte[] bytes = plainText.getBytes("UTF-8");
			decoded = Base64.getDecoder().decode(bytes);
			return new String(decoded);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * description: 订单code生成器
	 * @param 
	 * @return {@link }
	 * createdBy:wanhao
	 * created:2018年08月02日
	 * */
	public static void getOrderId(){
		Integer hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {//有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		String orderId= new Random().nextInt(9) + String.format("%015d", System.currentTimeMillis() + hashCodeV);
		System.out.println(orderId.length());
	}
	

}
