package mohamadamin.soft.com.rentalhouseproject.Activities;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.jar.Manifest;

import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import mohamadamin.soft.com.rentalhouseproject.PagerTransformers.SimpleCardsPagerTransformer;
import mohamadamin.soft.com.rentalhouseproject.Adapters.HousesViewPagerAdapter;
import mohamadamin.soft.com.rentalhouseproject.R;
import mohamadamin.soft.com.rentalhouseproject.UtilityClasses.ComponentInitializer;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
{

    private ViewPager MainViewPager;
    private AppCompatSeekBar SBChangeItems;
    private LinearLayout BottomSheetFilter;
    private BottomSheetBehavior BottomSheetBehavior;
    private BottomSheetDialog BottomSheetDialog;


    @Override
    protected void attachBaseContext(Context newBase)
    {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        setupViewPager();
        initializeFilterBottomSheet();
        initializeBottomSheetDialog();

        SBChangeItems.setMax(MainViewPager.getAdapter().getCount());
        SBChangeItems.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                MainViewPager.setCurrentItem(SBChangeItems.getProgress(),true);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

    }

    private void initializeBottomSheetDialog()
    {
        BottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = this.getLayoutInflater().inflate(R.layout.filter_bottom_sheet, null);
        BottomSheetDialog.setContentView(sheetView);
        BottomSheetDialog.setCancelable(false);
    }

    private void initializeComponents()
    {
        MainViewPager = findViewById(R.id.view_pager_main);
        BottomSheetFilter = findViewById(R.id.linear_filter);
        SBChangeItems = findViewById(R.id.sb_change_items);
    }

    private void setupViewPager()
    {
        MainViewPager.setClipToPadding(false);
        int pageMargin = calculatePagerMargin();
        MainViewPager.setPageMargin(pageMargin);
        int viewPagerPadding = calculatePagePadding(pageMargin);
        MainViewPager.setPadding(viewPagerPadding, 0, viewPagerPadding, 0);
        MainViewPager.setAdapter(createAdapterForPager());
        MainViewPager.setPageTransformer(false,new SimpleCardsPagerTransformer());

        MainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                SBChangeItems.setProgress(position);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    private FragmentPagerAdapter createAdapterForPager()
    {
        FragmentPagerAdapter adapterViewPager=new HousesViewPagerAdapter
                (getSupportFragmentManager(),getListOfHouses(20));
        return adapterViewPager;
    }

    private ArrayList<SecondaryHouse> getListOfHouses(int numberOfHouses)
    {
        return SecondaryHouse.createHouses(numberOfHouses);
    }

    private int calculatePagerMargin()
    {
        float density = getResources().getDisplayMetrics().density;
        //int partialWidth = (int) (16 * density); // 16dp
        int pageMargin = (int) (15 * density); // 15dp
        return pageMargin;
    }

    private int calculatePagePadding(int pageMargin)
    {
        int viewPagerPadding = 60 + pageMargin;
        return viewPagerPadding;
    }

    private void initializeFilterBottomSheet()
    {
        BottomSheetBehavior = BottomSheetBehavior.from(BottomSheetFilter);
        BottomSheetBehavior.setPeekHeight(0);
    }

    public void showDialogForPerformFilters(View view)
    {
        BottomSheetDialog.show();
    }

    public void dismissFilterDialog(View view)
    {
        BottomSheetDialog.dismiss();
    }

}
