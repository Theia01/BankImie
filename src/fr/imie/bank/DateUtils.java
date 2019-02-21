package fr.imie.bank;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
	public static final DateTimeFormatter fr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static DateFormat datef = new SimpleDateFormat("yyyy-MM-dd");



	public static Date toDate(String text) {
			Date d = null;
		    try {
				d = new SimpleDateFormat("dd/MM/yyyy").parse(text);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		    return d;
	}

	public static String toString(LocalDate date) {
		String t = datef.format(date);
		return t;
	}

	public static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
	
	public static Date LocaltoDate(LocalDate localDate) {
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
		
	}
}
