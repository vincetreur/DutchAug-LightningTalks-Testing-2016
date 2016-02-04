package com.appsingularity.testing.ui;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.view.View;

import com.appsingularity.testing.R;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

public abstract class ModelUI {

    public static final ViewInteraction CALCULATE_BTN = onView(withId(R.id.btn_calculate));
    public static final ViewInteraction INPUT_FIELD = onView(withId(R.id.input));
    public static final ViewInteraction ERROR_VIEW = onView(withId(R.id.error));
    public static final Matcher<View> RESULT_VIEW = withId(R.id.result);

    public static final ViewAssertion IS_EMPTY = matches(withText(""));
    public static final ViewAssertion IS_EVEN = matches(withText(R.string.result_even));
    public static final ViewAssertion IS_ODD = matches(withText(R.string.result_odd));

    public static final ViewAssertion IS_INVISIBLE = matches(not(isDisplayed()));
    public static final ViewAssertion IS_VISIBLE = matches(isDisplayed());

}
