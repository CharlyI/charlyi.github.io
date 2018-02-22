package com.math.util;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class DateParser {
	
	private SimpleDateFormat pattern;
	
	public DateParser(String pattern) {
		this.pattern = new SimpleDateFormat(pattern);
	}
	
	public String toString(Date date) {
		if (date == null) {
			return "Дата не установлена";
		}
		return pattern.format(date);
	}
	
	public Date toDate(String str) {
		try {
		      Date date = pattern.parse(str);
		      return date;
		    } catch (ParseException e) {
		      e.printStackTrace();
		      return null;
		    }
	}
}
