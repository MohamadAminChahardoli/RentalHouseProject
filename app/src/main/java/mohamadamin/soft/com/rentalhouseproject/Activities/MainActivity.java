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

import me.relex.circleindicator.CircleIndicator;
import mohamadamin.soft.com.rentalhouseproject.Adapters.MainSliderPagerAdapter;
import mohamadamin.soft.com.rentalhouseproject.Implementations.DrawerMenuDragStateListenerImpl;
import mohamadamin.soft.com.rentalhouseproject.Implementations.MainSliderPageChangeListenerImpl;
import mohamadamin.soft.com.rentalhouseproject.Implementations.MainViewPagerPageChangeListenerImpl;
import mohamadamin.soft.com.rentalhouseproject.Implementations.SBChangeItemsOnSeekBarChangeListenerImpl;
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
    private SlidingRootNav DrawerMenu;
    private SlidingRootNav SlidingFilter;
    private boolean IsSlidingOpened = false;
    private int currentPage = 0;
    private Handler SliderHandler;
    private CircleIndicator IndicatorMain;
    private final int SlidingDelay = 3000;


    @Override
    protected void attachBaseContext(Context newBase)
    {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed()
    {
        if (DrawerMenu.isMenuOpened())
        {
            DrawerMenu.closeMenu(true);
        }
        else if (SlidingFilter.isMenuOpened())
        {
            SlidingFilter.closeMenu(true);
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
        setContentView(R.layout.activity_main);
        initializeComponents();
        setupSlider();
        setupViewPager();
        setupSlidingNavigationDrawer();
        setupSlidingFilter();
        setupChangeItemsSeekBar();
    }

    private void initializeComponents()
    {
        MainViewPager = findViewById(R.id.view_pager_main);
        MainSliderViewPager = findViewById(R.id.view_pager_main_slider);
        SBChangeItems = findViewById(R.id.sb_change_items);
        IndicatorMain = findViewById(R.id.indicator_main);
    }

    private void setupSlider()
    {
        FragmentPagerAdapter adapterViewPager = new MainSliderPagerAdapter
                (getSupportFragmentManager(), SliderModel.createBanners());
        MainSliderViewPager.setAdapter(adapterViewPager);
        IndicatorMain.setViewPager(MainSliderViewPager);

        MainSliderViewPager.addOnPageChangeListener(new MainSliderPageChangeListenerImpl()
        {
            @Override
            public void onPageSelected(int position)
            {
                super.onPageSelected(position);
                currentPage = position;
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

    private void setupViewPager()
    {
        MainViewPager.setClipToPadding(false);
        int pageMargin = calculatePagerMargin();
        MainViewPager.setPageMargin(pageMargin);
        int viewPagerPadding = calculatePagePadding(pageMargin);
        MainViewPager.setPadding(viewPagerPadding, 0, viewPagerPadding, 0);
        FragmentPagerAdapter adapterViewPager = new HousesViewPagerAdapter
                (getSupportFragmentManager(), getListOfHouses(20));
        MainViewPager.setAdapter(adapterViewPager);
        MainViewPager.setPageTransformer(false, new SimpleCardsPagerTransformer());

        MainViewPager.addOnPageChangeListener(new MainViewPagerPageChangeListenerImpl()
        {
            @Override
            public void onPageSelected(int position)
            {
                super.onPageSelected(position);
                SBChangeItems.setProgress(position);
            }
        });
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
        int viewPagerPadding = 100 + pageMargin;
        return viewPagerPadding;
    }

    private ArrayList<SecondaryHouse> getListOfHouses(int numberOfHouses)
    {
        return SecondaryHouse.createHouses(numberOfHouses);
    }

    private void setupSlidingNavigationDrawer()
    {
        DrawerMenu = new SlidingRootNavBuilder(this)
                .withGravity(SlideGravity.RIGHT)
                .withMenuLayout(R.layout.menu_left_drawer_layout)
                .withContentClickableWhenMenuOpened(false)
                .addDragStateListener(new DrawerMenuDragStateListenerImpl()
                {
                    @Override
                    public void onDragStart()
                    {
                        super.onDragStart();

                        if (SlidingFilter.isMenuOpened())
                        {
                            SlidingFilter.closeMenu();
                        }
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

    private void setupChangeItemsSeekBar()
    {
        SBChangeItems.setMax(MainViewPager.getAdapter().getCount());
        SBChangeItems.setOnSeekBarChangeListener(new SBChangeItemsOnSeekBarChangeListenerImpl()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                MainViewPager.setCurrentItem(SBChangeItems.getProgress(), true);
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
        }, SlidingDelay);
    }

}