package mohamadamin.soft.com.rentalhouseproject.UtilityClasses;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ComponentInitializer
{
    private Activity ActivityInstance;
    private View ViewInstance;


    public ComponentInitializer(Activity activity)
    {
        ActivityInstance = activity;
    }

    public ComponentInitializer(View view)
    {
        ViewInstance = view;
    }


    public <T extends View> T initializeComponent(int componentId)
    {
        return ActivityInstance.findViewById(componentId);
    }

    public <T extends View> T initializeFragmentsComponent(int componentId)
    {
        return ViewInstance.findViewById(componentId);
    }
}
