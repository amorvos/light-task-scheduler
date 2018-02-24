package com.github.ltsopensource.core.failstore;

import java.lang.reflect.Type;
import java.util.List;

import com.github.ltsopensource.core.domain.Pair;

/**
 * Robert HG (254963746@qq.com) on 5/21/15.
 */
public interface FailStore {

	String getPath();

	void open() throws FailStoreException;

	void put(String key, Object value) throws FailStoreException;

	void delete(String key) throws FailStoreException;

	void delete(List<String> keys) throws FailStoreException;

	<T> List<Pair<String, T>> fetchTop(int size, Type type) throws FailStoreException;

	void close() throws FailStoreException;

	void destroy() throws FailStoreException;
}
