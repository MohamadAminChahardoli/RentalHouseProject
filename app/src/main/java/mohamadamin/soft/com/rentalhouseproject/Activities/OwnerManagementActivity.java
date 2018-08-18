package mohamadamin.soft.com.rentalhouseproject.Activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import mohamadamin.soft.com.rentalhouseproject.Fragments.MainHouseFragment;
import mohamadamin.soft.com.rentalhouseproject.R;

public class OwnerManagementActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_mangement);
        //-------------
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations
                (android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                        android.R.anim.fade_in, android.R.anim.fade_out).
                replace(R.id.owner_management_placeholder, new MainHouseFragment());
      //  fragmentTransaction.addToBackStack(MainHouseFragment.class.getName());
        fragmentTransaction.commit();
    }
}
