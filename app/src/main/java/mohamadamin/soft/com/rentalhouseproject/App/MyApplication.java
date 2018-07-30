package mohamadamin.soft.com.rentalhouseproject.App;

import android.app.Application;
import android.content.Context;

import mohamadamin.soft.com.rentalhouseproject.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication extends Application
{
    private static Context context;

    public static Context getContext()
    {
        return context;
    }
    @Override
    public void onCreate()
    {
        super.onCreate();
        context=getApplicationContext();
        initializeCalligraphy();
    }

    private void initializeCalligraphy()
    {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSansMobile.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
