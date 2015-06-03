/**
 * 
 */
package com.dm.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
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
	 * @throws ParseException 
	 */
	public static Date parseDateTime(String date) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.parse(date);
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
