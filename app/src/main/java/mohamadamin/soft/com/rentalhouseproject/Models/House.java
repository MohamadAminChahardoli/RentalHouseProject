package mohamadamin.soft.com.rentalhouseproject.Models;

import java.util.ArrayList;

public class House
{

    private String Title;
    private String DateOfRegister;
    private String Address;
    private String Description;
    private String Benefits;
    private String Zone;
    private String FixedNumber;
    private String PhoneNumber;
    private String Mortgage;
    private String MonthlyRent;
    private int Type;
    private int Photo;
    private int VisitedCount;
    private long Latitude;
    private long longitude;
    private ArrayList<BedCost> BedsInstance;


    public House()
    {
    }

    public House(String title, String dateOfRegister, String address, String description,
                 String benefits, String zone, String fixedNumber, String phoneNumber,
                 String mortgage, String monthlyRent, int type, int photo, int visitedCount,
                 long latitude, long longitude, ArrayList<BedCost> bedsInstance)
    {
        Title = title;
        DateOfRegister = dateOfRegister;
        Address = address;
        Description = description;
        Benefits = benefits;
        Zone = zone;
        FixedNumber = fixedNumber;
        PhoneNumber = phoneNumber;
        Mortgage = mortgage;
        MonthlyRent = monthlyRent;
        Type = type;
        Photo = photo;
        VisitedCount = visitedCount;
        Latitude = latitude;
        this.longitude = longitude;
        BedsInstance = bedsInstance;
    }


    public String getTitle()
    {
        return Title;
    }

    public String getDateOfRegister()
    {
        return DateOfRegister;
    }

    public String getAddress()
    {
        return Address;
    }

    public String getDescription()
    {
        return Description;
    }

    public String getBenefits()
    {
        return Benefits;
    }

    public String getZone()
    {
        return Zone;
    }

    public String getFixedNumber()
    {
        return FixedNumber;
    }

    public String getPhoneNumber()
    {
        return PhoneNumber;
    }

    public String getMortgage()
    {
        return Mortgage;
    }

    public String getMonthlyRent()
    {
        return MonthlyRent;
    }

    public int getType()
    {
        return Type;
    }

    public int getPhoto()
    {
        return Photo;
    }

    public int getVisitedCount()
    {
        return VisitedCount;
    }

    public long getLatitude()
    {
        return Latitude;
    }

    public long getLongitude()
    {
        return longitude;
    }

    public ArrayList<BedCost> getBedsInstance()
    {
        return BedsInstance;
    }


    public void setTitle(String title)
    {
        Title = title;
    }

    public void setDateOfRegister(String dateOfRegister)
    {
        DateOfRegister = dateOfRegister;
    }

    public void setAddress(String address)
    {
        Address = address;
    }

    public void setDescription(String description)
    {
        Description = description;
    }

    public void setBenefits(String benefits)
    {
        Benefits = benefits;
    }

    public void setZone(String zone)
    {
        Zone = zone;
    }

    public void setFixedNumber(String fixedNumber)
    {
        FixedNumber = fixedNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        PhoneNumber = phoneNumber;
    }

    public void setMortgage(String mortgage)
    {
        Mortgage = mortgage;
    }

    public void setMonthlyRent(String monthlyRent)
    {
        MonthlyRent = monthlyRent;
    }

    public void setType(int type)
    {
        Type = type;
    }

    public void setPhoto(int photo)
    {
        Photo = photo;
    }

    public void setVisitedCount(int visitedCount)
    {
        VisitedCount = visitedCount;
    }

    public void setLatitude(long latitude)
    {
        Latitude = latitude;
    }

    public void setLongitude(long longitude)
    {
        this.longitude = longitude;
    }

    public void setBedsInstance(ArrayList<BedCost> bedsInstance)
    {
        BedsInstance = bedsInstance;
    }

}
