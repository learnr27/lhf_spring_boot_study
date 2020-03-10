package com.lhf.springboot.date;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;

public class Java8TimeDemo {

	public static void main(String[] args) {

		//获取当前年月日
		LocalDate date = LocalDate.now();
		System.out.println("date = " + date);

		//构造指定的年月日
		LocalDate date1 = LocalDate.of(2019, 11, 27);
		System.out.println("date1 = " + date1);

		//获取年，月，日，星期几
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();  //年
		int month = localDate.getMonthValue();  //月
		int day = localDate.getDayOfMonth();  //日
		System.out.println(year + "-" + month + "-" + day);

		//获取年，月，日，星期几
		int year1 = localDate.get(ChronoField.YEAR);
		int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
		int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);
		int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
		System.out.println(year1 + "-" + month1 +  "-" + day1 + " ,星期" + dayOfWeek1);

		Month month2 = localDate.getMonth();
		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		System.out.println("month2 = " + month2 + " , dayOfWeek = " + dayOfWeek);

		//LocalTime 获取几点几分几秒
		//获取当前时间 时分秒
		LocalTime localTime = LocalTime.now();
		System.out.println("当前时间：" + localTime);

		//指定时分秒
		LocalTime localTime1 = LocalTime.of(13, 39, 10);
		System.out.println("当前时间1：" + localTime1);

		//获取时分秒
		//获取小时
		int hour = localTime.getHour();
		int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);

		//获取分
		int minute = localTime.getMinute();
		int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR);

		//获取秒
		int second = localTime.getSecond();
		int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE);
		System.out.println("时分秒："+hour+":"+minute+":"+second);
		System.out.println("时分秒1："+hour1+":"+minute1+":"+second1);

		//LocalDateTime 获取年月日时分秒    等于LocalDate + LocalTime
		//创建LocalDateTime
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 30, 13, 47, 45);
		LocalDateTime localDateTime2 = LocalDateTime.of(localDate,  localTime);
		LocalDateTime localDateTime3 = localDate.atTime(localTime);
		LocalDateTime localDateTime4 = localTime.atDate(localDate);

		System.out.println("年月日时分秒：" + localDateTime);
		System.out.println("年月日时分秒1：" + localDateTime1);
		System.out.println("年月日时分秒2：" + localDateTime2);
		System.out.println("年月日时分秒3：" + localDateTime3);
		System.out.println("年月日时分秒4：" + localDateTime4);

		//获取LocalDate
		LocalDate  localDate2 = localDateTime.toLocalDate();
		System.out.println("localDate2 = " + localDate2);

		//获取LocalTime
		LocalTime localTime2 = localDateTime.toLocalTime();
		System.out.println("localTime2 = " + localTime2);
		System.out.println(localDate2 + " " + localTime2);

		//Instant  获取秒数
		//创建Instant对象
		Instant instant = Instant.now();
		//获取秒数
		long currentSecond = instant.getEpochSecond();
		System.out.println("currentSecond = " + currentSecond);

		//获取毫秒数
		long currentMilli = instant.toEpochMilli();
		System.out.println("currentMilli = " + currentMilli);

		System.out.println("时间戳："+System.currentTimeMillis());




		/**********************************以下是年月日的修改以及格式化操作*********************************************/
		//指定一个时间 年月日时分秒
		LocalDateTime local = LocalDateTime.of(2019, Month.SEPTEMBER, 30, 14, 02, 45);
		System.out.println("local = " + local);
		//增加2年
		local = local.plusYears(2);
		System.out.println("local = " + local);
		//再增加2年
		local = local.plus(1, ChronoUnit.YEARS);
		System.out.println("local = " + local);
		//减少2个月
		local = local.minusMonths(2);
		System.out.println("local = " + local);
		//再减少3个月
		local = local.minus(3, ChronoUnit.MONTHS);
		System.out.println("local = " + local);
		//再减少3年
		local = local.minusYears(3);  //等价于local.minus(3, ChronoUnit.YEARS);
		System.out.println("local = " + local);
		//再加4个月
		local = local.plusMonths(4); //等价于 local.plus(4, ChronoUnit.MONTHS);
		System.out.println("local = " + local);
		//时分秒的增加(plus)或减少(minus)也是一样，这里就省略了。


		//修改某个年月日时分秒
		LocalDateTime local1 = LocalDateTime.of(2019, Month.SEPTEMBER, 30, 14, 20, 45);
		//修改年为2023
		local1 = local1.withYear(2023);
		System.out.println("local1 = " + local1);
		//再修改年为2025
		local1 = local1.with(ChronoField.YEAR, 2025);
		System.out.println("local1 = " + local1);

		//修改月为8
		local1 = local1.withMonth(8);
		System.out.println("local1 = " + local1);
		//再修改月为12
		local1 = local1.with(ChronoField.MONTH_OF_YEAR, 12);
		System.out.println("local1 = " + local1);

		//修改日为25
		local1 = local1.withDayOfMonth(25);
		System.out.println("local1 = " + local1);
		//再修改日为15
		local1 = local1.with(ChronoField.DAY_OF_MONTH, 15);
		System.out.println("local1 = " + local1);
		//时分秒的修改(with)也是类似的，这里就省略了。。。。


		//格式化时间  DateTimeFormatter默认提供了多种格式化方式，也可以使用它的ofPattern()自定义格式化方式
		//DateTimeFormatter 是线程安全的， SimpleDateFormat 是线程不安全的
		LocalDate localDate3 = LocalDate.of(2019, 11, 30);
		String dateStr = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println("格式化时间dateStr = " + dateStr);

		String dateStr1 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println("格式化时间dateStr1 = " + dateStr1);

		//自定义格式化时间
		DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dateStr2 = localDate.format(dataTimeFormatter);
		System.out.println("格式化时间dateStr2 = " + dateStr2);

		LocalTime localTime3 = LocalTime.of(14,  44, 23);
		LocalDateTime localDateTime5 = LocalDateTime.of(localDate3, localTime3);
		System.out.println("localDateTime5 = " + localDateTime5);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH|mm:ss");
		String dateStr3 = dtf.format(localDateTime5);
		System.out.println("dateStr3 = " + dateStr3);

		//解析时间
		LocalDate localDate4 = LocalDate.parse("20191130", DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println("localDate4 = " + localDate4);

		LocalDate localDate5 = LocalDate.parse("2019-11-30", DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println("localDate5 = " + localDate5);

		System.out.println("格式化时间：" + getCurrTime());

	}


	public static String getCurrTime(){
		DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHssmm");
		LocalDateTime localDateTime = LocalDateTime.now();
		String dataStr = dataTimeFormatter.format(localDateTime);

		return dataStr;

	}

}
