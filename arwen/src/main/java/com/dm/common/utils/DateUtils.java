/**
 * 
 */
package com.dm.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class DateUtils {
	
	/**
	 * @param date
	 * @return
	 */
	public static Date parseDateTime(String date){
		return null;
	}
	
	/**
	 * @param date
	 * @return
	 */
	public static String getDateStrCompact(Date date){
		SimpleDateFormat formatter= new SimpleDateFormat ("yyyyMMdd");
		return formatter.format(date);
	}
}
