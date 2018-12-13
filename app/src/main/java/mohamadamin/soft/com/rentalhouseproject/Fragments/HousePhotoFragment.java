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

import mohamadamin.soft.com.rentalhouseproject.R;

public class HousePhotoFragment extends Fragment
{
    private ImageView ImgHousePhoto;
    private RequestOptions Options;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.house_photos_item_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponent(view);
        bindViewsContents();
    }

    private void initializeComponent(View view)
    {
        ImgHousePhoto = view.findViewById(R.id.img_house_photo_item);
        Options = new RequestOptions()
                .centerInside()
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .priority(Priority.HIGH);
    }

    private void bindViewsContents()
    {
        String imageUrl = getString(R.string.houseImageFolder) + getArguments().getString("house_photo");
        Glide.with(getContext()).load(imageUrl).apply(Options).into(ImgHousePhoto);
    }

    public static Fragment getInstance(String housePhotoName)
    {
        HousePhotoFragment fragment = new HousePhotoFragment();
        fragment.setArguments(createArguments(housePhotoName));
        return fragment;
    }

    private static Bundle createArguments(String housePhotoName)
    {
        Bundle args=new Bundle();
        args.putString("house_photo", housePhotoName);
        return args;
    }

}

