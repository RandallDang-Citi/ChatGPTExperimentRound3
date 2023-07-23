package com.example.demo.service;

import com.google.gson.JsonObject;

/**
 * @Description TODO
 * @ClassName HolidayService
 * @Date 2023/7/21 10:33
 * @Author thl
 **/
public interface HolidayService {

    // read country_holidays.json and return the holidays of the country
    // 1. read country_holidays.json
    JsonObject loadHolidays();

    // return next year's holidays information for given country based on current system date
    public JsonObject getNextYearHolidays(String country);

    // return next holiday information for given country based on current system date
    public String getNextHoliday(String country);

    // check given date is holiday or not
    public JsonObject isHoliday(String date);

}
