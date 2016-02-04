package com.appsingularity.testing;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.SmallTest;
import android.test.suitebuilder.annotation.Smoke;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static com.appsingularity.testing.ui.ModelUI.CALCULATE_BTN;
import static com.appsingularity.testing.ui.ModelUI.ERROR_VIEW;
import static com.appsingularity.testing.ui.ModelUI.INPUT_FIELD;
import static com.appsingularity.testing.ui.ModelUI.IS_EMPTY;
import static com.appsingularity.testing.ui.ModelUI.IS_EVEN;
import static com.appsingularity.testing.ui.ModelUI.IS_INVISIBLE;
import static com.appsingularity.testing.ui.ModelUI.IS_ODD;
import static com.appsingularity.testing.ui.ModelUI.IS_VISIBLE;
import static com.appsingularity.testing.ui.ModelUI.RESULT_VIEW;

public class UITests {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    @Smoke
    @SmallTest
    public void testEvenNumber() {
        INPUT_FIELD.perform(typeText("2"));
        CALCULATE_BTN.perform(click());
        ERROR_VIEW.check(IS_INVISIBLE);
        onView(RESULT_VIEW).check(IS_EVEN);
    }

    @Test
    @SmallTest
    public void testOddNumber() {
        INPUT_FIELD.perform(typeText("3"));
        CALCULATE_BTN.perform(click());
        ERROR_VIEW.check(IS_INVISIBLE);
        onView(RESULT_VIEW).check(IS_ODD);
    }

    @Test
    @ErrorTest
    public void testInvalidString() {
        INPUT_FIELD.perform(typeText("meh"));
        CALCULATE_BTN.perform(click());
        ERROR_VIEW.check(IS_VISIBLE);
        onView(RESULT_VIEW).check(IS_EMPTY);
    }

    @Test
    @FractalTest
    public void testFractal() {
        INPUT_FIELD.perform(typeText("3.6"));
        CALCULATE_BTN.perform(click());
        ERROR_VIEW.check(IS_VISIBLE);
        onView(RESULT_VIEW).check(IS_EMPTY);
    }

}