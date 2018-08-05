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

    public static ArrayList<SliderModel> createBanners()
    {
        ArrayList<SliderModel> banners =  new ArrayList<>();
        banners.add(new SliderModel(1, R.drawable.pic1));
        banners.add(new SliderModel(2, R.drawable.pic2));
        banners.add(new SliderModel(3, R.drawable.pic3));

        return banners;
    }
}
