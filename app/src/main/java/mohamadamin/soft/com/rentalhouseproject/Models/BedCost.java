package mohamadamin.soft.com.rentalhouseproject.Models;

import java.util.ArrayList;

public class BedCost
{
    private String BedType;
    private String Cost;


    public BedCost()
    {
    }

    public BedCost(String bedType, String cost)
    {
        BedType = bedType;
        Cost = cost;
    }


    public String getBedType()
    {
        return BedType;
    }

    public String getCost()
    {
        return Cost;
    }

    public void setBedType(String bedType)
    {
        BedType = bedType;
    }

    public void setCost(String cost)
    {
        Cost = cost;
    }

}
