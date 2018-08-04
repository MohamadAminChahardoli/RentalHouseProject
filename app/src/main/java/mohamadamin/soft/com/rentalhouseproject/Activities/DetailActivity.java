package mohamadamin.soft.com.rentalhouseproject.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mohamadamin.soft.com.rentalhouseproject.Adapters.CostAdapter;
import mohamadamin.soft.com.rentalhouseproject.Dialogs.CostsViewerDialog;
import mohamadamin.soft.com.rentalhouseproject.Models.BedCost;
import mohamadamin.soft.com.rentalhouseproject.R;
import mohamadamin.soft.com.rentalhouseproject.UtilityClasses.ComponentInitializer;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity
{

    private ImageView ImgMainHousePhoto;
    private TextView TxtHouseTitle;
    private TextView TxtHouseZoneAndTime;
    private TextView TxtHouseMortgage;
    private TextView TxtHouseMonthlyRent;
    private Button BtnShowCosts;
    private Button BtnShowOnMap;
    private Button BtnCallToOwner;


    @Override
    protected void attachBaseContext(Context newBase)
    {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initializeComponent();

        Bundle bundle=getIntent().getExtras();

        ImgMainHousePhoto.setImageResource(bundle.getInt("house_photo"));
        TxtHouseTitle.setText(bundle.getString("house_title"));
        TxtHouseZoneAndTime.setText(bundle.getString("house_zone_and_time"));
        TxtHouseMortgage.setText(bundle.getString("house_mortgage"));
        TxtHouseMonthlyRent.setText(bundle.getString("house_monthly_rent"));

    }

    private void initializeComponent()
    {
        ImgMainHousePhoto = findViewById(R.id.img_house_main_photo_detail);
        TxtHouseTitle = findViewById(R.id.txt_house_title_detail);
        TxtHouseZoneAndTime = findViewById(R.id.txt_house_zone_and_registered_time_detail);
        TxtHouseMortgage = findViewById(R.id.txt_house_mortgage_detail);
        TxtHouseMonthlyRent = findViewById(R.id.txt_house_monthly_rent_detail);
        BtnShowCosts = findViewById(R.id.btn_show_cost);
        BtnShowOnMap = findViewById(R.id.btn_show_on_map);
        BtnCallToOwner = findViewById(R.id.btn_call_to_owner);
    }

    public void finishActivity(View view)
    {
        supportFinishAfterTransition();
    }


    public void showCosts(View view)
    {
        CostsViewerDialog dialog = new CostsViewerDialog();
        //dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(),"");
    }
}
