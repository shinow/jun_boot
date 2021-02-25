package me.chanjar.zonedatetime_json_format;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by qianjia on 2017/7/18.
 */
public class ZonedDateTimeEntity6 implements Entity {

  @JsonFormat(pattern = "yyyy-MMM-dd HH:mm:ss[VV]",
      with = { JsonFormat.Feature.WRITE_DATES_WITH_ZONE_ID },
      without = { JsonFormat.Feature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE })
  private ZonedDateTime java8ZonedDateTime;

  public ZonedDateTimeEntity6() {
  }

  public ZonedDateTimeEntity6(Date date) {

    this.java8ZonedDateTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }

  public ZonedDateTime getJava8ZonedDateTime() {
    return java8ZonedDateTime;
  }

  public void setJava8ZonedDateTime(ZonedDateTime java8ZonedDateTime) {
    this.java8ZonedDateTime = java8ZonedDateTime;
  }

  @Override
  public Object getValue() {
    return this.java8ZonedDateTime;
  }

  @Override
  public String getPattern() {
    return "yyyy-MMM-dd HH:mm:ss, eanble: JsonFormat.Feature.WRITE_DATES_WITH_ZONE_ID, disable: JsonFormat.Feature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE";
  }
}
