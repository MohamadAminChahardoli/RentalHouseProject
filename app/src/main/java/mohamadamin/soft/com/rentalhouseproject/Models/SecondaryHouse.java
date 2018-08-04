package mohamadamin.soft.com.rentalhouseproject.Models;

import java.util.ArrayList;

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

    private String Title;
    private String DateOfRegister;
    private String Zone;
    private String Mortgage;
    private String MonthlyRent;
    private int Photo;
    private int Type;
    private int VisitedCount;


    public SecondaryHouse()
    {
    }

    public SecondaryHouse(String title, String dateOfRegister, String zone, int photo, String mortgage, String monthlyRent, int type, int visitedCount)
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

    public int getPhoto()
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

    public void setPhoto(int photo)
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

