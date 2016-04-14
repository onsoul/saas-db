package com.hitler.common.util;

import java.io.IOException;
import java.util.Properties;


public class PreProperties extends Properties {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PreProperties(String pro_file) {
		try {
			if (StringUtils.isNotEmpty(pro_file)) {
				super.load(getClass().getClassLoader().getResourceAsStream(
						pro_file));
				System.out.println("###装载Properties 文件成功." + pro_file);
			}
		} catch (IOException e) {
			System.out.println("###装载Properties 文件失败." + pro_file);
			e.printStackTrace();
		}
	}

}
