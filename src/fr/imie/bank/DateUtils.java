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



	public static LocalDate toDate(String text) throws ParseException {
			LocalDate d = null;
		    d =  LocalDate.parse(text, fr);  
		    return d;
	}

	public static String toString(LocalDate date) {
		String t = datef.format(date);
		return t;
	}

	public static java.sql.Date convertUtilToSql(LocalDate date) {
        java.sql.Date sDate = java.sql.Date.valueOf(date);
        return sDate;
    }
}
