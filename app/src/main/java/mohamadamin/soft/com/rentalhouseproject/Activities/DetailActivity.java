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
    private TextView TxtHouseName;
    private TextView TxtHouseZone;
    private TextView TxtHouseVisitedCount;

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
        TxtHouseName.setText(bundle.getString("house_name"));
        TxtHouseVisitedCount.setText(bundle.getString("house_visited_count"));
    }

    private void initializeComponent()
    {
        ComponentInitializer ci = new ComponentInitializer(this);

        ImgMainHousePhoto = ci.initializeComponent(R.id.img_house_main_photo_detail);
        TxtHouseName = ci.initializeComponent(R.id.txt_house_name_detail);
        TxtHouseZone = ci.initializeComponent(R.id.txt_house_zone_detail);
        TxtHouseVisitedCount = ci.initializeComponent(R.id.txt_house_visited_count_detail);
    }

    public void finishActivity(View view)
    {
        supportFinishAfterTransition();
    }
}
