package com.my.learn.datedemo;

import org.junit.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class LocalDateDemo {
    @Test
    public void testLocalTime(){
        LocalTime of = LocalTime.of(3, 4, 2, 202_396_900);
        System.out.println(of.toString());
        LocalTime now = LocalTime.now();
        System.out.println(now.toString());
        System.out.println(now.getHour());
        System.out.println( now.getMinute() );
        System.out.println( now.getSecond() );
        System.out.println(now.getNano());

    }

    @Test
    public void testLocalDateTime(){
        LocalDateTime of = LocalDateTime.of(2019, 3, 30, 6, 4);
        System.out.println(of.toString());
        System.out.println(of.getYear());
        System.out.println(of.getMonthValue());
        System.out.println(of.getDayOfMonth());
        System.out.println(of.getHour());
        System.out.println(of.getMinute());
        System.out.println(of.getSecond());
        System.out.println(of.getDayOfYear());
    }

    @Test
    public void testModifyLocalDateTime(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toString());
        //修改时间
        LocalDateTime localDateTime = now.withHour(2);
        System.out.println(localDateTime.toString());
        //增加时间
        LocalDateTime localDateTime1 = now.plusHours(3);
        System.out.println(localDateTime1.toString());
        //减少时间
        LocalDateTime localDateTime2 = now.minusHours(2);
        System.out.println(localDateTime2.toString());
        boolean before = now.isBefore(localDateTime2);
        System.out.println(before);
    }

    @Test
    public void testDateTimeFormatter(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = now.format(dateTimeFormatter);
        System.out.println(format);

        LocalDateTime parse = LocalDateTime.parse("2023-03-12 01:12:32", dateTimeFormatter);
        System.out.println(parse.toString());
    }

    @Test
    public void testInstant(){
//        Instant now = Instant.now();
//        System.out.println(now);
//        System.out.println(now.getNano());
//        System.out.println(now.getEpochSecond());
//        System.out.println(now.toEpochMilli());

        LocalDateTime nows = LocalDateTime.now();
        LocalDateTime localDateTime1 = nows.plusHours(3).plusDays(2);
        Duration between = Duration.between(nows, localDateTime1);
        System.out.println(between.toDays());
        System.out.println(between.toHours());
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.minusMonths(3);
        Period between1 = Period.between(now, localDate);
        System.out.println(between1.getMonths());
        int years = between1.getYears();
        System.out.println(years);
    }

    @Test
    public void testZoneId(){
//        ZoneId.getAvailableZoneIds().forEach(e -> System.out.println(e));
//        ZoneId.getAvailableZoneIds().forEach(System.out::println);
        System.out.println(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).toString());
        System.out.println(ZonedDateTime.now(ZoneId.of("America/Marigot")).toString());
        ZonedDateTime now = ZonedDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
        System.out.println(now.toString());

    }

    @Test
    public void testAjust(){
        LocalDate now = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.firstDayOfMonth();
        LocalDate with = now.with(temporalAdjuster);
        System.out.println(with.toString());
        LocalDate localDateTime = now.with(new TemporalAdjuster() {
            @Override
            public Temporal adjustInto(Temporal temporal) {
                LocalDate localDate = (LocalDate) temporal;
                LocalDate localDate1 = localDate.plusYears(1).withDayOfMonth(3);
                return localDate1;
            }
        });
        System.out.println(localDateTime.toString());
    }
}
