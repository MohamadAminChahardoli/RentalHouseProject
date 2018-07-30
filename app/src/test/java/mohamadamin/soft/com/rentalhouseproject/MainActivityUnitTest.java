package mohamadamin.soft.com.rentalhouseproject;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import mohamadamin.soft.com.rentalhouseproject.Activities.MainActivity;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricTestRunner.class)
public class MainActivityUnitTest
{
    private MainActivity MainActivityInstance;
    private ActivityController<MainActivity> controller;


    @Before
    public void setup()
    {
        MainActivityInstance = Robolectric.setupActivity(MainActivity.class);
        controller = Robolectric.buildActivity(MainActivity.class);
    }

    @Test
    public void validateTextViewFilterContent()
    {
        TextView txtFilterIcon = MainActivityInstance.findViewById(R.id.txt_filter);
        assertNotNull("The text view should not to be null",txtFilterIcon);
        String correctContentForTextView= " فیلتر ";
        assertTrue("The text view should equals with فیلتر",
                correctContentForTextView.equals(txtFilterIcon.getText().toString()));
    }

    
    @Test
    public void check()
    {
        assertTrue("not equal", "hi".equals("hi"));
    }

    private void createWithIntent(String extra)
    {
        Intent intent = new Intent(RuntimeEnvironment.application, MainActivity.class);
        intent.putExtra("activity_extra", extra);
        /*MainActivityInstance = controller
                .withIntent(intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();*/
    }

}
