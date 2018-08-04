package mohamadamin.soft.com.rentalhouseproject.Models;

import java.util.ArrayList;

public class BedCost
{
    private int Id;
    private String BedType;
    private String Cost;

    public BedCost()
    {
    }

    public BedCost(int id, String bedType, String cost)
    {
        Id = id;
        BedType = bedType;
        Cost = cost;
    }


    public int getId()
    {
        return Id;
    }

    public String getBedType()
    {
        return BedType;
    }

    public String getCost()
    {
        return Cost;
    }


    public void setId(int id)
    {
        Id = id;
    }

    public void setBedType(String bedType)
    {
        BedType = bedType;
    }

    public void setCost(String cost)
    {
        Cost = cost;
    }

    public static ArrayList<BedCost> createList()
    {
        ArrayList<BedCost> list = new ArrayList<>();
        list.add(new BedCost(1, "یک تخته", "900"));
        list.add(new BedCost(2, "دو تخته", "600"));
        list.add(new BedCost(3, "چهار تخته", "480"));
        list.add(new BedCost(4, "شش تخته", "380"));
        list.add(new BedCost(5, "هشت تخته", "320"));
        list.add(new BedCost(6, "ده تخته", "270"));

        return list;
    }
}
