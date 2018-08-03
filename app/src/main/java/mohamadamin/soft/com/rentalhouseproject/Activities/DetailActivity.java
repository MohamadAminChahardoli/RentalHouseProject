package mohamadamin.soft.com.rentalhouseproject.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    }

    public void finishActivity(View view)
    {
        supportFinishAfterTransition();
    }
}
