package mohamadamin.soft.com.rentalhouseproject.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.Fragments.BannerFragment;
import mohamadamin.soft.com.rentalhouseproject.Fragments.HouseItemFragment;
import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import mohamadamin.soft.com.rentalhouseproject.Models.SliderModel;

public class MainSliderPagerAdapter extends FragmentPagerAdapter
{
    private ArrayList<SliderModel> Banners;


    public MainSliderPagerAdapter(FragmentManager fm, ArrayList<SliderModel> banners)
    {
        super(fm);
        Banners = banners;
    }

    @Override
    public Fragment getItem(int position)
    {
        return BannerFragment.getInstance(Banners.get(position));
    }

    @Override
    public int getCount()
    {
        return Banners.size();
    }
}
