package mohamadamin.soft.com.rentalhouseproject.Models;

import java.util.ArrayList;
import java.util.Random;

import mohamadamin.soft.com.rentalhouseproject.R;

public class SecondaryHouse
{
    private String title;
    private String dateOfRegister;
    private String zone;
    private int photo;
    private int mortgage;
    private int monthlyRent;


    public SecondaryHouse()
    {
    }

    public SecondaryHouse(String title, String dateOfRegister, String zone, int photo, int mortgage, int monthlyRent)
    {
        this.title = title;
        this.dateOfRegister = dateOfRegister;
        this.zone = zone;
        this.photo = photo;
        this.mortgage = mortgage;
        this.monthlyRent = monthlyRent;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDateOfRegister()
    {
        return dateOfRegister;
    }

    public String getZone()
    {
        return zone;
    }

    public int getPhoto()
    {
        return photo;
    }

    public int getMortgage()
    {
        return mortgage;
    }

    public int getMonthlyRent()
    {
        return monthlyRent;
    }


    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDateOfRegister(String dateOfRegister)
    {
        this.dateOfRegister = dateOfRegister;
    }

    public void setZone(String zone)
    {
        this.zone = zone;
    }

    public void setPhoto(int photo)
    {
        this.photo = photo;
    }

    public void setMortgage(int mortgage)
    {
        this.mortgage = mortgage;
    }

    public void setMonthlyRent(int monthlyRent)
    {
        this.monthlyRent = monthlyRent;
    }
}

