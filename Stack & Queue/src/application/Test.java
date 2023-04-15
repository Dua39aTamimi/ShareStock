package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Test {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");
	//	formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

		Date date = formatter.parse("22-09-2018");
		String formattedDate = formatter.format(date);

		System.out.println(date);
		System.out.println(formattedDate);
	}
}
