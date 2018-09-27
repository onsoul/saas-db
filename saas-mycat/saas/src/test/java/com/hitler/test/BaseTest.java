package com.hitler.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author onsoul
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:conf/provider/ha-db-context.xml" })
public class BaseTest {
	protected Logger logger = LoggerFactory.getLogger(BaseTest.class);

	
	@Test
	public void testContext() {
		logger.info("ok .");
	}
}