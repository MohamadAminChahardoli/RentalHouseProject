package mohamadamin.soft.com.rentalhouseproject.Models;

import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.R;

public class SliderModel
{
    private int Id;
    private int BannerPhoto;


    public SliderModel()
    {
    }

    public SliderModel(int id, int bannerPhoto)
    {
        Id = id;
        BannerPhoto = bannerPhoto;
    }


    public int getId()
    {
        return Id;
    }

    public int getBannerPhoto()
    {
        return BannerPhoto;
    }


    public void setId(int id)
    {
        Id = id;
    }

    public void setBannerPhoto(int bannerPhoto)
    {
        BannerPhoto = bannerPhoto;
    }

}
