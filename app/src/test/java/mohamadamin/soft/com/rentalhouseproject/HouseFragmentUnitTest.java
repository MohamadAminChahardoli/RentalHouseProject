package mohamadamin.soft.com.rentalhouseproject;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowActivity;

import mohamadamin.soft.com.rentalhouseproject.Activities.DetailActivity;
import mohamadamin.soft.com.rentalhouseproject.Activities.MainActivity;
import mohamadamin.soft.com.rentalhouseproject.Fragments.HouseItemFragment;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricTestRunner.class)
public class HouseFragmentUnitTest
{
    private MainActivity MainActivityInstance;
    private HouseItemFragment FragmentInstance;

    @Before
    public void setup()
    {
        MainActivityInstance= Robolectric.setupActivity(MainActivity.class);
        FragmentInstance = new HouseItemFragment();
        FragmentManager fragmentManager = MainActivityInstance.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(FragmentInstance, "");
        fragmentTransaction.commit();
    }

    @Test
    public void detailActivityStartedOnButtonClicked()
    {
        Button btn =  FragmentInstance.getView().findViewById(R.id.btn_show_item);
        btn.performClick();
        Intent expectedIntent = new Intent(MainActivityInstance, DetailActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(MainActivityInstance);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        Assert.assertTrue("not equal",actualIntent.filterEquals(expectedIntent));

    }
}
