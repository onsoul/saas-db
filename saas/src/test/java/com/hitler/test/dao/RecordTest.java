package com.hitler.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.dubbo.rpc.RpcContext;
import com.hitler.common.dubbo.RpcContextAttachment;
import com.hitler.model.business.Record;
import com.hitler.service.business.IRecordService;
import com.hitler.test.BaseTest;

public class RecordTest extends BaseTest {

	@Resource
	private IRecordService recordService;

	@Test
	public void schemaChangeTest() {
		String schema_name="T2";
		Object result =recordService.testMycatSchemas(schema_name);
		logger.info("###end"+result.toString());
	}
	
	@Test
	public void saveTest() {
		recordService.save(cellRecord());
	}

	@Test
	public void findTest() {
		
		RpcContext.getContext().setAttachment(RpcContextAttachment.ATTR_T_SCHEMA, "T2");
		List<Record> result = recordService.findAll();
		for (Record br : result) {
			logger.info("####    user name:" + br.getUserName());
		}
	}

	private Record cellRecord() {  //生成一条记录
		Record br = new Record();
		br.setUserName("kind");
		br.setUserId(2);
		br.setParentId(1);
		br.setParentName("onsoul@qq.com");
		br.setFloor((short) 99);
		br.setAmount(1024d);
		br.setTotal(10000D);
		return br;
	}

}
