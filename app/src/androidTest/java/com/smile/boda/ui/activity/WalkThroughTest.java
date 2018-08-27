package com.smile.boda.ui.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.smile.boda.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WalkThroughTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void walkThroughTest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction cardView = onView(
              allOf(withId(R.id.selectOrigin),
                    childAtPosition(
                          childAtPosition(
                                withClassName(is("android.support.v4.widget.NestedScrollView")),
                                0),
                          0),
                    isDisplayed()));
        cardView.perform(click());

        ViewInteraction recyclerView = onView(
              allOf(withId(R.id.recyclerView),
                    childAtPosition(
                          withClassName(is("android.widget.LinearLayout")),
                          1)));
        recyclerView.perform(actionOnItemAtPosition(3, click()));

        ViewInteraction cardView2 = onView(
              allOf(withId(R.id.selectDestination),
                    childAtPosition(
                          childAtPosition(
                                withClassName(is("android.support.v4.widget.NestedScrollView")),
                                0),
                          1),
                    isDisplayed()));
        cardView2.perform(click());

        ViewInteraction recyclerView2 = onView(
              allOf(withId(R.id.recyclerView),
                    childAtPosition(
                          withClassName(is("android.widget.LinearLayout")),
                          1)));
        recyclerView2.perform(actionOnItemAtPosition(6, click()));

        ViewInteraction linearLayout = onView(
              allOf(childAtPosition(
                    childAtPosition(
                          withId(R.id.recyclerView),
                          0),
                    0),
                    isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
          final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                      && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
