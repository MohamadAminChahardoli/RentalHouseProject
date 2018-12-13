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

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

import mohamadamin.soft.com.rentalhouseproject.Activities.DetailActivity;
import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import mohamadamin.soft.com.rentalhouseproject.R;

public class HouseItemFragment extends Fragment implements View.OnClickListener {

    private ImageView ImgHousePhoto;
    private TextView TxtHouseTitle;
    private TextView TxtHouseZoneAndRegisteredTime;
    private TextView TxtHouseMortgage;
    private TextView TxtHouseMortgageLabel;
    private TextView TxtHouseMonthlyRent;
    private TextView TxtHouseMonthlyRentLabel;
    private TextView TxtHouseType;
    private TextView TxtHouseTypeLabel;
    private TextView TxtHouseVisitedCount;
    private final int DORM = 1;
    private final int HOUSE = 2;
    private RequestOptions Options;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.pager_item_layout, container, false);
        initializeComponent(itemView);
        bindViewsContents();
        itemView.setOnClickListener(this);
        return itemView;
    }

    @Override
    public void onClick(View view) {
        showDetailOfCurrentItem();
    }

    private void showDetailOfCurrentItem() {
        Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
        detailIntent.putExtras(getArguments());
        Pair<View, String> p1 = Pair.create((View) ImgHousePhoto, "mainHousePhoto");
        Pair<View, String> p2 = Pair.create((View) TxtHouseTitle, "houseTitle");
        Pair<View, String> p3 = Pair.create((View) TxtHouseZoneAndRegisteredTime, "houseZoneAndTime");
        Pair<View, String> p4 = Pair.create((View) TxtHouseMortgage, "houseMortgage");
        Pair<View, String> p5 = Pair.create((View) TxtHouseMortgageLabel, "houseMortgageLabel");
        Pair<View, String> p6 = Pair.create((View) TxtHouseMonthlyRent, "houseMonthlyRent");
        Pair<View, String> p7 = Pair.create((View) TxtHouseMonthlyRentLabel, "houseMonthlyRentLabel");
        Pair<View, String> p8 = Pair.create((View) TxtHouseTypeLabel, "houseTypeLabel");
        Pair<View, String> p9 = Pair.create((View) TxtHouseType, "houseType");
        Pair<View, String> p10 = Pair.create((View) TxtHouseVisitedCount, "houseVisitedCount");

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);

        startActivity(detailIntent, options.toBundle());
    }

    private void initializeComponent(View view) {
        ImgHousePhoto = view.findViewById(R.id.img_house_main_photo);
        TxtHouseTitle = view.findViewById(R.id.txt_house_title);
        TxtHouseZoneAndRegisteredTime = view.findViewById(R.id.txt_house_zone_and_registered_time);
        TxtHouseMortgage = view.findViewById(R.id.txt_house_mortgage);
        TxtHouseMortgageLabel = view.findViewById(R.id.txt_house_mortgage_label);
        TxtHouseMonthlyRent = view.findViewById(R.id.txt_house_monthly_rent);
        TxtHouseMonthlyRentLabel = view.findViewById(R.id.txt_house_monthly_rent_label);
        TxtHouseType = view.findViewById(R.id.txt_house_type);
        TxtHouseTypeLabel = view.findViewById(R.id.txt_house_type_label);
        TxtHouseVisitedCount = view.findViewById(R.id.txt_house_visited_count_item);

        Options = new RequestOptions()
                .centerInside()
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .priority(Priority.HIGH);
    }

    private void bindViewsContents() {
        String imageUrl = getString(R.string.houseImageFolder) + getArguments().getString("house_photo");
        Glide.with(getContext()).load(imageUrl).apply(Options).into(ImgHousePhoto);
        TxtHouseTitle.setText(getArguments().getString("house_title"));
        TxtHouseZoneAndRegisteredTime.setText(getArguments().getString("house_zone_and_time"));
        TxtHouseMortgage.setText(getArguments().getString("house_mortgage"));
        TxtHouseMonthlyRent.setText(getArguments().getString("house_monthly_rent"));
        int houseType = getArguments().getInt("house_type");
        if (houseType == DORM)
            TxtHouseType.setText(getString(R.string.dormText));
        else if (houseType == HOUSE)
            TxtHouseType.setText(getString(R.string.houseText));
        TxtHouseVisitedCount.setText(getArguments().getInt("house_visited_count") + " بازدید");
    }

    public static Fragment getInstance(SecondaryHouse houseModel) {
        HouseItemFragment fragment = new HouseItemFragment();
        fragment.setArguments(createArguments(houseModel));
        return fragment;
    }

    private static Bundle createArguments(SecondaryHouse houseModel) {
        Bundle args = new Bundle();
        args.putInt("house_id", houseModel.getId());
        args.putString("house_title", houseModel.getTitle());
        args.putString("house_photo", houseModel.getPhoto());
        args.putString("house_zone_and_time", houseModel.getDateOfRegister() + " در " + houseModel.getZone());
        args.putString("house_mortgage", houseModel.getMortgage());
        args.putString("house_monthly_rent", houseModel.getMonthlyRent());
        args.putInt("house_type", houseModel.getType());
        args.putInt("house_visited_count", houseModel.getVisitedCount());
        return args;
    }


}
