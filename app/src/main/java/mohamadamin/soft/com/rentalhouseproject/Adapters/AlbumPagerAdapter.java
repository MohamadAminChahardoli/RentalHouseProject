package mohamadamin.soft.com.rentalhouseproject.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Collections;

import mohamadamin.soft.com.rentalhouseproject.Fragments.HouseItemFragment;
import mohamadamin.soft.com.rentalhouseproject.Fragments.HousePhotoFragment;
import mohamadamin.soft.com.rentalhouseproject.Models.HousePhoto;
import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;

public class AlbumPagerAdapter extends FragmentPagerAdapter
{
    private ArrayList<String> PhotosNameList;


    public AlbumPagerAdapter(FragmentManager fm)
    {
        super(fm);
        PhotosNameList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position)
    {
        return HousePhotoFragment.getInstance(PhotosNameList.get(position));
    }

    @Override
    public int getCount()
    {
        return PhotosNameList.size();
    }

    public void insertNewPhotos(ArrayList<String> photosNameList)
    {
        PhotosNameList.addAll(photosNameList);
        this.notifyDataSetChanged();
        Collections.reverse(PhotosNameList);
    }
}