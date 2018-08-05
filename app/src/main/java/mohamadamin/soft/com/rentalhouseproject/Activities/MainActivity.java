package mohamadamin.soft.com.rentalhouseproject.Activities;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragStateListener;

import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.Adapters.MainSliderPagerAdapter;
import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import mohamadamin.soft.com.rentalhouseproject.Models.SliderModel;
import mohamadamin.soft.com.rentalhouseproject.PagerTransformers.SimpleCardsPagerTransformer;
import mohamadamin.soft.com.rentalhouseproject.Adapters.HousesViewPagerAdapter;
import mohamadamin.soft.com.rentalhouseproject.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
{

    private ViewPager MainViewPager;
    private ViewPager MainSliderViewPager;
    private AppCompatSeekBar SBChangeItems;
    private LinearLayout BottomSheetFilter;
    private BottomSheetBehavior BottomSheetBehavior;
    private BottomSheetDialog BottomSheetDialog;
    private SlidingRootNav DrawerMenu;
    private SlidingRootNav SlidingFilter;
    private boolean IsSlidingOpened = false;
    private int currentPage = 0;
    private Handler SliderHandler;

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
        setupSlider();
        setupViewPager();
        setupSlidingNavigationDrawer();
        setupSlidingFilter();
        //initializeFilterBottomSheet();
        //initializeBottomSheetDialog();

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



        SliderHandler = new Handler();
        SliderHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                slide();
            }
        });



    }



    public void openDrawerMenu(View view)
    {
        if (SlidingFilter.isMenuOpened())
        {
            SlidingFilter.closeMenu();
        }
        DrawerMenu.openMenu(true);
    }

    public void openSlidingFilter(View view)
    {
        if (DrawerMenu.isMenuOpened())
        {
            DrawerMenu.closeMenu();
        }
        SlidingFilter.openMenu(true);
    }

    private void setupSlidingNavigationDrawer()
    {
        DrawerMenu = new SlidingRootNavBuilder(this)
                .withGravity(SlideGravity.RIGHT)
                .withMenuLayout(R.layout.menu_left_drawer_layout)
                .withContentClickableWhenMenuOpened(false)
                .addDragStateListener(new DragStateListener()
                {
                    @Override
                    public void onDragStart()
                    {
                        if (SlidingFilter.isMenuOpened())
                        {
                            SlidingFilter.closeMenu();
                        }
                    }

                    @Override
                    public void onDragEnd(boolean isMenuOpened)
                    {

                    }
                })
                .inject();
    }
    private void setupSlidingFilter()
    {
        SlidingFilter = new SlidingRootNavBuilder(this)
                .withGravity(SlideGravity.LEFT)
                .withRootViewScale(0.009999999776482582f)
                .withContentClickableWhenMenuOpened(false)
                .withMenuLayout(R.layout.filter_bottom_sheet)
                .addDragStateListener(new DragStateListener()
                {
                    @Override
                    public void onDragStart()
                    {
                        if (DrawerMenu.isMenuOpened())
                        {
                            DrawerMenu.closeMenu();
                            IsSlidingOpened = true;
                        }
                    }

                    @Override
                    public void onDragEnd(boolean isMenuOpened)
                    {
                        if (IsSlidingOpened)
                        {
                            SlidingFilter.closeMenu();
                            IsSlidingOpened = false;
                        }
                    }
                })
                .inject();
    }




   /* private void initializeBottomSheetDialog()
    {
        BottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = this.getLayoutInflater().inflate(R.layout.filter_bottom_sheet, null);
        BottomSheetDialog.setContentView(sheetView);
        BottomSheetDialog.setCancelable(false);
    }
*/
    private void initializeComponents()
    {
        MainViewPager = findViewById(R.id.view_pager_main);
        MainSliderViewPager = findViewById(R.id.view_pager_main_slider);
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
        FragmentPagerAdapter adapterViewPager=new HousesViewPagerAdapter
                (getSupportFragmentManager(),getListOfHouses(20));
        MainViewPager.setAdapter(adapterViewPager);
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

    private void setupSlider()
    {
        FragmentPagerAdapter adapterViewPager=new MainSliderPagerAdapter
                (getSupportFragmentManager(), SliderModel.createBanners());
        MainSliderViewPager.setAdapter(adapterViewPager);
        MainSliderViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    private void slide()
    {
        if (currentPage == MainSliderViewPager.getAdapter().getCount())
        {
            currentPage = 0;
        }
        MainSliderViewPager.setCurrentItem(currentPage++, true);
        SliderHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                slide();
            }
        }, 4000);
    }




    private ArrayList<SecondaryHouse> getListOfHouses(int numberOfHouses)
    {
        return SecondaryHouse.createHouses(numberOfHouses);
    }

    private int calculatePagerMargin()
    {
        float density = getResources().getDisplayMetrics().density;
        //int partialWidth = (int) (16 * density); // 16dp
        int pageMargin = (int) (20 * density); // 15dp
        return pageMargin;
    }

    private int calculatePagePadding(int pageMargin)
    {
        int viewPagerPadding = 80 + pageMargin;
        return viewPagerPadding;
    }

    /*private void initializeFilterBottomSheet()
    {
        BottomSheetBehavior = BottomSheetBehavior.from(BottomSheetFilter);
        BottomSheetBehavior.setPeekHeight(0);
    }*/

    /*public void showDialogForPerformFilters(View view)
    {
        BottomSheetDialog.show();
    }

    public void dismissFilterDialog(View view)
    {
        BottomSheetDialog.dismiss();
    }*/



    @Override
    public void onBackPressed()
    {
        if(DrawerMenu.isMenuOpened())
        {
            DrawerMenu.closeMenu(true);
        }
        else if(SlidingFilter.isMenuOpened())
        {
            SlidingFilter.closeMenu(true);
        }
        else
        {
            super.onBackPressed();
        }

    }


}
