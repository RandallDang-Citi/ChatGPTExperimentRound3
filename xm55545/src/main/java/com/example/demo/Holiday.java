
package com.example.demo;
public class Holiday {
    private String countryCode;
    private String countryDescription;
    private String holidayDate;
    private String holidayName;

    // Default constructor
    public Holiday() {
    }

    // Parameterized constructor
    public Holiday(String countryCode, String countryDescription, String holidayDate, String holidayName) {
        this.countryCode = countryCode;
        this.countryDescription = countryDescription;
        this.holidayDate = holidayDate;
        this.holidayName = holidayName;
    }

    // Getter and Setter methods for each field
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryDescription() {
        return countryDescription;
    }

    public void setCountryDescription(String countryDescription) {
        this.countryDescription = countryDescription;
    }

    public String getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(String holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    // Override toString() method to provide a formatted string representation of the object
    @Override
    public String toString() {
        return "Holiday{" +
                "countryCode='" + countryCode + '\'' +
                ", countryDescription='" + countryDescription + '\'' +
                ", holidayDate='" + holidayDate + '\'' +
                ", holidayName='" + holidayName + '\'' +
                '}';
    }
}
