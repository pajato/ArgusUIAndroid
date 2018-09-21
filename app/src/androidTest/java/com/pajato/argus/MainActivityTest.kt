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
    fun onCreateOptionsMenu() {
        val activity = rule.activity
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        Assert.assertTrue(activity.optionsMenu is Menu)
    }

    @Test
    fun onOptionsItemSelectedWithNormalItem() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText("Settings")).perform(click())
    }

    @Test
    fun onOptionsItemSelectedWithTestItem() {
        val activity = rule.activity
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        activity.optionsMenu?.apply {
            activity.onOptionsItemSelected(findItem(R.id.test))
            return
        }
        Assert.fail("Menu was not established!")
    }

    @Test
    fun testSetOptionsMenu() {
        val activity = rule.activity
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        val menu = activity.optionsMenu
        activity.optionsMenu = menu
    }
}
