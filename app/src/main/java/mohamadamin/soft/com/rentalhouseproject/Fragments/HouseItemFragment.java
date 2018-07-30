package mohamadamin.soft.com.rentalhouseproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mohamadamin.soft.com.rentalhouseproject.Activities.DetailActivity;
import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import mohamadamin.soft.com.rentalhouseproject.R;
import mohamadamin.soft.com.rentalhouseproject.UtilityClasses.ComponentInitializer;

public class HouseItemFragment extends Fragment implements View.OnClickListener
{
    private ImageView imgHousePhoto;
    private TextView txtHouseName;
    private TextView txtHouseZone;
    private TextView txtHouseVisitedCount;
    private Button btnShowItem;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View itemView = inflater.inflate(R.layout.pager_item_layout,container,false);
        initializeComponent(itemView);
        bindViewsContents();
        return itemView;
    }

    private void initializeComponent(View view)
    {
        ComponentInitializer componentInitializer = new ComponentInitializer(view);

        imgHousePhoto = componentInitializer.initializeFragmentsComponent(R.id.img_house_main_photo);
        txtHouseName = componentInitializer.initializeFragmentsComponent(R.id.txt_house_name);
        txtHouseZone = componentInitializer.initializeFragmentsComponent(R.id.txt_house_zone);
        txtHouseVisitedCount = componentInitializer.initializeFragmentsComponent(R.id.txt_house_visited_count);
        btnShowItem = componentInitializer.initializeFragmentsComponent(R.id.btn_show_item);
    }

    private void bindViewsContents()
    {
        imgHousePhoto.setImageResource(getArguments().getInt("house_photo"));
        txtHouseName.setText(getArguments().getString("house_name"));
        txtHouseVisitedCount.setText(getArguments().getString("house_visited_count"));
        btnShowItem.setOnClickListener(this);

    }

    public static Fragment getInstance(SecondaryHouse houseModel)
    {
        HouseItemFragment fragment=new HouseItemFragment();
        fragment.setArguments(createArguments(houseModel));
        return fragment;
    }

    private static Bundle createArguments(SecondaryHouse houseModel)
    {
        Bundle args=new Bundle();
        args.putString("house_name",houseModel.getName());
        args.putInt("house_photo",houseModel.getPhoto());
        args.putString("house_visited_count",houseModel.getVisitedCount()+"");
        return args;
    }



    @Override
    public void onClick(View view)
    {
        showDetailOfCurrentItem();
    }

    private void showDetailOfCurrentItem()
    {
        Intent detailIntent=new Intent(getActivity(), DetailActivity.class);
        detailIntent.putExtras(getArguments());
        Pair<View, String> p1 = Pair.create((View)imgHousePhoto, "mainHousePhoto");
        Pair<View, String> p2 = Pair.create((View)txtHouseName, "houseName");
        Pair<View, String> p3 = Pair.create((View)txtHouseZone, "houseZone");
        Pair<View, String> p4 = Pair.create((View)txtHouseVisitedCount, "houseVisitedCount");

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), p1, p2, p3,p4);

        startActivity(detailIntent, options.toBundle());
    }
}
