package mohamadamin.soft.com.rentalhouseproject.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.Fragments.HouseItemFragment;
import mohamadamin.soft.com.rentalhouseproject.Fragments.HousePhotoFragment;
import mohamadamin.soft.com.rentalhouseproject.Models.HousePhoto;
import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;

public class AlbumPagerAdapter extends FragmentPagerAdapter
{
    private ArrayList<HousePhoto> Photos;


    public AlbumPagerAdapter(FragmentManager fm, ArrayList<HousePhoto> photos)
    {
        super(fm);
        this.Photos = photos;
    }

    @Override
    public Fragment getItem(int position)
    {
        return HousePhotoFragment.getInstance(Photos.get(position));
    }

    @Override
    public int getCount()
    {
        return Photos.size();
    }

}