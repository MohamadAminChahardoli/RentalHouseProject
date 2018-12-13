package mohamadamin.soft.com.rentalhouseproject.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.relex.circleindicator.CircleIndicator;
import mohamadamin.soft.com.rentalhouseproject.Adapters.AlbumPagerAdapter;
import mohamadamin.soft.com.rentalhouseproject.Adapters.CostAdapter;
import mohamadamin.soft.com.rentalhouseproject.Implementations.HouseDetailsImpl;
import mohamadamin.soft.com.rentalhouseproject.Models.BedCost;
import mohamadamin.soft.com.rentalhouseproject.Models.House;
import mohamadamin.soft.com.rentalhouseproject.R;
import mohamadamin.soft.com.rentalhouseproject.ServerApi.ApiServiceGenerator;
import mohamadamin.soft.com.rentalhouseproject.ServerApi.RentalHouseApi;
import mohamadamin.soft.com.rentalhouseproject.Utils.Networking;
import mohamadamin.soft.com.rentalhouseproject.Utils.SweetDialog;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity
        implements HouseDetailsImpl.OnHouseDetailsReceivedListener, Networking.NetworkStatusListener {

    private ImageButton BtnCall;
    private TextView TxtCallLabel;
    private ImageButton BtnShowMap;
    private TextView TxtShowMapLabel;
    private ImageButton BtnShowBedsCost;
    private TextView TxtShowBedsCostLabel;
    private TextView TxtHouseTitle;
    private TextView TxtHouseZoneAndTime;
    private TextView TxtHouseMortgage;
    private TextView TxtHouseMonthlyRent;
    private TextView TxtHouseType;
    private TextView TxtHouseVisitedCount;
    private TextView TxtHouseAddress;
    private TextView TxtHouseBenefits;
    private TextView TxtHouseDescription;
    private ProgressBar ProgressLoading;
    private ListView ListViewCosts;
    private LinearLayout CostsBottomSheet;
    private BottomSheetBehavior SheetBehavior;
    private final int DORM = 1;
    private final int HOUSE = 2;
    private SweetDialog MessageDialog;
    private int HouseId = 0;
    private AlbumPagerAdapter PhotosAlbumAdapter;
    private String HousePhoneNumber = "";
    private String HouseLatitude = "";
    private String HouseLongitude = "";
    private String HouseFirsPictureName = "";
    private final  int CALL_PHONE_PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (SheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            hideCostsBottomSheet();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initializeComponent();
        bindViewsContentFromBundle();
        initializeBottomSheetBehavior();
        Networking.checkNetwork(this, this, 3);
    }

    @Override
    public void onNetworkConnected(int requestCode) {
        getMoreDetails();
    }

    @Override
    public void onNetworkDisconnected() {
        MessageDialog.setContentText(getString(R.string.noInternetText)).show();
    }

    @Override
    public void onHouseDetailsReceived(Response<House> response) {
        ProgressLoading.setVisibility(View.GONE);
        if (response.body() != null) {
            TxtHouseAddress.setText(response.body().HouseAddress);
            TxtHouseBenefits.setText(response.body().HouseBenefits);
            TxtHouseDescription.setText(response.body().HouseDescription);
            response.body().PhotoNames.add(HouseFirsPictureName);
            PhotosAlbumAdapter.insertNewPhotos(response.body().PhotoNames);
            setupViewPager();
            setupCostsListView(response.body().BedCosts);
            HouseLatitude = response.body().HouseLatitude;
            HouseLongitude = response.body().HouseLongitude;
            HousePhoneNumber = response.body().HousePhoneNumber;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CALL_PHONE_PERMISSION_REQUEST_CODE)
        {
            call(HousePhoneNumber);
        }
    }

    private void initializeComponent() {
        BtnCall = findViewById(R.id.btn_call_to_owner);
        BtnShowMap = findViewById(R.id.btn_show_on_map);
        BtnShowBedsCost = findViewById(R.id.btn_show_cost);
        TxtCallLabel = findViewById(R.id.txt_call_to_owner_caption);
        TxtShowMapLabel = findViewById(R.id.txt_show_on_map_caption);
        TxtShowBedsCostLabel = findViewById(R.id.txt_cost_caption);
        TxtHouseTitle = findViewById(R.id.txt_house_title_detail);
        TxtHouseZoneAndTime = findViewById(R.id.txt_house_zone_and_registered_time_detail);
        TxtHouseMortgage = findViewById(R.id.txt_house_mortgage_detail);
        TxtHouseMonthlyRent = findViewById(R.id.txt_house_monthly_rent_detail);
        TxtHouseType = findViewById(R.id.txt_house_type);
        TxtHouseVisitedCount = findViewById(R.id.txt_house_visited_count);
        TxtHouseAddress = findViewById(R.id.txt_house_address);
        TxtHouseBenefits = findViewById(R.id.txt_house_benefits);
        TxtHouseDescription = findViewById(R.id.txt_house_description);
        ProgressLoading = findViewById(R.id.progressLoadingDetails);
        CostsBottomSheet = findViewById(R.id.costs_bottom_sheet);
        ListViewCosts = findViewById(R.id.list_view_costs);
        PhotosAlbumAdapter = new AlbumPagerAdapter(getSupportFragmentManager());

        MessageDialog = new SweetDialog.Builder()
                .setDialogType(SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.dearUserText))
                .build(this);
    }

    private void bindViewsContentFromBundle() {
        Bundle bundle = getIntent().getExtras();
        HouseId = bundle.getInt("house_id");
        TxtHouseTitle.setText(bundle.getString("house_title"));
        TxtHouseZoneAndTime.setText(bundle.getString("house_zone_and_time"));
        TxtHouseMortgage.setText(bundle.getString("house_mortgage"));
        TxtHouseMonthlyRent.setText(bundle.getString("house_monthly_rent"));
        int houseType = bundle.getInt("house_type");
        if (houseType == DORM)
        {
            TxtHouseType.setText(getString(R.string.dormText));
            BtnShowBedsCost.setVisibility(View.VISIBLE);
            TxtShowBedsCostLabel.setVisibility(View.VISIBLE);
        }
        else if (houseType == HOUSE)
        {
            TxtHouseType.setText(getString(R.string.houseText));
            BtnShowBedsCost.setVisibility(View.INVISIBLE);
            TxtShowBedsCostLabel.setVisibility(View.INVISIBLE);
        }
        TxtHouseVisitedCount.setText(bundle.getInt("house_visited_count") + "");
        HouseFirsPictureName = bundle.getString("house_photo");
    }

    private void setupViewPager() {
        ViewPager viewPagerAlbum = findViewById(R.id.view_pager_house_photos);
        CircleIndicator indicator = findViewById(R.id.indicator_detail);
        viewPagerAlbum.setAdapter(PhotosAlbumAdapter);
        indicator.setViewPager(viewPagerAlbum);
    }

    private void setupCostsListView(ArrayList<BedCost> costsList) {
        CostAdapter adapter = new CostAdapter(this, costsList);
        ListViewCosts.setAdapter(adapter);
    }

    private void initializeBottomSheetBehavior() {
        SheetBehavior = BottomSheetBehavior.from(CostsBottomSheet);
    }

    public void showCostsBottomSheet(View view) {
        SheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void hideCostsBottomSheet() {
        SheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public void showOnGoogleMap(View view) {
        if (HouseLatitude != "" && HouseLongitude != "") {
            String uri = String.format(Locale.ENGLISH, "geo:%f,%f", Double.parseDouble(HouseLatitude), Double.parseDouble(HouseLongitude));
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        }
    }

    public void finishActivity(View view) {
        supportFinishAfterTransition();
    }

    private void getMoreDetails() {
        ProgressLoading.setVisibility(View.VISIBLE);
        RentalHouseApi rentalHouseApi = ApiServiceGenerator.getApiService();
        Call<House> detailsCall = rentalHouseApi.getHouseDetails(HouseId);
        detailsCall.enqueue(new HouseDetailsImpl(this));
    }

    public void showCallDialog(View view) {
        SweetAlertDialog sweetCallDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
        sweetCallDialog.setTitleText("شماره تماس");
        sweetCallDialog.setContentText(HousePhoneNumber);
        sweetCallDialog.setCancelable(false);
        sweetCallDialog.setConfirmButton("تماس", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                call(HousePhoneNumber);
            }
        });

        sweetCallDialog.setCancelButton("لغو", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });

        sweetCallDialog.show();

    }

    private void call(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION_REQUEST_CODE);
            }
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        }
    }
}
