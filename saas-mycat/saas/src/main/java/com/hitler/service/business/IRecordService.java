package com.hitler.service.business;

import com.hitler.model.business.Record;
import com.hitler.service.support.IGenericService;

public interface IRecordService extends IGenericService<Record, Integer> {
	public int ping(String cnd);
	public Object testMycatSchemas(String schema_name);
}
