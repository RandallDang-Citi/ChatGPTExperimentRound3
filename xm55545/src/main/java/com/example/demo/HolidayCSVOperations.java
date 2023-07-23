package com.example.demo;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HolidayCSVOperations {
    private static final String CSV_FILE_PATH = "E:\\2023\\project\\demo\\src\\main\\resources\\holidays.csv";

    // Method to add a single record to the CSV file
    public static void addHoliday(Holiday holiday) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(holidayToCSV(holiday));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add multiple records to the CSV file
    public static void addHolidays(List<Holiday> holidays) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            for (Holiday holiday : holidays) {
                bw.write(holidayToCSV(holiday));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to update a single record in the CSV file
    public static void updateHoliday(String countryCode, Holiday updatedHoliday) {
        List<Holiday> holidays = readAllHolidays();
        for (int i = 0; i < holidays.size(); i++) {
            if (holidays.get(i).getCountryCode().equals(countryCode)) {
                holidays.set(i, updatedHoliday);
                break;
            }
        }
        writeAllHolidays(holidays);
    }

    // Method to update multiple records in the CSV file
    public static void updateHolidays(List<Holiday> updatedHolidays) {
        List<Holiday> holidays = readAllHolidays();
        for (Holiday updatedHoliday : updatedHolidays) {
            for (int i = 0; i < holidays.size(); i++) {
                if (holidays.get(i).getCountryCode().equals(updatedHoliday.getCountryCode())) {
                    holidays.set(i, updatedHoliday);
                    break;
                }
            }
        }
        writeAllHolidays(holidays);
    }

    // Method to delete a single record from the CSV file
    public static void deleteHoliday(String countryCode) {
        List<Holiday> holidays = readAllHolidays();
        holidays.removeIf(holiday -> holiday.getCountryCode().equals(countryCode));
        writeAllHolidays(holidays);
    }

    // Method to query records by countryCode from the CSV file
    public static List<Holiday> queryByCountryCode(String countryCode) {
        List<Holiday> holidays = readAllHolidays();
        List<Holiday> result = new ArrayList<>();
        for (Holiday holiday : holidays) {
            if (holiday.getCountryCode().equals(countryCode)) {
                result.add(holiday);
            }
        }
        return result;
    }

    // Helper method to read all holidays from the CSV file
    public static List<Holiday> readAllHolidays() {
        List<Holiday> holidays = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    Holiday holiday = new Holiday(data[0], data[1], data[2], data[3]);
                    holidays.add(holiday);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return holidays;
    }

    // Helper method to write all holidays to the CSV file
    public static void writeAllHolidays(List<Holiday> holidays) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH);
             BufferedWriter bw = new BufferedWriter(writer)) {
            for (Holiday holiday : holidays) {
                bw.write(holidayToCSV(holiday));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to convert Holiday object to CSV string
    public static String holidayToCSV(Holiday holiday) {
        return holiday.getCountryCode() + "," +
                holiday.getCountryDescription() + "," +
                holiday.getHolidayDate() + "," +
                holiday.getHolidayName();
    }
    //return next  holiday for a give country by using compareWithCurrentDate method, date format is yyyy-MM-dd

    public static Holiday getNextHoliday(String countryCode) {
        List<Holiday> holidays = readAllHolidays();
        for (Holiday holiday : holidays) {
            if (holiday.getCountryCode().equals(countryCode)) {
                String holidayDate = holiday.getHolidayDate();
                int compareResult = compareWithCurrentDate(holidayDate);
                if (compareResult == 1) {
                    return holiday;
                }
            }
            
        }
        return null;
    }
//return next years holday for a give country by using isNextYear method, date format is yyyy-MM-dd
    public static Holiday getNextYearHoliday(String countryCode) {
        List<Holiday> holidays = readAllHolidays();
        for (Holiday holiday : holidays) {
            if (holiday.getCountryCode().equals(countryCode)) {
                String holidayDate = holiday.getHolidayDate();
                boolean isNextYear = isNextYear(holidayDate);
                if (isNextYear) {
                    return holiday;
                }
            }
        }
        return null;
    }

    //return country list by a give date, date format is yyyy-MM-dd
    public static List<Holiday> getHolidayByDate(String date) {
        List<Holiday> holidays = readAllHolidays();
        List<Holiday> result = new ArrayList<>();
        for (Holiday holiday : holidays) {
            if (holiday.getHolidayDate().equals(date)) {
                result.add(holiday);
            }
        }
        return result;
    }



    //write a date compare method to compare the date with current system date by a given date, date format is yyyy-MM-dd
    public static int compareWithCurrentDate(String givenDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        try {
            Date parsedGivenDate = dateFormat.parse(givenDate);
            return parsedGivenDate.compareTo(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return a default value in case of any errors
        return -2;
    }

public static boolean isNextYear(String givenDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        try {
            Date parsedGivenDate = dateFormat.parse(givenDate);

            Calendar currentYearCalendar = Calendar.getInstance();
            currentYearCalendar.setTime(currentDate);

            Calendar givenYearCalendar = Calendar.getInstance();
            givenYearCalendar.setTime(parsedGivenDate);

            int currentYear = currentYearCalendar.get(Calendar.YEAR);
            int givenYear = givenYearCalendar.get(Calendar.YEAR);

            return (givenYear - currentYear) == 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Return a default value in case of any errors
        return false;
    }




    public static void main(String[] args) {
        // Test the operations on the CSV file
        // Adding a single record
        Holiday newHoliday = new Holiday("FR", "France", "2023-09-01", "National Day");
        addHoliday(newHoliday);

        // Adding multiple records
        List<Holiday> newHolidays = new ArrayList<>();
        newHolidays.add(new Holiday("DE", "Germany", "2023-12-25", "Christmas Day"));
        newHolidays.add(new Holiday("IT", "Italy", "2023-04-25", "Liberation Day"));
        addHolidays(newHolidays);

        // Updating a single record
        Holiday updatedHoliday = new Holiday("FR", "France", "2023-09-01", "Victory in Europe Day");
        updateHoliday("FR", updatedHoliday);

        // Updating multiple records
        List<Holiday> updatedHolidays = new ArrayList<>();
        updatedHolidays.add(new Holiday("DE", "Germany", "2023-12-25", "Christmas Day"));
        updatedHolidays.add(new Holiday("IT", "Italy", "2023-04-25", "Liberation Day"));
        updateHolidays(updatedHolidays);

        // Deleting a single record
        deleteHoliday("FR");

        // Querying records by countryCode
        List<Holiday> queriedHolidays = queryByCountryCode("US");
        for (Holiday holiday : queriedHolidays) {
            System.out.println(holiday);
        }
    }
//check holiday date is in format yyyy-mm-dd, country code is 2 characters, holiday name is not empty

    public boolean isValidHoliday(Holiday holiday) {
        String countryCode = holiday.getCountryCode();
        String holidayDate = holiday.getHolidayDate();
        String holidayName = holiday.getHolidayName();
        if (countryCode.length() == 2 && !holidayDate.isEmpty() && !holidayName.isEmpty()) {
            return true;
        }
        if (countryCode.length() != 2) {
            System.out.println("Country code is not 2 characters");
        }
        if (holidayDate.isEmpty()) {
            System.out.println("Holiday date is empty");
        }
        if (holidayName.isEmpty()) {
            System.out.println("Holiday name is empty");
        }
        return false;
    }

    //check a give date is a holiday or not, date format is yyyy-MM-dd
    public boolean isHoliday(String date) {
        List<Holiday> holidays = readAllHolidays();
        for (Holiday holiday : holidays) {
            if (holiday.getHolidayDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidDate(String date) {
        // Check if the date is in the correct format, yyyy-MM-dd
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
