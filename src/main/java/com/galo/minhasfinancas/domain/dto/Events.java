package com.galo.minhasfinancas.domain.dto;

import java.util.Date;

public class Events {

  public Events() {
  }

  public Events(String title, Date start, String backgroundColor, String borderColor, boolean allDay) {
    this.title = title;
    this.start = start;
    this.backgroundColor = backgroundColor;
    this.borderColor = borderColor;
    this.allDay = allDay;
  }

  private String title;
  private Date start;
  private String backgroundColor;
  private String borderColor;
  private boolean allDay;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getStart() {
    return start;
  }

  public void setStart(Date start) {
    this.start = start;
  }

  public String getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(String backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public String getBorderColor() {
    return borderColor;
  }

  public void setBorderColor(String borderColor) {
    this.borderColor = borderColor;
  }

  public boolean isAllDay() {
    return allDay;
  }

  public void setAllDay(boolean allDay) {
    this.allDay = allDay;
  }
}
