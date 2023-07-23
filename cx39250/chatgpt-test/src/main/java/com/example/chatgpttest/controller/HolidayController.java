package com.example.chatgpttest.controller;

import com.example.chatgpttest.model.Holiday;
import com.example.chatgpttest.service.HolidayService;
import com.google.gson.Gson;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holiday")
public class HolidayController {


    @Autowired
    private HolidayService holidayService;

    //generate a add holiday method
    @PostMapping("/add")
    public String addHolidays(@RequestBody List<Holiday> holidays) {
        // generate print statement
        try {
            holidayService.addHoliday(holidays);
            return "add holiday successfully";
        } catch (Exception e) {
            return "add holiday failed,error message:" + e.getMessage() ;
        }
    }

    //generate a delete holiday method
    @PostMapping("/delete")
    public String deleteHolidays(@RequestBody List<Holiday> holidays) {
        // generate print statement
        try {
            holidayService.deleteHoliday(holidays);
            return "delete holiday successfully";
        } catch (Exception e) {
            return "delete holiday failed,error message:" + e.getMessage() ;
        }
    }

    //generate a update holiday method
    @PostMapping("/update")
    public String updateHolidays(@RequestBody List<Holiday> holidays) {
        try {
            holidayService.updateHoliday(holidays);
            return "update holiday successfully";
        } catch (Exception e) {
            return "update holiday failed,error message:" + e.getMessage() ;
        }
    }

    //generate a get holiday method
    @GetMapping("/get")
    public void getHoliday() {
        // generate print statement
        System.out.println("Hello World!");
    }

    //generate get all holidays method
    @GetMapping("/getAll")
    public List<Holiday> getAllHolidays() {
        return holidayService.getAllHolidays();
    }

    @GetMapping("/getHolidayByDate")
    public String getHolidayByDate(@RequestParam String date) {
        try {
            Gson gson = new Gson();

            return gson.toJson(holidayService.getHolidayByDate(date));
        } catch (Exception e) {
            return "getHolidayByDate error, error:" + e.getMessage();
        }
    }

}
