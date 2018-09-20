package com.pajato.argus

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.Menu
import org.hamcrest.Matchers.allOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @JvmField
    @Rule
    var rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun onCreate_shows_the_hello_world_text_view_correctly() {
        val activity = rule.activity
        val expectedText = activity.getString(R.string.helloWorld)
        onView(withId(R.id.helloWorld)).check(matches(withText(expectedText)))
        onView(withId(R.id.fab)).check(matches(isDisplayed()))
    }

    @Test
    fun clicking_on_the_FAB_button_triggers_the_anonymous_handler() {
        onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click())
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Replace with your own action")))
                .check(matches(isDisplayed()))
    }
    @Test
    fun onCreateOptionsMenu() {
        val activity = rule.activity
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        Assert.assertTrue(activity.optionsMenu is Menu)
    }

    @Test
    fun onOptionsItemSelectedWithNormalItem() {
        val activity = rule.activity
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        onView(withText("Settings")).perform(click())
    }

    @Test
    fun onOptionsItemSelectedWithTestItem() {
        val activity = rule.activity
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        activity.optionsMenu?.apply {
            activity.onOptionsItemSelected(findItem(R.id.test))
            return
        }
        Assert.fail("Menu was not established!")
    }

    @Test
    fun testSetOptionsMenu() {
        val activity = rule.activity
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        val menu = activity.optionsMenu
        activity.optionsMenu = menu
    }
}
