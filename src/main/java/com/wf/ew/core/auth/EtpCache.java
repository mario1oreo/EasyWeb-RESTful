package com.wf.ew.core.auth;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.wf.etp.authz.IEtpCache;
import com.wf.ew.core.utils.RedisUtil;

/**
 * 权限缓存的实现
 * 
 * @author wangfan
 * @date 2018-2-2 上午11:35:42
 */
public class EtpCache extends IEtpCache {
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public List<String> getCacheSet(String key) {
		return redisUtil.listRange(key, 0, -1);
	}

	@Override
	public boolean putCacheInSet(String key, Set<String> values) {
		return redisUtil.listLeftPushAll(key, values) > 0;
	}

	@Override
	public boolean clearCacheSet(String key) {
		return redisUtil.listTrim(key, 1, 0);
	}

	@Override
	public boolean removeCacheSetValue(String key, String value) {
		return redisUtil.listRemove(key, 0, value) > 0;
	}

}
