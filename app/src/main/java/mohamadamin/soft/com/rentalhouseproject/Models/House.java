package mohamadamin.soft.com.rentalhouseproject.Models;

import java.util.ArrayList;

public class House
{
    private String title;
    private String dateOfRegister;
    private String address;
    private String description;
    private String benefits;
    private String zone;
    private String fixedNumber;
    private String phoneNumber;
    private String mortgage;
    private String monthlyRent;
    private int type;
    private int photo;
    private int visitedCount;
    private long latitude;
    private long longitude;
    private ArrayList<BedCost> BedsInstance;


    public House()
    {
    }

    public House(String title, String dateOfRegister, String address, String description, String benefits, String zone, String fixedNumber, String phoneNumber, int type, String mortgage, String monthlyRent, int photo, int visitedCount, long latitude, long longitude)
    {
        this.title = title;
        this.dateOfRegister = dateOfRegister;
        this.address = address;
        this.description = description;
        this.benefits = benefits;
        this.zone = zone;
        this.fixedNumber = fixedNumber;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.mortgage = mortgage;
        this.monthlyRent = monthlyRent;
        this.photo = photo;
        this.visitedCount = visitedCount;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getTitle()
    {
        return title;
    }

    public String getDateOfRegister()
    {
        return dateOfRegister;
    }

    public String getAddress()
    {
        return address;
    }

    public String getDescription()
    {
        return description;
    }

    public String getBenefits()
    {
        return benefits;
    }

    public String getZone()
    {
        return zone;
    }

    public String getFixedNumber()
    {
        return fixedNumber;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public int getType()
    {
        return type;
    }

    public String getMortgage()
    {
        return mortgage;
    }

    public String getMonthlyRent()
    {
        return monthlyRent;
    }

    public int getPhoto()
    {
        return photo;
    }

    public int getVisitedCount()
    {
        return visitedCount;
    }

    public long getLatitude()
    {
        return latitude;
    }

    public long getLongitude()
    {
        return longitude;
    }


    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDateOfRegister(String dateOfRegister)
    {
        this.dateOfRegister = dateOfRegister;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setBenefits(String benefits)
    {
        this.benefits = benefits;
    }

    public void setZone(String zone)
    {
        this.zone = zone;
    }

    public void setFixedNumber(String fixedNumber)
    {
        this.fixedNumber = fixedNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public void setMortgage(String mortgage)
    {
        this.mortgage = mortgage;
    }

    public void setMonthlyRent(String monthlyRent)
    {
        this.monthlyRent = monthlyRent;
    }

    public void setPhoto(int photo)
    {
        this.photo = photo;
    }

    public void setVisitedCount(int visitedCount)
    {
        this.visitedCount = visitedCount;
    }

    public void setLatitude(long latitude)
    {
        this.latitude = latitude;
    }

    public void setLongitude(long longitude)
    {
        this.longitude = longitude;
    }
}
