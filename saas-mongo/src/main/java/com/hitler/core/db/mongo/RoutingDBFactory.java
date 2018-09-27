package com.hitler.core.db.mongo;

import java.net.UnknownHostException;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoExceptionTranslator;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;


/**
 * 重写SimpleMongoDBFactory
 * 加入动态路由部分
 * @author onsoul 2018年4月23日
 */
public class RoutingDBFactory implements DisposableBean, MongoDbFactory {

	private final MongoClient mongoClient;
	private final String databaseName;
	private final boolean mongoInstanceCreated;
	private final PersistenceExceptionTranslator exceptionTranslator;

	private @Nullable WriteConcern writeConcern;

	/**
	 * Creates a new {@link SimpleMongoDbFactory} instance from the given
	 * {@link MongoClientURI}.
	 * 
	 * @param uri
	 *            must not be {@literal null}.
	 * @throws UnknownHostException
	 * @since 1.7
	 */
	public RoutingDBFactory(MongoClientURI uri) {
		this(new MongoClient(uri), uri.getDatabase(), true);
	}

	/**
	 * Creates a new {@link SimpleMongoDbFactory} instance from the given
	 * {@link MongoClient}.
	 * 
	 * @param mongoClient
	 *            must not be {@literal null}.
	 * @param databaseName
	 *            must not be {@literal null}.
	 * @since 1.7
	 */
	public RoutingDBFactory(MongoClient mongoClient, String databaseName) {
		this(mongoClient, databaseName, false);
	}

	/**
	 * @param client
	 * @param databaseName
	 * @param mongoInstanceCreated
	 * @since 1.7
	 */
	private RoutingDBFactory(MongoClient mongoClient, String databaseName, boolean mongoInstanceCreated) {

		Assert.notNull(mongoClient, "MongoClient must not be null!");
		Assert.hasText(databaseName, "Database name must not be empty!");
		Assert.isTrue(databaseName.matches("[\\w-]+"),
				"Database name must only contain letters, numbers, underscores and dashes!");

		this.mongoClient = mongoClient;
		this.databaseName = databaseName;
		this.mongoInstanceCreated = mongoInstanceCreated;
		this.exceptionTranslator = new MongoExceptionTranslator();
	}

	/**
	 * Configures the {@link WriteConcern} to be used on the {@link DB} instance
	 * being created.
	 * 
	 * @param writeConcern
	 *            the writeConcern to set
	 */
	public void setWriteConcern(WriteConcern writeConcern) {
		this.writeConcern = writeConcern;
	}

	@Override
	public MongoDatabase getDb() throws DataAccessException {
		String dbName = RoutingDBContextHolder.getDBName();
		if (!StringUtils.isEmpty(dbName)) {
			return getDb(dbName);
		}
		// Thread local中获取数据库的变量
		return getDb(databaseName);
	}

	@Override
	public MongoDatabase getDb(String dbName) throws DataAccessException {
		Assert.hasText(dbName, "Database name must not be empty.");

		MongoDatabase db = mongoClient.getDatabase(dbName);

		if (writeConcern == null) {
			return db;
		}
		return db.withWriteConcern(writeConcern);
	}

	@Override
	public PersistenceExceptionTranslator getExceptionTranslator() {
		return this.exceptionTranslator;
	}

	@SuppressWarnings("deprecation")
	@Override
	public DB getLegacyDb() {
		return mongoClient.getDB(databaseName);
	}

	@Override
	public void destroy() throws Exception {
		if (mongoInstanceCreated) {
			mongoClient.close();
		}

	}

}
