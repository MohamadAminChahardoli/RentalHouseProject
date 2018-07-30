package mohamadamin.soft.com.rentalhouseproject.Models;

public class House
{
    private String name;
    private String dateOfRegister;
    private String address;
    private String description;
    private String benefits;
    private int mortgage;
    private int monthlyRent;
    private int photo;
    private int visitedCount;
    private long latitude;
    private long longitude;


    public House()
    {
    }
    public House(String name, String dateOfRegister, String address, String description, String benefits, int mortgage, int monthlyRent, int photo,int visitedCount, long latitude, long longitude)
    {
        this.name = name;
        this.dateOfRegister = dateOfRegister;
        this.address = address;
        this.description = description;
        this.benefits = benefits;
        this.mortgage = mortgage;
        this.monthlyRent = monthlyRent;
        this.photo = photo;
        this.visitedCount=visitedCount;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName()
    {
        return name;
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

    public int getMortgage()
    {
        return mortgage;
    }

    public int getMonthlyRent()
    {
        return monthlyRent;
    }

    public int getPhoto()
    {
        return photo;
    }

    public int getVisitedCount() {return  visitedCount;}

    public long getLatitude()
    {
        return latitude;
    }

    public long getLongitude()
    {
        return longitude;
    }



    public void setName(String name)
    {
        this.name = name;
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

    public void setMortgage(int mortgage)
    {
        this.mortgage = mortgage;
    }

    public void setMonthlyRent(int monthlyRent)
    {
        this.monthlyRent = monthlyRent;
    }

    public void setPhoto(int photo)
    {
        this.photo = photo;
    }
    public void setVisitedCount(int visitedCount) {this.visitedCount=visitedCount;}

    public void setLatitude(long latitude)
    {
        this.latitude = latitude;
    }

    public void setLongitude(long longitude)
    {
        this.longitude = longitude;
    }
}
