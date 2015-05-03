/**
 * 
 */
package com.dm.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.CollectionUtils;

import com.dm.common.domain.model.IEntity;

/**
 * @author Administrator
 *
 */
public class SerializeUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * @Title: convertMaps2JsonStrings
	 * @Description: 
	 * @param objs
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static List<String> convertMaps2JsonStrings(List<Map<Object,Object>> objs) throws JsonGenerationException, JsonMappingException, IOException{
		List<String> res = new ArrayList<String>();
		for(Map<Object,Object> obj : objs){
			res.add(convertMap2JsonString(obj));
		}
		return res;
	}
	
	/**
	 * @Title: convertMap2JsonString
	 * @Description: 
	 * @param map
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String convertMap2JsonString(Map<Object, Object> map) throws JsonGenerationException, JsonMappingException, IOException{
		return objectMapper.writeValueAsString(map);
	}
	
	public static <T> T convertJsonString2Object(String jsonString,Class<T> clazz) throws JsonParseException, JsonMappingException, IOException{
		if(jsonString == null || "".equals(jsonString.trim())){
			return null;
		}
		return objectMapper.readValue(jsonString, clazz);
	}
	
	/**
	 * @Title: convertEntitiesToMaps
	 * @Description: 
	 * @param entities
	 * @param cascade
	 * @return
	 */
	public static <T extends IEntity> Collection<Map<Object,Object>> convertEntitiesToMaps(Collection<T> entities){
		List<Map<Object,Object>> res = new ArrayList<>();
		if(!CollectionUtils.isEmpty(entities)){
			for(T entity : entities){
				res.add(entity.toMap());
			}
		}
		return res;
	}
}
