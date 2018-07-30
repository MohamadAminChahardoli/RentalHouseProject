package mohamadamin.soft.com.rentalhouseproject.Models;

import java.util.ArrayList;
import java.util.Random;

import mohamadamin.soft.com.rentalhouseproject.R;

public class SecondaryHouse
{
    private String name;
    private int photo;
    private int visitedCount;

    public SecondaryHouse()
    {
    }

    public SecondaryHouse(String name, int photo, int visitedCount)
    {
        this.name = name;
        this.photo = photo;
        this.visitedCount = visitedCount;
    }

    public String getName()
    {
        return name;
    }

    public int getPhoto()
    {
        return photo;
    }

    public int getVisitedCount()
    {
        return visitedCount;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPhoto(int photo)
    {
        this.photo = photo;
    }

    public void setVisitedCount(int visitedCount)
    {
        this.visitedCount = visitedCount;
    }

    public static ArrayList<SecondaryHouse> createHouses(int numberOfHouses)
    {
        ArrayList<SecondaryHouse> housesList = new ArrayList<SecondaryHouse>();
        for (int i = 1; i <= numberOfHouses; i++)
        {
            housesList.add(new SecondaryHouse(" خانه زیبا "+i, R.drawable.pic3,i+170));
        }
        return housesList;
    }

    private static String makeName()
    {
        ArrayList<String> list=new ArrayList<>();
        list.add(" زیبا و جا دار با ویوو بسیار عالی در مرکز شهر");
        list.add(" زیبا و جا دار با ویوو بسیار عالی در مرکز شهرزیبا و جا دار با ویوو بسیار عالی در مرکز شهر");
        list.add(" زیبا و جا دیوو بسیار عالی در مرکز شهربسیار عالی در مرکز شهر");
        list.add(" زیبا و جا دار و بسیار عال شهربسیار عالی در مرکز شهربسیار عالی در مرکز شهربسیار عالی در مرکز شهربسیار عالی در مرکز شهر");
        list.add(" زیبا و جا دار باسیار عالی در مرکز شهربسیار عالی در مرکز شهربسیار عالی در مرکز شهر");

        Random rnd=new Random();
        int random= rnd.nextInt(5);
        return list.get(random);
    }
}

