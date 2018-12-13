package mohamadamin.soft.com.rentalhouseproject.Models;

import java.io.Serializable;
import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.R;

public class SecondaryHouse implements Serializable
{
    private int Id;
    private String Title;
    private String DateOfRegister;
    private String Zone;
    private String Mortgage;
    private String MonthlyRent;
    private String Photo;
    private int Type;
    private int VisitedCount;


    public SecondaryHouse()
    {
    }

    public SecondaryHouse(String title, String dateOfRegister, String zone, String photo, String mortgage, String monthlyRent, int type, int visitedCount)
    {
        this.Title = title;
        this.DateOfRegister = dateOfRegister;
        this.Zone = zone;
        this.Photo = photo;
        this.Mortgage = mortgage;
        this.MonthlyRent = monthlyRent;
        this.Type = type;
        this.VisitedCount = visitedCount;
    }

    public int getId() {
        return Id;
    }

    public String getTitle()
    {
        return Title;
    }

    public String getDateOfRegister()
    {
        return DateOfRegister;
    }

    public String getZone()
    {
        return Zone;
    }

    public String getPhoto()
    {
        return Photo;
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

    public int getVisitedCount()
    {
        return VisitedCount;
    }


    public void setId(int id) {
        Id = id;
    }

    public void setTitle(String title)
    {
        this.Title = title;
    }

    public void setDateOfRegister(String dateOfRegister)
    {
        this.DateOfRegister = dateOfRegister;
    }

    public void setZone(String zone)
    {
        this.Zone = zone;
    }

    public void setPhoto(String photo)
    {
        this.Photo = photo;
    }

    public void setMortgage(String mortgage)
    {
        this.Mortgage = mortgage;
    }

    public void setMonthlyRent(String monthlyRent)
    {
        this.MonthlyRent = monthlyRent;
    }

    public void setType(int type)
    {
        this.Type = type;
    }

    public void setVisitedCount(int visitedCount)
    {
        this.VisitedCount = visitedCount;
    }

}

