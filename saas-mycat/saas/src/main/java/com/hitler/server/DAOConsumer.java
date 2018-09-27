package com.hitler.server;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hitler.model.business.Record;
import com.hitler.service.business.IRecordService;

/**
 * 初始化Spring Data JPA，并为DAO接口提供Dubbo Provider服务
 * 
 * @author
 * @version 1.0
 * 
 */

public class DAOConsumer {

	//private static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"conf/consumer/consumer-context.xml");

		context.start();
		IRecordService recordService = context.getBean(IRecordService.class);
		query(recordService);
		System.out.println("Service successfully started");
		// 按任意键退出
		System.in.read();
	}

	public static boolean query(IRecordService recordService) throws Exception {
		for (int i = 0; i < 1000; i++) {
			List<Record> full_record = recordService.findAll();
			for (Record record : full_record) {
				//String record_json = mapper.writeValueAsString(record);
				System.out.println("结果:"+record.getUserName());
			}
			
			Thread.sleep(1000);
		}

		return false;
	}

}