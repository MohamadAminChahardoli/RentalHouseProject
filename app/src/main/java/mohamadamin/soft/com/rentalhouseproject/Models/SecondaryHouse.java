package mohamadamin.soft.com.rentalhouseproject.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mohamadamin.soft.com.rentalhouseproject.R;

public class SecondaryHouse
{
    public static ArrayList<SecondaryHouse> createHouses(int numberOfHouses)
    {
        ArrayList<SecondaryHouse> houses = new ArrayList<>();
        for(int i=0; i<numberOfHouses; i++)
        {
            houses.add(new SecondaryHouse(
                    "خانه زیبا شماره "+i,
                    "دیروز",
                    "ظفر",
                    R.drawable.pic3,
                    "مجانی",
                    "360,000",
                    1,
                    123

            ));
        }

        return houses;
    }

    private String title;
    private String dateOfRegister;
    private String zone;
    private String mortgage;
    private String monthlyRent;
    private int photo;
    private int type;
    private int visitedCount;


    public SecondaryHouse()
    {
    }

    public SecondaryHouse(String title, String dateOfRegister, String zone, int photo, String mortgage, String monthlyRent, int type, int visitedCount)
    {
        this.title = title;
        this.dateOfRegister = dateOfRegister;
        this.zone = zone;
        this.photo = photo;
        this.mortgage = mortgage;
        this.monthlyRent = monthlyRent;
        this.type = type;
        this.visitedCount = visitedCount;
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

    public String getMortgage()
    {
        return mortgage;
    }

    public String getMonthlyRent()
    {
        return monthlyRent;
    }

    public int getType()
    {
        return type;
    }

    public int getVisitedCount()
    {
        return visitedCount;
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

    public void setMortgage(String mortgage)
    {
        this.mortgage = mortgage;
    }

    public void setMonthlyRent(String monthlyRent)
    {
        this.monthlyRent = monthlyRent;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public void setVisitedCount(int visitedCount)
    {
        this.visitedCount = visitedCount;
    }
}

