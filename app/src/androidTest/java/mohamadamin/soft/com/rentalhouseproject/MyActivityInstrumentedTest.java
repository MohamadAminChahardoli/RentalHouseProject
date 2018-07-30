package mohamadamin.soft.com.rentalhouseproject;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class MyActivityInstrumentedTest
{
    /*@Rule
    public ActivityTestRule<MyActivity> ActivityTestRuleInstance =
            new ActivityTestRule<>(MyActivity.class);*/

    @Test
    public void validateEditText()
    {
        onView(withId(R.id.edt_1)).perform(typeText("Hello Android UI Test"))
                .check(matches(withText("Hello Android UI Test")));
    }

}
