package mohamadamin.soft.com.rentalhouseproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import mohamadamin.soft.com.rentalhouseproject.Models.SliderModel;
import mohamadamin.soft.com.rentalhouseproject.R;

public class BannerFragment extends Fragment
{
    private ImageView ImgBannerPhoto;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View itemView =
                inflater.inflate(R.layout.main_banner_slider_layout,container,false);
        initializeComponent(itemView);
        bindViewsContents();
        return itemView;
    }

    private void initializeComponent(View view)
    {
        ImgBannerPhoto = view.findViewById(R.id.img_banner_main_slider);
    }

    private void bindViewsContents()
    {
        ImgBannerPhoto.setImageResource(getArguments().getInt("banner_photo"));

    }

    public static Fragment getInstance(SliderModel sliderModel)
    {
        BannerFragment fragment = new BannerFragment();
        fragment.setArguments(createArguments(sliderModel));
        return fragment;
    }

    private static Bundle createArguments(SliderModel sliderModel)
    {
        Bundle args=new Bundle();
        args.putInt("banner_photo",sliderModel.getBannerPhoto());
        return args;
    }

}
