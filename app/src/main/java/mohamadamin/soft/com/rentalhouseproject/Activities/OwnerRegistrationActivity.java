package mohamadamin.soft.com.rentalhouseproject.Activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import mohamadamin.soft.com.rentalhouseproject.Fragments.OwnerLoginFragment;
import mohamadamin.soft.com.rentalhouseproject.R;


public class OwnerRegistrationActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_owner_registration);
        //-------------
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.owner_registration_placeholder, new OwnerLoginFragment());
        fragmentTransaction.commit();

    }
}
