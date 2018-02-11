package com.wf.ew.test;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wf.ew.core.utils.RedisUtil;

/**
 * 测试用例基类
 * 
 * @author wangfan
 * @date 2018-1-24 上午9:07:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml" })
public class BaseTest {
	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void test(){
		String encodeHexString = Hex.encodeHexString("aaaa".getBytes());
		System.out.println(encodeHexString);
	}
}