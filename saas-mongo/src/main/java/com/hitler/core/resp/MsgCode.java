package com.hitler.core.resp;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;



/**
 * 消息代码处理
 * 在应用内某一消息触发加载时，从文件中加载出所有消息列表。
 * 
 * @author onsoul
 * 2018年8月3日 下午2:28:20
 */
@Component
public class MsgCode {

	@Value(value = "classpath:/msg_code.json")
	private Resource data;

	private static Map<String, Object> queue = new LinkedHashMap<String, Object>();

	public Object code(Code code) {
		return code(code.toString());
	}

	@SuppressWarnings("unchecked")
	public Object code(String code) {
		try {
			if (null == queue || queue.size() < 1) {
				String jsonData = this.jsonRead(data.getInputStream());
				queue = JSON.parseObject(jsonData, LinkedHashMap.class);
			}
			return queue.get(code);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public Object makeSuccess() {
		return code(Code.MSG_100);   //请求成功
	}
	
	
	/**
	 * 读取文件内容为字符串
	 * 
	 * @param file
	 * @return
	 */
	private String jsonRead(InputStream is) {
		Scanner scanner = null;
		StringBuilder buffer = new StringBuilder();
		try {
			scanner = new Scanner(is, "utf-8");
			while (scanner.hasNextLine()) {
				buffer.append(scanner.nextLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return buffer.toString();
	}

}
