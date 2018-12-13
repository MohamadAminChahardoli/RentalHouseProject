package mohamadamin.soft.com.rentalhouseproject.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import mohamadamin.soft.com.rentalhouseproject.Models.Slide;
import mohamadamin.soft.com.rentalhouseproject.Models.SliderModel;
import mohamadamin.soft.com.rentalhouseproject.R;

public class BannerFragment extends Fragment
{
    private ImageView ImgBannerPhoto;
    private RequestOptions Options;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.main_banner_slider_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponent(view);
        bindViewsContents();
    }

    private void initializeComponent(View view)
    {
        ImgBannerPhoto = view.findViewById(R.id.img_banner_main_slider);
        Options = new RequestOptions()
                .centerInside()
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .priority(Priority.HIGH);
    }

    private void bindViewsContents()
    {
        String imageUrl = getString(R.string.sliderImageFolder) + getArguments().getString("banner_photo");
        Glide.with(getContext()).load(imageUrl).into(ImgBannerPhoto);
    }

    public static Fragment getInstance(Slide slide)
    {
        BannerFragment fragment = new BannerFragment();
        fragment.setArguments(createArguments(slide));
        return fragment;
    }

    private static Bundle createArguments(Slide slide)
    {
        Bundle args = new Bundle();
        args.putString("banner_photo",slide.getSliderImage());
        return args;
    }

}
