package com.hitler.service.business.impl;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hitler.model.business.Record;
import com.hitler.repository.business.IRecordRepository;
import com.hitler.repository.support.GenericRepository;
import com.hitler.service.business.IRecordService;
import com.hitler.service.support.GenericService;

/**
 * 记录服务实现
 * @author onsoul by JT 2015-5-26 下午3:38:57
 */
@Service("recordService")
public class RecordService extends GenericService<Record, Integer>
		implements IRecordService {

	@Resource
	private IRecordRepository repository;

	@Override
	protected GenericRepository<Record, Integer> getRepository() {
		return repository;
	}

	@Override
	public int ping(String cnd) {
		logger.info("cmd=========================:" + cnd);
		return 90;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Object testMycatSchemas(String schema_name) {
		String sql ="select * from tb_test";
		Query query = em.createNativeQuery(sql);
		Object obj = query.getResultList();
		return obj;
	}

}
