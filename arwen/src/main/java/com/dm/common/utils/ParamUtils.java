/**
 * 
 */
package com.dm.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * @author Administrator
 *
 */
public class ParamUtils {
	
	/**
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(Map<Object,Object> params,String key,String defaultValue){
		if(!check(params,key)){
			return defaultValue;
		}
		return String.valueOf(params.get(key));
	}
	
	/**  
	 * @Title: getDouble  
	 * @Description: 取Double型参数  
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return  
	 */
	public static Double getDouble(Map<Object,Object> params,String key,Double defaultValue){
		if(!check(params,key)){
			return defaultValue;
		}
		Double v = defaultValue;
		try {
			v = Double.valueOf(String.valueOf(params.get(key)));
		} catch (Exception e) {
			v = defaultValue;
		}
		return v;
	}
	
	public static Enum<?> getEnum(Map<Object,Object> params,String key,Enum<?>[]values){
		if(!check(params,key)){
			return null;
		}
		for(Enum<?> e : values){
			if(e.name().equals(getString(params,key,""))){
				return e;
			}
		}
		return null;
	}
	
	/**
	 * @param params
	 * @param key
	 * @return
	 */
	private static boolean check(Map<Object,Object> params,String key){
		if(params == null || !StringUtils.hasText(key) || params.get(key)==null){
			return false;
		}
		return true;
	}
	
	public static class ParamBuilder{
		
		private Map<Object,Object> map = new HashMap<>();
		
		public ParamBuilder set(Object key,Object value){
			map.put(key, value);
			return this;
		}
		
		public Map<Object,Object> build(){
			return map;
		}
	}
}
