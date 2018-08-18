package mohamadamin.soft.com.rentalhouseproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import mohamadamin.soft.com.rentalhouseproject.R;

public class EditProfileOwnerActivity extends AppCompatActivity
{
    private EditText edtProfileOwnerName;
    private EditText edtProfileOwnerFamily;
    private EditText edtProfileOwnerMobile;
    private Spinner spProfileStates;
    private Spinner spProfileCities;
    private Button btnEditOwnerProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_owner);
        initViews();
        initViewsEvents();
    }


    private void initViews()
    {
        edtProfileOwnerName = findViewById(R.id.edt_profile_owner_name);
        edtProfileOwnerFamily = findViewById(R.id.edt_profile_owner_family);
        edtProfileOwnerMobile = findViewById(R.id.edt_profile_owner_mobile);
        spProfileStates = findViewById(R.id.sp_profile_states);
        spProfileCities = findViewById(R.id.sp_profile_cities);
        btnEditOwnerProfile = findViewById(R.id.btn_edit_owner_profile);
    }

    private void initViewsEvents()
    {

    }

}
