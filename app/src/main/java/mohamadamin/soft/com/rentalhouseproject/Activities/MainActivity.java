package mohamadamin.soft.com.rentalhouseproject.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import co.ronash.pushe.Pushe;
import me.angrybyte.numberpicker.listener.OnValueChangeListener;
import me.angrybyte.numberpicker.view.ActualNumberPicker;
import me.relex.circleindicator.CircleIndicator;
import mohamadamin.soft.com.rentalhouseproject.Adapters.MainSliderPagerAdapter;
import mohamadamin.soft.com.rentalhouseproject.Implementations.DownloadAllHousesImpl;
import mohamadamin.soft.com.rentalhouseproject.Implementations.DrawerMenuDragStateListenerImpl;
import mohamadamin.soft.com.rentalhouseproject.Implementations.MainSliderPageChangeListenerImpl;
import mohamadamin.soft.com.rentalhouseproject.Implementations.MainViewPagerPageChangeListenerImpl;
import mohamadamin.soft.com.rentalhouseproject.Implementations.SBChangeItemsOnSeekBarChangeListenerImpl;
import mohamadamin.soft.com.rentalhouseproject.Implementations.DownloadSlidesImpl;
import mohamadamin.soft.com.rentalhouseproject.Models.FilterModel;
import mohamadamin.soft.com.rentalhouseproject.Models.SecondaryHouse;
import mohamadamin.soft.com.rentalhouseproject.Models.Slide;
import mohamadamin.soft.com.rentalhouseproject.PagerTransformers.SimpleCardsPagerTransformer;
import mohamadamin.soft.com.rentalhouseproject.Adapters.HousesViewPagerAdapter;
import mohamadamin.soft.com.rentalhouseproject.R;
import mohamadamin.soft.com.rentalhouseproject.ServerApi.ApiServiceGenerator;
import mohamadamin.soft.com.rentalhouseproject.ServerApi.RentalHouseApi;
import mohamadamin.soft.com.rentalhouseproject.Utils.Networking;
import mohamadamin.soft.com.rentalhouseproject.Utils.SweetDialog;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements
        DownloadSlidesImpl.OnSliderReceivedListener, DownloadAllHousesImpl.OnHousesDownloadedListener,
        Networking.NetworkStatusListener
{

    private ImageButton BtnMenu;
    private ImageButton BtnSearch;
    private CardView CardToolbar;
    private TextView TxtTitle;
    private Button BtnDeleteFilters;
    private ImageButton BtnSubmitFilter;
    private ImageButton BtnCancelFilter;
    private EditText EdtZone;
    private EditText EdtFromMortgage;
    private EditText EdtToMortgage;
    private EditText EdtFromRent;
    private EditText EdtToRent;
    private ViewPager MainViewPager;
    private ViewPager MainSliderViewPager;
    private ActualNumberPicker SBChangeItems;
    private SlidingRootNav DrawerMenu;
    private SlidingRootNav SlidingFilter;
    private boolean IsSlidingOpened = false;
    private int CurrentPage = 0;
    private Handler SliderHandler;
    private CircleIndicator IndicatorMain;
    private final int SlidingDelay = 3000;
    private DialogPlus FilterDialog;
    private int DefaultSkipItem = 0;
    private int DefaultTakeItem = 20;
    private SweetDialog Loading;
    private SweetDialog MessageDialog;

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
        else if (FilterDialog.isShowing())
        {
            FilterDialog.dismiss();
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
        Networking.checkNetwork(this, this, 1);
        setupSlidingNavigationDrawer();
        setupFilterDialog();
        Pushe.initialize(this,true);
    }

    @Override
    public void onSliderReceived(Response<List<Slide>> response) {
        setupSlider(response.body());
        getAllHouses();
    }

    @Override
    public void onHousesDownloaded(Response<List<SecondaryHouse>> response) {
        setupViewPager(response.body());
        Loading.hide();
    }

    @Override
    public void onNetworkConnected(int requestCode) {
        if (requestCode == 1)
        {
            getSlides();
        }
    }

    @Override
    public void onNetworkDisconnected() {
        MessageDialog.setContentText(getString(R.string.noInternetText)).show();
    }

    private void initializeComponents()
    {
        BtnMenu = findViewById(R.id.btn_menu);
        BtnSearch = findViewById(R.id.btn_search);
        CardToolbar = findViewById(R.id.cardToolbar);
        TxtTitle = findViewById(R.id.txtTitle);

        BtnDeleteFilters = findViewById(R.id.btnDeleteFilters);
        BtnSubmitFilter = findViewById(R.id.btn_submit_filter);
        BtnCancelFilter = findViewById(R.id.btn_cancel_filter);
        EdtZone = findViewById(R.id.edtZone);
        EdtFromMortgage = findViewById(R.id.edt_from_mortgage);
        EdtToMortgage = findViewById(R.id.edt_to_mortgage);
        EdtFromRent = findViewById(R.id.edt_from_rent);
        EdtToRent = findViewById(R.id.edt_to_rent);

        MainViewPager = findViewById(R.id.view_pager_main);
        MainSliderViewPager = findViewById(R.id.view_pager_main_slider);
        SBChangeItems = findViewById(R.id.sb_change_items);
        IndicatorMain = findViewById(R.id.indicator_main);
        Loading = new SweetDialog.Builder()
                .setDialogType(SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText(getString(R.string.waiting_text))
                .build(this);
        MessageDialog = new SweetDialog.Builder()
                .setDialogType(SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.dearUserText))
                .build(this);
    }

    private void setupSlider(List<Slide> slides)
    {
        FragmentPagerAdapter adapterViewPager = new MainSliderPagerAdapter
                (getSupportFragmentManager(), slides);
        MainSliderViewPager.setAdapter(adapterViewPager);
        IndicatorMain.setViewPager(MainSliderViewPager);

        MainSliderViewPager.addOnPageChangeListener(new MainSliderPageChangeListenerImpl()
        {
            @Override
            public void onPageSelected(int position)
            {
                super.onPageSelected(position);
                CurrentPage = position;
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

    private void setupViewPager(List<SecondaryHouse> houses)
    {
        MainViewPager.setClipToPadding(false);
        int pageMargin = calculatePagerMargin();
        MainViewPager.setPageMargin(pageMargin);
        int viewPagerPadding = calculatePagePadding(pageMargin);
        MainViewPager.setPadding(viewPagerPadding, 0, viewPagerPadding, 0);
        FragmentPagerAdapter adapterViewPager = new HousesViewPagerAdapter
                (getSupportFragmentManager(), houses);
        MainViewPager.setAdapter(adapterViewPager);
        MainViewPager.setPageTransformer(false, new SimpleCardsPagerTransformer());

        MainViewPager.addOnPageChangeListener(new MainViewPagerPageChangeListenerImpl()
        {
            @Override
            public void onPageSelected(int position)
            {
                super.onPageSelected(position);
                SBChangeItems.setValue(position);
            }
        });

        setupChangeItemsSeekBar();
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
        int viewPagerPadding = 95 + pageMargin;
        return viewPagerPadding;
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

                       /* if (SlidingFilter.isMenuOpened())
                        {
                            SlidingFilter.closeMenu();
                        }*/
                    }
                })
                .inject();
    }

    private void setupChangeItemsSeekBar()
    {
        SBChangeItems.setVisibility(View.VISIBLE);
        SBChangeItems.setMaxValue(MainViewPager.getAdapter().getCount());
        SBChangeItems.setListener(new OnValueChangeListener() {
            @Override
            public void onValueChanged(int oldValue, int newValue) {
                MainViewPager.setCurrentItem(newValue, true);
            }
        });
    }

    public void openDrawerMenu(View view)
    {
        DrawerMenu.openMenu(true);
    }

    private void slide()
    {
        if (CurrentPage == MainSliderViewPager.getAdapter().getCount())
        {
            CurrentPage = 0;
        }
        MainSliderViewPager.setCurrentItem(CurrentPage++, true);
        SliderHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                slide();
            }
        }, SlidingDelay);
    }

    public void showFilterDialog(View view)
    {
        FilterDialog.show();
    }

    public void openSearchActivity(View view)
    {
        Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);

        Pair<View, String> p1 = Pair.create((View) CardToolbar, "cardToolbar");
        Pair<View, String> p2 = Pair.create((View) BtnMenu, "buttonClearText");
        Pair<View, String> p3 = Pair.create((View) BtnSearch, "buttonSearch");
        //Pair<View, String> p4 = Pair.create((View) TxtTitle, "searchText");

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2, p3);
        startActivity(searchIntent, options.toBundle());
    }

    private void setupFilterDialog()
    {
        FilterDialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.filter_bottom_sheet))
                .setGravity(Gravity.TOP)
                .create();
    }

    public void goToOwnerManagement(View view)
    {
        DrawerMenu.closeMenu(true);
        startActivity(new Intent(MainActivity.this, OwnerManagementActivity.class));
    }

    public void goToOwnerRegistration(View view)
    {
        DrawerMenu.closeMenu(true);
        startActivity(new Intent(MainActivity.this, OwnerRegistrationActivity.class));
    }

    private void getSlides() {
        Loading.show();
        RentalHouseApi rentalHouseApi = ApiServiceGenerator.getApiService();
        Call<List<Slide>> slidesCall = rentalHouseApi.getSlides();
        slidesCall.enqueue(new DownloadSlidesImpl(this));
    }

    private void getAllHouses() {
        RentalHouseApi araApi = ApiServiceGenerator.getApiService();
        Call<List<SecondaryHouse>> allHousesCall = araApi.getAllHouses(DefaultSkipItem, DefaultTakeItem);
        allHousesCall.enqueue(new DownloadAllHousesImpl(this));
    }

    private FilterModel getFilterModel()
    {
        FilterModel filterModel = new FilterModel();
        filterModel.Zone = EdtZone.getText().toString();
        filterModel.FromMortgage = EdtFromMortgage.getText().toString() != "" ? Integer.parseInt(EdtFromMortgage.getText().toString()) : 0;
        filterModel.ToMortgage = EdtToMortgage.getText().toString() != "" ? Integer.parseInt(EdtToMortgage.getText().toString()) : 0;
        filterModel.FromRent = Integer.parseInt(EdtFromRent.getText().toString());
        filterModel.ToRent = Integer.parseInt(EdtToRent.getText().toString());
        /*if (RBBothDormAndHouse.isChecked()){}
        else if (RBDorm.isChecked()){filterModel.Type = 1;}
        else if (RBHouse.isChecked()){filterModel.Type = 2;}
        if (RBMale.isChecked()){filterModel.Gender = 1;}
        else if (RBFemale.isChecked()){filterModel.Gender = 0;}
        else if (RBBothMaleAndFemale.isChecked()){filterModel.Gender = 2;}*/

        return filterModel;
    }

}