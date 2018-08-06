package mohamadamin.soft.com.rentalhouseproject.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import mohamadamin.soft.com.rentalhouseproject.Fragments.HouseItemFragment;


public class HousesViewPagerAdapter extends FragmentPagerAdapter
{
    private ArrayList<SecondaryHouse> Houses;


    public HousesViewPagerAdapter(FragmentManager fm, ArrayList<SecondaryHouse> houses)
    {
        super(fm);
        this.Houses = houses;
    }

    @Override
    public Fragment getItem(int position)
    {
        return HouseItemFragment.getInstance(Houses.get(position));
    }

    @Override
    public int getCount()
    {
        return Houses.size();
    }

}
