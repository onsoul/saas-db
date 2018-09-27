package com.hitler.core.services;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.hitler.core.beans.Opations;
import com.hitler.core.db.entity.Docs;
import com.hitler.core.resp.Result;

/**
 * 通用集合处理Service,此类为整个SAAS的核心部分. 完成所有模型的操作
 * 
 * @author onsoul 2018年8月6日 下午5:59:58
 */
@Service
public class GenericService extends BaseService<Docs> {

	private Class<Docs> clasz = Docs.class; // 声明装载类型

	// repository是针对单体的，所以在此不再适用。

	@Override
	public Docs findById(String id) {
		return null;
	}
	
	public Docs findByIdAndQuery(String doc, String id, Document query) {
		Query q = new BasicQuery(new Document(), query);
		q.addCriteria(Criteria.where(Opations.ID_FIELD).is(id));
		return mongoTemplate.findOne(q, clasz , doc);
	}
	

	@Override
	public Document save(Document t, String collectionName) {
		if (!t.isEmpty()) {
			t.put(Opations.CREATED_FIELD, new Date()); // 创建时间
			t.put(Opations.UPDATED_FIELD, new Date()); // 更新时间
		}
		return super.save(t, collectionName);
	}

	/**
	 * 更新通用集合表
	 * 
	 * @param doc
	 * @param id
	 * @param updata
	 * @return
	 */
	public Docs update(String doc, String id, Docs updata) {
		Update update = new Update();
		updata.forEach((key, value) -> {
			update.set(key, value);
		});

		update.set(Opations.UPDATED_FIELD, new Date()); // 更新时间
		Query query = Query.query(Criteria.where(Opations.ID_FIELD).is(id));
		Docs docs = mongoTemplate.findAndModify(query, update, clasz, doc);
		return docs;
	}

	public Result<Docs> page(String collectionName, Document queryDoc, Pageable pageable) {
		Result<Docs> result = new Result<>(); // 结果集
		Query query = new BasicQuery(new Document() , queryDoc); // 组装query
		query.with(pageable); // 组装分页
		Long count = mongoTemplate.count(query, collectionName); // 统计
		List<Docs> dtos = (List<Docs>) mongoTemplate.find(query, clasz, collectionName); // 映射结果集
		result.setData(dtos); // 组装返回
		result.setCount(count); // 填充统计
		return result;
	}
	
	public List<Docs> pageNoCount(String collectionName, Document queryDoc, Pageable pageable) {
		Query query = new BasicQuery(new Document() , queryDoc); // 组装query
		query.with(pageable); // 组装分页
		List<Docs> dtos = (List<Docs>) mongoTemplate.find(query, clasz, collectionName); // 映射结果集
		return dtos;
	}
	

	public Long count(String collectionName, Document queryDoc) {
		Query query = new BasicQuery(new Document() , queryDoc); // 组装query
		Long count = mongoTemplate.count(query, collectionName); // 统计
		return count;
	}

}
