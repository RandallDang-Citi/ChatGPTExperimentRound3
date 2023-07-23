package com.example.demo.controller;

import com.example.demo.service.HolidayService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @ClassName AdminController
 * @Date 2023/7/21 10:17
 * @Author thl
 **/
@RestController
public class AdminController {

    @Autowired
    private HolidayService holidayService;

    public static void main(String[] args) {

    }

    // generate 3 RESTFUL APIs
    // 1. GET /admin
    @RequestMapping(value = "/loadHolidays", method = RequestMethod.GET)
    public JsonObject loadHolidays() {
        JsonObject jsonObject = holidayService.loadHolidays();
        return jsonObject;
    }

   // return next year's holidays information for given country based on current system date
    @RequestMapping(value = "/getNextYearHolidays", method = RequestMethod.GET)
    public JsonObject getNextYearHolidays(String country) {
        JsonObject jsonObject = holidayService.getNextYearHolidays(country);
        return jsonObject;
    }

    // return next holiday information for given country based on current system date

    @RequestMapping(value = "/getNextHoliday", method = RequestMethod.GET)
    public String getNextHoliday(String country) {
        String nextHoliday = holidayService.getNextHoliday(country);
        return nextHoliday;
    }

    @RequestMapping(value = "/getHolidayCountry", method = RequestMethod.GET)
    public JsonObject isHoliday(String date) {
        JsonObject jsonObject = holidayService.isHoliday(date);
        return jsonObject;
    }

}
