package com.javasampleapproach.twitterbootstrap.utility;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;

public class DateTimeUtils {

	// public static String serverDateFormat="yyyy-MM-dd-hh.mm.ssa";

	public static DecimalFormat decimalFormat = new DecimalFormat("######.00");
	public static String DATE_DD_MM_YYYY_WITH_DASH = "dd-MM-yyyy";
	public static String DATE_MM_DD_YYYY_WITH_DASH = "MM-dd-yyyy";
	public static String DATE_YYYY_MM_DD_WITH_DASH = "yyyy-MM-dd";
	public static String DATE_YYYY_DD_MM_WITH_DASH = "yyyy-dd-MM";
	public static String DATE_DD_MM_YYYY_WITH_SLASH = "dd/MM/yyyy";
	public static String DATE_MM_DD_YYYY_WITH_SLASH = "MM/dd/yyyy";
	public static String DATE_YYYY_MM_DD_WITH_SLASH = "yyyy/MM/dd";
	public static String DATE_YYYY_DD_MM_WITH_SLASH = "yyyy/dd/MM";
	public static String DATE_DDMMYYYY = "ddMMyyyy";
	public static String DATE_MMDDYYYY = "MMddyyyy";
	public static String DATE_EEEE_MMM_DD_YYYY_HH_MM_SS_A = "EEEE, MMM dd, yyyy HH:mm:ss a";
	public static final String DATE_DD_MMM_YYYY_WITH_DASH = "dd-MMM-yyyy";

	public final static int FIRST = 0;
	public final static int LAST = 1;

	public final static int NEXTDATE = 1;
	public final static int NEXTMONDAY = 3;
	public final static int NEXTMONTH = 2;

	public final static int MONDAY = 4;
	public final static int TUESDAY = 7;
	public final static int WEDNESDAY = 8;
	public final static int THRUSDAY = 9;
	public final static int FRIDAY = 10;
	public final static int SATURDAY = 11;
	public final static int SUNDAY = 12;

	public final static int LASTMONDAY = 5;
	public final static int PREVIOUSDATE = 6;
	public static String serverDateFormat = "MMM-dd-yy hh:mma";
	public static final String COMMON_DATE_PATTERN = "yyyy-MM-dd-hh.mm.ss a";

	/**
	 * 
	 * @param c1
	 * @param c2
	 * @param isLessThan
	 * @return
	 */
	public static boolean checkDate(Calendar c1, Calendar c2, boolean isLessThan) {
		boolean isCheck = false;
		if (isLessThan) {
			if ((c1.get(Calendar.YEAR) <= c1.get(Calendar.YEAR))
					&& (c1.get(Calendar.MONTH) <= c1.get(Calendar.MONTH))
					&& (c1.get(Calendar.DAY_OF_YEAR) <= c1
							.get(Calendar.DAY_OF_YEAR))) {
				isCheck = true;
			}
		} else {

			if ((c1.get(Calendar.YEAR) >= c1.get(Calendar.YEAR))
					&& (c1.get(Calendar.MONTH) >= c1.get(Calendar.MONTH))
					&& (c1.get(Calendar.DAY_OF_YEAR) >= c1
							.get(Calendar.DAY_OF_YEAR))) {
				isCheck = true;
			}
		}
		return isCheck;
	}

	/**
	 * 
	 * @param gpswknumr
	 * @param gpssecwk
	 * @param tz
	 * @return
	 */
	public static Calendar gpsToJavaCal(int gpswknumr, int gpssecwk, int tz) {
		Calendar cal = new GregorianCalendar(1980, Calendar.JANUARY, 6, 0, 0);
		TimeZone tzz = TimeZone.getTimeZone("GMT");
		tzz.setRawOffset(tz);

		cal.add(Calendar.WEEK_OF_YEAR, gpswknumr);
		cal.add(Calendar.SECOND, gpssecwk);
		// cal.setTimeZone(tzz);
		// cal.setTimeInMillis(cal.getTimeInMillis());
		return cal;
	}

	/*
	 * public static String gpsToJavaDate(int gpswknumr, int gpssecwk, int tz) {
	 * Calendar cal = new GregorianCalendar(1980, Calendar.JANUARY, 6, 0, 0);
	 * cal.setTimeZone(TimeZone.getTimeZone("GMT"));
	 * 
	 * cal.add(Calendar.WEEK_OF_YEAR, gpswknumr); cal.add(Calendar.SECOND,
	 * gpssecwk);
	 * 
	 * SimpleDateFormat sdf = new SimpleDateFormat(serverDateFormat); TimeZone
	 * secondTime = TimeZone.getDefault(); secondTime.setRawOffset(tz);
	 * sdf.setTimeZone(secondTime); return sdf.format(cal.getTime()); }
	 */
	
	/**
	 * 
	 * @param minute
	 * @return
	 */
	public static String getHours(long minute) {
		long hours = minute / 60;
		minute = minute % 60;

		return "" + ((hours < 10) ? "0" + hours : hours) + ":"
				+ ((minute < 10) ? "0" + minute : minute);
	}

	/**
	 * 
	 * @param str
	 * @param replaceStr
	 * @return
	 */
	public static String removeNull(String str, String replaceStr) {
		return str == null ? replaceStr : str;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static double getDouble(double value) {
		double number = 0.0;
		try {
			DecimalFormat format = new DecimalFormat("###,###.00");
			String formatedNUmber = format.format(value);
			number = Double.parseDouble(formatedNUmber);
		} catch (Exception e) {
			number = value;
		}
		return number;
	}

	/**
	 * 
	 * @param dte
	 * @return
	 */
	@SuppressWarnings("unused")
	private static Calendar getCal(String dte) {

		Date date = DateTimeUtils.parseDate(dte, "yyyy-MM-dd");
		Calendar start = Calendar.getInstance();
		start.setTime(date);
		start.set(Calendar.MILLISECOND, 0);

		return start;
	}

	/**
	 * 
	 * @param timestamp
	 * @param dateFormat
	 * @return
	 */
	private static String formatDate(Timestamp timestamp, String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(timestamp);
	}

	/**
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String formatToCommonDate(Timestamp timestamp) {
		return formatDate(timestamp, COMMON_DATE_PATTERN);
	}

	/**
	 * 
	 * @param dateStart
	 * @param dateStop
	 * @return
	 */
	public static long dateDiffInDays(String dateStart, String dateStop) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		long diffDays = 0;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			// in milliseconds
			long diff = d2.getTime() - d1.getTime();
			diffDays = diff / (24 * 60 * 60 * 1000);
			System.out.print(diffDays + " days, ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diffDays;
	}

	/**
	 * 
	 * @param dateString
	 * @param format
	 * @return
	 */
	public static java.util.Date parseDate(String dateString, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(dateString);
			return date;
		} catch (ParseException e) {
			// Loger.log("Parse Exception " + e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param minuend
	 * @param subtrahend
	 * @return
	 */
	public static int monthsBetween(Calendar minuend, Calendar subtrahend) {
		int minuendMonth = minuend.get(Calendar.MONTH);
		int minuendYear = minuend.get(Calendar.YEAR);

		int subtrahendMonth = subtrahend.get(Calendar.MONTH);
		int subtrahendYear = subtrahend.get(Calendar.YEAR);

		return ((minuendYear - subtrahendYear) * (minuend
				.getMaximum(Calendar.MONTH) + 1))
				+ (minuendMonth - subtrahendMonth);
	}

	/**
	 * 
	 * @param strDate
	 * @param minMaxFlag
	 * @param gpsDateType
	 * @param tz
	 * @return
	 */
	public static int getGPSDate(String strDate, int minMaxFlag,
			int gpsDateType, int tz) {
		int gpsDate = -1;
		try {
			// First convert into Date object to perform calculatin
			Date date = DateTimeUtils.parseDate(strDate, "yyyy-MM-dd");

			// Calendar with default Time Zone
			TimeZone tzz = TimeZone.getTimeZone("GMT");
			GregorianCalendar cal = new GregorianCalendar(1980,
					Calendar.JANUARY, 6, 0, 0);
			tzz.setRawOffset(tz);
			cal.setTime(date);

			switch (minMaxFlag) {
			case 0:
				cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
				cal.set(GregorianCalendar.MINUTE, 0);
				cal.set(GregorianCalendar.SECOND, 0);
				cal.set(GregorianCalendar.MILLISECOND, 0);
				break;
			case 1:
				cal.set(GregorianCalendar.HOUR_OF_DAY, 23);
				cal.set(GregorianCalendar.MINUTE, 59);
				cal.set(GregorianCalendar.SECOND, 59);
				cal.set(GregorianCalendar.MILLISECOND, 59);
				break;
			}

			gpsDate = DateTimeUtils.getGPSWeekNumber(cal, gpsDateType);
			if (gpsDateType == 1) {
				gpsDate += tz / 1000 * (-1);// timezone offset is add for the
											// search query......
			}
		} catch (Exception e) {
			gpsDate = -1;
		}
		return gpsDate;
	}

	/**
	 * 
	 * @param cal
	 * @param type
	 * @return
	 */
	public static int getGPSWeekNumber(GregorianCalendar cal, int type) {
		int dayCount = GetMjd3(cal.get(GregorianCalendar.YEAR),
				cal.get(GregorianCalendar.MONTH) + 1,
				cal.get(GregorianCalendar.DATE))
				- GetMjd3(1980, 1, 6);
		// System.out.println("--- "+ cal.get(GregorianCalendar.YEAR) +
		// cal.get(GregorianCalendar.MONTH) + cal.get(GregorianCalendar.DATE) +
		// cal.get(GregorianCalendar.HOUR_OF_DAY) +
		// cal.get(GregorianCalendar.MINUTE) +
		// cal.get(GregorianCalendar.SECOND));
		int GpsWeekCount = Math.abs(dayCount / 7);
		// int GpsCycle = Math.abs(GpsWeekCount / 1024);
		int GpsDay = dayCount % 7;
		// int GpsWeek = GpsWeekCount % 1024;setRawOffset
		int GpsSecond = (GpsDay * 86400) + GetElapsed(cal);

		switch (type) {
		case 0:
			return GpsWeekCount;
		case 1:
			return GpsSecond;
		default:
			return 0;
		}
	}

	/**
	 * 
	 * @param cal
	 * @return
	 */
	private static int GetElapsed(GregorianCalendar cal) {
		int hours = cal.get(GregorianCalendar.HOUR_OF_DAY);
		int minutes = cal.get(GregorianCalendar.MINUTE);
		int seconds = cal.get(GregorianCalendar.SECOND);
		int elapsed = (((hours * 60) + minutes) * 60) + seconds;
		return elapsed;
	}

	/**
	 * 
	 * @param cal
	 * @return
	 */
	private static int GetElapsed(Calendar cal) {
		int hours = cal.get(GregorianCalendar.HOUR_OF_DAY);
		int minutes = cal.get(GregorianCalendar.MINUTE);
		int seconds = cal.get(GregorianCalendar.SECOND);
		int elapsed = (((hours * 60) + minutes) * 60) + seconds;
		return elapsed;
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private static int GetMjd3(int year, int month, int day) {
		int mjd = 367 * year - idiv(7 * (idiv(month + 9, 12) + year), 4)
				- idiv(3 * (idiv(idiv(month + 9, 12) + year - 1, 100) + 1), 4)
				+ idiv(275 * month, 9) + day + 1721028 - 2400000;
		return mjd;
	}

	/**
	 * 
	 * @param n
	 * @param d
	 * @return
	 */
	private static int idiv(int n, int d) {
		return Math.abs(n / d);
	}

	/**
	 * 
	 * @param gpswknumr
	 * @param gpssecwk
	 * @param tz
	 * @return
	 */
	public static String gpsToJavaDate(int gpswknumr, int gpssecwk, int tz) {
		Calendar cal = new GregorianCalendar(1980, Calendar.JANUARY, 6, 0, 0);
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));

		cal.add(Calendar.WEEK_OF_YEAR, gpswknumr);
		cal.add(Calendar.SECOND, gpssecwk);

		SimpleDateFormat sdf = new SimpleDateFormat(serverDateFormat);
		TimeZone secondTime = TimeZone.getDefault();
		secondTime.setRawOffset(tz);
		sdf.setTimeZone(secondTime);
		return sdf.format(cal.getTime());
	}

	/**
	 * 
	 * @param type
	 * @param dateType
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String getRangeDateString(int type, String dateType,
			String date, String dateFormat) {
		GregorianCalendar cal = getRangeDate(type, dateType, date, dateFormat);

		return dateFormat(dateFormat, cal.getTime());
	}

	/**
	 * 
	 * @param format
	 * @param dt
	 * @return
	 */
	public static String dateFormat(String format, Object dt) {
		java.text.Format frt = new java.text.SimpleDateFormat(format);
		return frt.format(dt);
	}

	/**
	 * 
	 * @param type
	 * @param dateType
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static GregorianCalendar getRangeDate(int type, String dateType,
			String date, String dateFormat) {
		GregorianCalendar rangeCal = new GregorianCalendar();

		if (date != null && !"".equals(date)) {
			Date uDate = DateTimeUtils.parseDate(date, dateFormat);
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(uDate);
			switch (type) {
			case 0:
				if ("week".equals(dateType)) {
					cal.add(GregorianCalendar.DATE,
							-((cal.get(GregorianCalendar.DAY_OF_WEEK)) - 1));
				} else if ("month".equals(dateType)) {
					cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
				}
				break;
			case 1:
				if ("week".equals(dateType)) {
					cal.add(GregorianCalendar.DATE,
							+(7 - cal.get(GregorianCalendar.DAY_OF_WEEK)));
				} else if ("month".equals(dateType)) {
					cal.set(GregorianCalendar.DAY_OF_MONTH, cal
							.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
				}
				break;
			}
			rangeCal.setTimeInMillis(cal.getTimeInMillis());
		}

		return rangeCal;
	}

	/**
	 * 
	 * @param dateString
	 * @param currentFormat
	 * @param convertFormat
	 * @return
	 */
	public static String parseSQLDate(String dateString, String currentFormat,
			String convertFormat) {
		String strDate = dateString;
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat(currentFormat);
			Date dte = sdf1.parse(dateString);
			SimpleDateFormat sdf = new SimpleDateFormat(convertFormat);
			strDate = sdf.format(dte);
		} catch (Exception e) {
			// Loger.log("Parse Exception " + e);
			e.printStackTrace();
		}
		return strDate;
	}

	/**
	 * 
	 * @param dateString
	 * @param currentFormat
	 * @param convertedFormat
	 * @param currentTimeZoneID
	 * @param convertedTimeZoneId
	 * @return
	 */
	public static String formatTimeZone(String dateString,
			String currentFormat, String convertedFormat,
			String currentTimeZoneID, String convertedTimeZoneId) {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat(currentFormat);
			sdf.setTimeZone(TimeZone.getTimeZone(currentTimeZoneID));

			// Convert date with current time zone
			Date tempDate = sdf.parse(dateString);

			// Set formater for convertd timezone
			SimpleDateFormat sdf1 = new SimpleDateFormat(convertedFormat);

			sdf1.setTimeZone(TimeZone.getTimeZone(convertedTimeZoneId));

			return sdf1.format(tempDate);
		} catch (Exception e) {
			return dateString;
		}
	}

	/**
	 * 
	 * @param dateString
	 * @param format
	 * @param value
	 * @return
	 */
	public static String add(String dateString, String format, int value) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(dateString);
			// date.setDate(value);
			sdf.format(date);
			return "" + sdf.format(date);
		} catch (ParseException e) {
			// Loger.log("Exception Formatter : add " + e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param DateFormat
	 * @return
	 */
	public static List<String> getDatesInRange(String startDate,
			String endDate, String DateFormat) {
		Date sDate = DateTimeUtils.parseDate(startDate, "yyyy-MM-dd");
		Date eDate = DateTimeUtils.parseDate(endDate, "yyyy-MM-dd");

		List<String> dateList = new ArrayList<String>();

		for (; sDate.compareTo(eDate) <= 0;) {
			String dateString = DateTimeUtils.getDateStrInGivenFormat(sDate,
					DateFormat);
			dateList.add(dateString);
			sDate = DateTimeUtils.getNextDate(sDate);
		}
		return dateList;
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateStrInGivenFormat(Date date, String format) {
		String dte = "";
		try {
			dte = new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
			// Loger.log("Exception Formatter : getMSQLDate" + e);
			e.printStackTrace();
		}
		return dte;
	}

	/**
	 * 
	 * @param currentDate
	 * @return
	 */
	public static Date getNextDate(Date currentDate) {
		java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.setTime(currentDate);
		calendar.add(GregorianCalendar.DATE, 1);
		java.util.Date nextDate = calendar.getTime();
		return nextDate;
	}

	/**
	 * 
	 * @param type
	 * @param isStartdate
	 * @param tz
	 * @return
	 */
	public static String getDateCal(String type, boolean isStartdate, int tz) {
		Calendar cal = new GregorianCalendar();
		TimeZone tzone = TimeZone.getTimeZone("GMT");
		tzone.setRawOffset(tz);
		cal.setTimeZone(tzone);
		if (type == null || "0".equals(type)) {
			// return cal;
		} else if ("1".equals(type)) {
			cal.add(Calendar.DAY_OF_YEAR, -1);
			// return cal;
		} else if ("2".equals(type)) {
			cal.set(Calendar.DAY_OF_WEEK, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1);
			if (!isStartdate) {
				cal.set(Calendar.DAY_OF_WEEK, 1);
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	/**
	 * 
	 * @param date
	 * @param nextType
	 * @return
	 */
	public static String getNext(Date date, int nextType) {
		java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.setTime(date);

		String returnDate = null;

		switch (nextType) {
		case NEXTDATE:
			calendar.add(GregorianCalendar.DATE, 1);
			java.util.Date nextDate = calendar.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf.format(nextDate);
			break;
		case NEXTMONTH:
			calendar.add(GregorianCalendar.MONTH, 1);
			calendar.set(GregorianCalendar.DATE, 1);
			java.util.Date nextMonth = calendar.getTime();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf1.format(nextMonth);
			break;

		case NEXTMONDAY:
			calendar.add(GregorianCalendar.WEEK_OF_MONTH, 1);
			calendar.set(GregorianCalendar.DAY_OF_WEEK, 1);

			java.util.Date nextMonday = calendar.getTime();
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf3.format(nextMonday);
			break;

		case MONDAY:
			calendar.add(GregorianCalendar.WEEK_OF_MONTH, 1);
			calendar.set(GregorianCalendar.DAY_OF_WEEK, 2);

			java.util.Date monday = calendar.getTime();
			SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf4.format(monday);
			break;

		case TUESDAY:
			calendar.add(GregorianCalendar.WEEK_OF_MONTH, 1);
			calendar.set(GregorianCalendar.DAY_OF_WEEK, 3);

			java.util.Date tueday = calendar.getTime();
			SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf5.format(tueday);
			break;

		case WEDNESDAY:
			calendar.add(GregorianCalendar.WEEK_OF_MONTH, 1);
			calendar.set(GregorianCalendar.DAY_OF_WEEK, 4);

			java.util.Date wednesday = calendar.getTime();
			SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf6.format(wednesday);
			break;

		case THRUSDAY:
			calendar.add(GregorianCalendar.WEEK_OF_MONTH, 1);
			calendar.set(GregorianCalendar.DAY_OF_WEEK, 4);

			java.util.Date thursday = calendar.getTime();
			SimpleDateFormat sdf7 = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf7.format(thursday);
			break;

		case FRIDAY:
			calendar.add(GregorianCalendar.WEEK_OF_MONTH, 1);
			calendar.set(GregorianCalendar.DAY_OF_WEEK, 4);

			java.util.Date friday = calendar.getTime();
			SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf8.format(friday);
			break;

		case SATURDAY:
			calendar.add(GregorianCalendar.WEEK_OF_MONTH, 1);
			calendar.set(GregorianCalendar.DAY_OF_WEEK, 4);

			java.util.Date saturday = calendar.getTime();
			SimpleDateFormat sdf9 = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf9.format(saturday);
			break;

		case SUNDAY:
			calendar.add(GregorianCalendar.WEEK_OF_MONTH, 1);
			calendar.set(GregorianCalendar.DAY_OF_WEEK, 4);

			java.util.Date sunday = calendar.getTime();
			SimpleDateFormat sdf10 = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf10.format(sunday);
			break;

		case PREVIOUSDATE:
			calendar.add(GregorianCalendar.DATE, -1);
			nextDate = calendar.getTime();
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf.format(nextDate);
			break;
		}

		return returnDate;

	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String convert24To12Formet(String date) {
		String formeteddate = "";
		DateFormat f1 = new SimpleDateFormat("HH:mm:ss"); // HH for hour of the
															// day (0 - 23)
		Date d;
		try {
			d = f1.parse(date);
			DateFormat f2 = new SimpleDateFormat("hh:mma");
			formeteddate = f2.format(d).toLowerCase(); // "12:18am"
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formeteddate;
	}

	/**
	 * 
	 * @param date
	 * @param nextType
	 * @return
	 */
	public static String getLast(Date date, int nextType) {
		java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.setTime(date);

		String returnDate = null;

		switch (nextType) {
		case LASTMONDAY:
			calendar.add(GregorianCalendar.WEEK_OF_MONTH, -1);
			calendar.set(GregorianCalendar.DAY_OF_WEEK, 2);

			java.util.Date nextMonday = calendar.getTime();
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf3.format(nextMonday);
			break;
		}

		return returnDate;

	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getMSQLDate(java.util.Date date) {
		String dte = "";
		try {
			dte = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		} catch (Exception e) {
			// Loger.log("Exception Formatter : getMSQLDate" + e);
			e.printStackTrace();
		}
		return dte;
	}

	/**
	 * 
	 * @param dateInString
	 * @param format
	 * @return
	 */
	public static Date convertStringIntoDate(String dateInString, String format) {

		try {
			DateFormat formatter = new SimpleDateFormat(format);
			Date date = formatter.parse(dateInString);
			return date;
		} catch (ParseException exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param dateToConvert
	 * @param formatInString
	 * @return
	 */
	public static String convertDateIntoString(Date dateToConvert,
			String formatInString) {

		try {
			DateFormat formatter = new SimpleDateFormat(formatInString);
			String dateInString = formatter.format(dateToConvert);
			return dateInString;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDay(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 1);
		Date date2 = cal.getTime();
		return date2;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFullDay(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		Date date2 = cal.getTime();
		return date2;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getPreviousDateWithFullDay(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return getFullDay(cal.getTime());
	}

	/**
	 * 
	 * @param endDate
	 * @return
	 */
	public static Date getPreviousDateWithStartDay(Date endDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return getStartDay(cal.getTime());
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDateWithFullDay(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, +1);
		return getFullDay(cal.getTime());
	}

	/**
	 * 
	 * @param endDate
	 * @return very next date to provided Date
	 */
	public static Date getNextDateWithStartDay(Date endDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.add(Calendar.DAY_OF_MONTH, +1);
		return getStartDay(cal.getTime());
	}

	/**
	 * 
	 * @param stringDate
	 *            date in String Formate for Example "02/05/2018"
	 * @return Date Object for Example Mon Feb 05 00:00:00 IST 2018
	 */
	public static Date stringMmDdYyyyToDate(String stringDate) {

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			return formatter.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param ids
	 * @return
	 */
	public static String parseInCommaSeparatedString(Object[] ids) {

		StringBuffer buffer = new StringBuffer("");
		if (ids == null || ids.length <= 0) {
			return buffer.toString();
		}
		for (Object obj : ids) {
			if (buffer.length() > 0)
				buffer.append(',');
			buffer.append(obj);
		}
		return buffer.toString();
	}

	/**
	 * 
	 * @param ids
	 * @return
	 */
	public static Long[] parseInObjectArray(String ids) {

		StringTokenizer tokenizer = new StringTokenizer(ids, ",");
		int n = tokenizer.countTokens();
		Long[] list = new Long[n];
		for (int i = 0; i < n; i++) {
			String token = tokenizer.nextToken();
			list[i] = Long.parseLong(token);
		}
		return list;
	}

	/**
	 * 
	 * @param ids
	 * @return
	 */
	public static String[] parseInStringArray(String ids) {

		StringTokenizer tokenizer = new StringTokenizer(ids, ",");
		int n = tokenizer.countTokens();
		String[] list = new String[n];
		for (int i = 0; i < n; i++) {
			String token = tokenizer.nextToken();
			list[i] = token;
		}
		return list;
	}

	/**
	 * 
	 * @param ids
	 * @return
	 */
	public static Integer[] parseInIntegerArray(String ids) {

		StringTokenizer tokenizer = new StringTokenizer(ids, ",");
		int n = tokenizer.countTokens();
		Integer[] list = new Integer[n];
		for (int i = 0; i < n; i++) {
			String token = tokenizer.nextToken();
			list[i] = Integer.parseInt(token);
		}
		return list;
	}

	public static void main(String[] args) {

		Date date = stringMmDdYyyyToDate("02/05/2018");
		System.out.println(date.toString());
		date = getNextDateWithStartDay(date);
		System.out.println(date.toString());
	}
}
