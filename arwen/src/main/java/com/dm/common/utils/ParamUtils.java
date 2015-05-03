/**
 * 
 */
package com.dm.common.utils;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.dm.common.domain.model.GenericObjectCallBack;

/**
 * @author Administrator
 *
 */
public class ParamUtils {
	/**
	 * @Title: getString
	 * @Description:
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(Map<Object, Object> params, String key,
			String defaultValue) {
		if (validate(params, key)) {
			return defaultValue;
		}
		return String.valueOf(params.get(key));
	}

	/**
	 * @Title: getInteger
	 * @Description:
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Integer getInteger(Map<Object, Object> params, String key,
			Integer defaultValue) {
		if (validate(params, key)) {
			return defaultValue;
		}
		return Integer.valueOf(params.get(key).toString());
	}

	/**
	 * @Title: getDouble
	 * @Description:
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Double getDouble(Map<Object, Object> params, String key,
			Double defaultValue) {
		if (validate(params, key)) {
			return defaultValue;
		}
		return Double.valueOf(params.get(key).toString());
	}

	/**
	 * @Title: getLong
	 * @Description:
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Long getLong(Map<Object, Object> params, String key,
			Long defaultValue) {
		if (validate(params, key)
				|| !StringUtils.hasText(params.get(key).toString())) {
			return defaultValue;
		}
		return Long.valueOf(params.get(key).toString());
	}

	/**
	 * @Title: getObjects
	 * @Description:
	 * @param params
	 * @param key
	 * @param callBack
	 * @return
	 */
	public static <T> Set<T> getObjects(Map<Object, Object> params, String key,
			GenericObjectCallBack<T> callBack) {
		Set<T> res = new LinkedHashSet<T>();
		if (validate(params, key)
				|| !StringUtils.hasText(params.get(key).toString())) {
			return res;
		}
		@SuppressWarnings("unchecked")
		Collection<Map<Object, Object>> objs = (Collection<Map<Object, Object>>) params
				.get(key);
		for (Map<Object, Object> obj : objs) {
			res.add(callBack.genericObjects(obj));
		}
		return res;
	}

	/**
	 * @Title: getBoolean
	 * @Description:
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Boolean getBoolean(Map<Object, Object> params, String key,
			Boolean defaultValue) {
		if (validate(params, key)) {
			return defaultValue;
		}
		return Boolean.valueOf(params.get(key).toString());
	}
	
	/**
	 * @Title: getDate
	 * @Description: yyyy-MM-dd HH:mm:ss
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Date getDate(Map<Object, Object> params, String key,
			Date defaultValue){
		if (validate(params, key)) {
			return defaultValue;
		}
		try {
			return DateUtils.parseDateTime(params.get(key).toString());
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * @Title: getEnum
	 * @Description:
	 * @param params
	 * @param key
	 * @param en
	 * @return
	 */
	public static <T> T getEnum(Map<Object, Object> params, String key, T[] en) {
		String enumType = getString(params, key, "");
		for (T ie : en) {
			if (enumType.equals(ie.toString())) {
				return ie;
			}
		}
		return null;
	}

	/**
	 * @Title: validate
	 * @Description:
	 * @param params
	 * @param key
	 * @return
	 */
	private static boolean validate(Map<Object, Object> params, String key) {
		if (params == null || !StringUtils.hasText(key)
				|| params.get(key) == null) {
			return true;
		}
		return false;
	}

	public static class ParamBuilder {

		private Map<Object, Object> params = new HashMap<Object, Object>();

		public ParamBuilder set(Object key, Object value) {
			this.params.put(key, value);
			return this;
		}

		public Map<Object, Object> build() {
			return this.params;
		}
	}
}
