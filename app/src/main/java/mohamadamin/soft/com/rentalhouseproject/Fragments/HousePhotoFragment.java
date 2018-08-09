package mohamadamin.soft.com.rentalhouseproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import mohamadamin.soft.com.rentalhouseproject.Models.HousePhoto;
import mohamadamin.soft.com.rentalhouseproject.Models.SliderModel;
import mohamadamin.soft.com.rentalhouseproject.R;

public class HousePhotoFragment extends Fragment
{
    private ImageView ImgHousePhoto;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View itemView =
                inflater.inflate(R.layout.house_photos_item_layout,container,false);
        initializeComponent(itemView);
        bindViewsContents();
        return itemView;
    }

    private void initializeComponent(View view)
    {
        ImgHousePhoto = view.findViewById(R.id.img_house_photo_item);
    }

    private void bindViewsContents()
    {
        ImgHousePhoto.setImageResource(getArguments().getInt("house_photo"));

    }

    public static Fragment getInstance(HousePhoto housePhoto)
    {
        HousePhotoFragment fragment = new HousePhotoFragment();
        fragment.setArguments(createArguments(housePhoto));
        return fragment;
    }

    private static Bundle createArguments(HousePhoto housePhoto)
    {
        Bundle args=new Bundle();
        args.putInt("house_photo",housePhoto.getPhoto());
        return args;
    }

}

