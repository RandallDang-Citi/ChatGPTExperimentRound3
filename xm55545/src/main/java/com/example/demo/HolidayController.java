package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/holiday")
public class HolidayController {

    private HolidayCSVOperations holidayCSVOperations = new HolidayCSVOperations();

    //using holidayCSVOperations to add holiday


    //restapi to add new holidays support single and multiple
    //restapi to update holidays support single and multiple using holidayCSVOperations
    @PostMapping("/add")
    public String addHoliday(@RequestBody Holiday holiday) {
        //check holiday date is in format yyyy-mm-dd, country code is 2 characters, holiday name is not empty
        if (!holidayCSVOperations.isValidHoliday(holiday)) {
            return "Invalid holiday";
        }
        holidayCSVOperations.addHoliday(holiday);
        return "Holiday added";
    }
    //add holidays using holidayCSVOperations
    @PostMapping("/addall")
    public String addAllHolidays(@RequestBody List<Holiday> holidays) {
        if (!holidayCSVOperations.isValidHoliday(holidays.get(0))) {
            return "Invalid holiday";
        }
        holidayCSVOperations.addHolidays(holidays);
        return "Holidays added";
    }
    //restapi to update holiday by country code using holidayCSVOperations
    @PutMapping("/update/{countryCode}")
    public String updateHolidayByCountryCode(@PathVariable String countryCode, @RequestBody Holiday holiday) {
        if (!holidayCSVOperations.isValidHoliday(holiday)) {
            return "Invalid holiday";
        }
        //if country code is not 2 characters, return error message
        if (countryCode.length() != 2) {
            System.out.println("Country code is not 2 characters");
            return null;
        }
        holidayCSVOperations.updateHoliday(countryCode, holiday);
        return "Holiday updated";
    }
    
    //update holidays using holidayCSVOperations
    @PutMapping("/updateall")
    public String updateAllHolidays(@RequestBody List<Holiday> holidays) {
        //
        
                for (Holiday holiday : holidays) {
                if (!holidayCSVOperations.isValidHoliday(holidays.get(0))) {
                     return "Invalid holiday";
                }
            }
           
        

        holidayCSVOperations.updateHolidays(holidays);
        return "Holidays updated";
    }
    

    //restapi to get next holiday by country code using holidayCSVOperations
    @GetMapping("/next/{countryCode}")
    public Holiday getNextHolidayByCountryCode(@PathVariable String countryCode) {
        //if country code is not 2 characters, return error message
        if (countryCode.length() != 2) {
            System.out.println("Country code is not 2 characters");
            return null;
        }
        return holidayCSVOperations.getNextHoliday(countryCode);
    }
    //restapi to get next year's holiday by country code using holidayCSVOperations
    @GetMapping("/nextyear/{countryCode}")
    public Holiday getNextYearHolidayByCountryCode(@PathVariable String countryCode) {
        //if country code is not 2 characters, return error message
        if (countryCode.length() != 2) {
            System.out.println("Country code is not 2 characters");
            return null;
        }
        return holidayCSVOperations.getNextYearHoliday(countryCode);
    }

    //restapi to check a give date is holiday or not using holidayCSVOperations isHoliday
    @GetMapping("/check/{date}")
    public boolean isHoliday(@PathVariable String date) {
        //if date is not in format yyyy-mm-dd, return error message
        if (!holidayCSVOperations.isValidDate(date)) {
            System.out.println("Date is not in format yyyy-mm-dd");
            return false;
        }
        //use isHoliday method to check if the date is holiday
        if (holidayCSVOperations.isHoliday(date)) {
            System.out.println("Date is holiday");
            return true;
        }
        return holidayCSVOperations.isHoliday(date);
    }
    

    ////query holiday by country code and return all holdays using holidayCSVOperations
    //restapi to update holidays support single and multiple using holidayCSVOperations
    @GetMapping("/country/{countryCode}")
    public List<Holiday> getHolidayByCountryCode(@PathVariable String countryCode) {

        //if country code is not 2 characters, return error message
        if (countryCode.length() != 2) {
            System.out.println("Country code is not 2 characters");
            return null;
        }
        return holidayCSVOperations.queryByCountryCode(countryCode);
    }

    //restapi to delete holidays support single and multiple using holidayCSVOperations
    @DeleteMapping("/delete/{countryCode}")
    public String deleteHolidayByCountryCode(@PathVariable String countryCode) {
        //if country code is not 2 characters, return error message
        if (countryCode.length() != 2) {
            System.out.println("Country code is not 2 characters");
            return null;
        }
        holidayCSVOperations.deleteHoliday(countryCode);
        return "Holiday deleted";
    }
    





    
    //restapi to delete holidays support single and multiple using holidayCSVOperations
    //restapi to get all holidays
    //restapi to get holiday by name
    //restapi to get holiday by date


   

}
