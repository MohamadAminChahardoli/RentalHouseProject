package mohamadamin.soft.com.rentalhouseproject.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;
import mohamadamin.soft.com.rentalhouseproject.Adapters.AlbumPagerAdapter;
import mohamadamin.soft.com.rentalhouseproject.Adapters.CostAdapter;
import mohamadamin.soft.com.rentalhouseproject.Adapters.HousesViewPagerAdapter;
import mohamadamin.soft.com.rentalhouseproject.Implementations.MainViewPagerPageChangeListenerImpl;
import mohamadamin.soft.com.rentalhouseproject.Models.BedCost;
import mohamadamin.soft.com.rentalhouseproject.Models.HousePhoto;
import mohamadamin.soft.com.rentalhouseproject.PagerTransformers.SimpleCardsPagerTransformer;
import mohamadamin.soft.com.rentalhouseproject.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity
{

    //private ImageView ImgMainHousePhoto;
    private TextView TxtHouseTitle;
    private TextView TxtHouseZoneAndTime;
    private TextView TxtHouseMortgage;
    private TextView TxtHouseMonthlyRent;
    private ListView ListViewCosts;
    private LinearLayout CostsBottomSheet;
    private BottomSheetBehavior SheetBehavior;



    @Override
    protected void attachBaseContext(Context newBase)
    {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed()
    {
        if (SheetBehavior.getState()==BottomSheetBehavior.STATE_EXPANDED)
        {
            hideCostsBottomSheet();
        }
        else
        {
            super.onBackPressed();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initializeComponent();
        bindViewsContentFromBundle();
        setupViewPager();
        setupCostsListView();
        initializeBottomSheetBehavior();
    }

    private void initializeComponent()
    {
        //ImgMainHousePhoto = findViewById(R.id.img_house_main_photo_detail);
        TxtHouseTitle = findViewById(R.id.txt_house_title_detail);
        TxtHouseZoneAndTime = findViewById(R.id.txt_house_zone_and_registered_time_detail);
        TxtHouseMortgage = findViewById(R.id.txt_house_mortgage_detail);
        TxtHouseMonthlyRent = findViewById(R.id.txt_house_monthly_rent_detail);
        CostsBottomSheet = findViewById(R.id.costs_bottom_sheet);
        ListViewCosts = findViewById(R.id.list_view_costs);
    }

    private void bindViewsContentFromBundle()
    {
        Bundle bundle=getIntent().getExtras();
        //ImgMainHousePhoto.setImageResource(bundle.getInt("house_photo"));
        TxtHouseTitle.setText(bundle.getString("house_title"));
        TxtHouseZoneAndTime.setText(bundle.getString("house_zone_and_time"));
        TxtHouseMortgage.setText(bundle.getString("house_mortgage"));
        TxtHouseMonthlyRent.setText(bundle.getString("house_monthly_rent"));
    }

    private void setupViewPager()
    {
        ViewPager viewPagerAlbum = findViewById(R.id.view_pager_house_photos);
        CircleIndicator indicator = findViewById(R.id.indicator_detail);
        FragmentPagerAdapter adapterViewPager = new AlbumPagerAdapter
                (getSupportFragmentManager(), HousePhoto.createList());
        viewPagerAlbum.setAdapter(adapterViewPager);
        indicator.setViewPager(viewPagerAlbum);
    }

    private void setupCostsListView()
    {
        CostAdapter adapter = new CostAdapter(this, BedCost.createList());
        ListViewCosts.setAdapter(adapter);
    }

    private void initializeBottomSheetBehavior()
    {
        SheetBehavior = BottomSheetBehavior.from(CostsBottomSheet);
    }

    public void showCostsBottomSheet(View view)
    {
        SheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void hideCostsBottomSheet()
    {
        SheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public void showOnGoogleMap(View view)
    {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 51.46504, 35.63266);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    public void finishActivity(View view)
    {
        supportFinishAfterTransition();
    }

}
