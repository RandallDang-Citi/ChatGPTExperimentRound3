package com.example.demo.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @ClassName HolidayServiceImpl
 * @Date 2023/7/21 10:34
 * @Author thl
 **/
@Service
public class HolidayServiceImpl implements HolidayService{
    // read country_holidays.json and return the holidays of the country
    // 1. read country_holidays.json
    @Override
    public JsonObject loadHolidays() {
        // read country_holidays.json
        // 1. read country_holidays.json
        //InputStreamReader inputStream(manually)
        InputStreamReader inputStreamReader = new InputStreamReader(
                this.getClass().getResourceAsStream("/country_holidays.json")
        );
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String lineContent = null;
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            try {
                if (!((lineContent=bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(lineContent);
            stringBuffer.append(lineContent);
        }
        // convert to JsonObject
        JsonObject jsonObject = JsonParser.parseString(stringBuffer.toString()).getAsJsonObject();
        // return the holidays of the country
        return jsonObject;
    }

    @Override
    public JsonObject getNextYearHolidays(String country) {
        // step 1: read country_holidays.json
        JsonObject jsonObject = loadHolidays();
        JsonElement jsonElement = jsonObject.get(country);
        if (jsonElement==null) {
            JsonObject result = new JsonObject();
            result.addProperty("message", "country not found");
            return result;
        }
        JsonObject countryYears = jsonElement.getAsJsonObject();
        // get the next year from this Calendar instance
        int nextYear = 2022;
        Calendar instance = Calendar.getInstance();
        int currentYear = instance.get(Calendar.YEAR);
        nextYear = currentYear + 1;
        // step 2: get the next year holidays
        JsonElement yearJson = countryYears.get(String.valueOf(nextYear));
        if (yearJson==null) {
            JsonObject result = new JsonObject();
            result.addProperty("message", "No next year holidays");
            return result;
        }
        JsonObject nextYearHolidays = countryYears.get(String.valueOf(nextYear)).getAsJsonObject();
        return nextYearHolidays;
    }

    // return next holiday information for given country based on current system date
    @Override
    public String getNextHoliday(String country) {
        JsonObject jsonObject = loadHolidays();
        if (jsonObject.get(country)==null) {
            return "No this Country";
        }
        JsonObject countryJsonObject = jsonObject.get(country).getAsJsonObject();
        // get the next holiday from this Calendar instance
        Calendar instance = Calendar.getInstance();
        int currentYear = instance.get(Calendar.YEAR);
        // get the next holiday
        if (countryJsonObject.get(String.valueOf(currentYear))==null) {
            return "No this year holidays";
        }
        JsonObject holidayJson = countryJsonObject.get(String.valueOf(currentYear)).getAsJsonObject();
        // sort the holidayJson by key
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nextholiday = null;
        for (String key : holidayJson.keySet()) {
            try {
                Date parse = simpleDateFormat.parse(key);
                long time = parse.getTime();
                System.out.println(key+ "  " +time + " " + System.currentTimeMillis());
                if (time > System.currentTimeMillis()) {
                    nextholiday = key;
                    break;
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if (nextholiday==null) {
            return "No next holidays";
        }
        JsonElement jsonElement = holidayJson.get(nextholiday);
        return jsonElement.toString();
    }

    @Override
    public JsonObject isHoliday(String date) {
        JsonObject resultJson = new JsonObject();
        List<String> holidayList = new ArrayList<>();
        List<String> nonHolidayList = new ArrayList<>();
        JsonObject jsonObject = loadHolidays();
        // containsKey date in jsonObject
        for (String countryCode : jsonObject.keySet()) {
            JsonObject countryJsonObject = jsonObject.get(countryCode).getAsJsonObject();
            if (countryJsonObject.toString().contains(date)) {
                // it is holiday
                holidayList.add(countryCode);
            }else {
                nonHolidayList.add(countryCode);
            }
        }
        if (holidayList.isEmpty() && nonHolidayList.isEmpty()) {
            JsonObject result = new JsonObject();
            result.addProperty("message", "date is not exist in country_holidays.json");
            return result;
        }
        resultJson.add("holidayCountry", JsonParser.parseString(holidayList.toString()));
        resultJson.add("nonHolidayCountry", JsonParser.parseString(holidayList.toString()));
        return resultJson;
    }
}
