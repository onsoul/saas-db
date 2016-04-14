package com.hitler.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang.StringUtils {
	
	public static List<Integer> toIntegerList(String str, String regex) {
		StringTokenizer t = new StringTokenizer(str, regex);
		List<Integer> l = new ArrayList<Integer>();
		while (t.hasMoreElements()) {
			String s = t.nextToken();
			if (isNotBlank(s)) {
				l.add(Integer.parseInt(s));
			}
		}
		return l;
	}
	
	public static Integer[] toIntegerArray(String str, String regex) {
		List<Integer> list = toIntegerList(str, regex);
		Integer[] arr = new Integer[list.size()];
		return list.toArray(arr);
	}
	
	/**
	 * 校验字符串是否是不以0开头的纯数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeral(String str) {
		return str.matches("^[1-9]\\d*$");
	}
	/**
	 * 校验字符串是否整型(0开头也可以)
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String input){  
        Matcher mer = Pattern.compile("^[+-]?[0-9]+$").matcher(input);  
        return mer.find();  
    }  
	
	public static List<String> partition(String str, int partition) {
		List<String> parts = new ArrayList<String>();
		int len = str.length();
		for (int i = 0; i < len; i += partition) {
			parts.add(str.substring(i, Math.min(len, i + partition)));
		}
		return parts;
	}
	
	//(c >= 0x0391 && c <= 0xFFE5) //中文字符
	//(c>=0x0000 && c<=0x00FF)     //英文字符
	public static boolean isZH(char c){
		if(c >= 0x0391 && c <= 0xFFE5)  
			return true;
		return false;
	}
	
	public static String[] splitStr(String targetStr){
		String[] paraStr = null;
		if(null!=targetStr&&!"".equals(targetStr)){
			paraStr = targetStr.split("\\|",-1);
		}
		return paraStr;
	}
	
	/**
	 * 将json字符串Unicode编码转换成中文 
	 * @param str
	 * @return
	 */
	public static String convert(String utfString){  
        StringBuilder sb = new StringBuilder();  
        int i = -1;  
        int pos = 0;  
          
        while((i=utfString.indexOf("\\u", pos)) != -1){  
            sb.append(utfString.substring(pos, i));  
            if(i+5 < utfString.length()){  
                pos = i+6;  
                sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));  
            }  
        }  
          
        return sb.toString();  
    }
	/**
	 * 获取当前日期和时间 
	 * @param str
	 * @return
	 */
	public static String getCurrentDateStr() {
		 Date date = new Date();
		 String str = null;
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 str = df.format(date);
		 return str;
	}
	
	public static int getTimes(String src, String dest) {
		int times = 0, index = 0;
		do {
			index = src.indexOf(dest);
			if(index >= 0) {
				times++;
				src = src.substring(index + dest.length());
			}
		} while(index != -1);
		
		return times;
	}
	
	public static void main(String[] args){
		/*String targetStr="123|abc";
		String[] str = splitStr(targetStr);
		System.out.println(str[0]+";"+str[1]);*/	
//		System.out.println(getCurrentDateStr());
		System.out.println(getTimes("ajdkfokadsfkajokadjfok", "ok"));
	}
}
