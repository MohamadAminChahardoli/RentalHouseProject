package mohamadamin.soft.com.rentalhouseproject.Models;

import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.R;

public class HousePhoto
{

    private int Id;
    private int Photo;


    public HousePhoto()
    {
    }

    public HousePhoto(int id, int photo)
    {
        Id = id;
        Photo = photo;
    }

    public int getId()
    {
        return Id;
    }

    public int getPhoto()
    {
        return Photo;
    }

    public void setId(int id)
    {
        Id = id;
    }

    public void setPhoto(int photo)
    {
        Photo = photo;
    }

    public static ArrayList<HousePhoto> createList()
    {
        ArrayList<HousePhoto> list = new ArrayList<>();
        list.add(new HousePhoto(1, R.drawable.pic1));
        list.add(new HousePhoto(2, R.drawable.pic2));
        list.add(new HousePhoto(3, R.drawable.pic3));

        return list;
    }
}
